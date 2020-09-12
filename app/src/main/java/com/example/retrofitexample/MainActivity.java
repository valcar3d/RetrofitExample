package com.example.retrofitexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitexample.APIs.ApiEndPoints;
import com.example.retrofitexample.APIs.RetrofitInstance;
import com.example.retrofitexample.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPostComment = findViewById(R.id.btnPostComment);
        Button btnPatchPost = findViewById(R.id.btnPatchPost);

        btnPostComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating a dummy Post object to request a post from server
                Post newPost = new Post();
                newPost.setUserId(1);
                newPost.setId(1);
                newPost.setTitle("New title post");
                newPost.setBody("This is the body of the new post");

                postSingleComment(newPost);
            }
        });

        btnPatchPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating a dummy Edition object to request a post from server
                Post newPost = new Post();
                newPost.setUserId(1);
                newPost.setId(1);
                newPost.setTitle("Edition of post done");
                newPost.setBody("This is the body of the edition");

                patchPost(newPost);
            }
        });
    }

    //region Requests

    public void getAllPosts(View view) {

        ApiEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(ApiEndPoints.class);

        Call<List<Post>> call = apiEndPoints.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Toast.makeText(getApplicationContext(), "Posts downloaded", Toast.LENGTH_SHORT).show();
                System.out.println("Response = " + response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getSinglePost(View view) {

        ApiEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(ApiEndPoints.class);
        Call<Post> call = apiEndPoints.getSinglePost(1);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(getApplicationContext(), "The user 1 is: " + response.body().getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postSingleComment(Post newPost) {
        ApiEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(ApiEndPoints.class);
        Call<Post> call = apiEndPoints.postNewCommentary(newPost);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(getApplicationContext(), "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server error " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void deletePost(View view) {
        ApiEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(ApiEndPoints.class);
        Call<Void> call = apiEndPoints.deletePost(1);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void patchPost(Post post) {
        ApiEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(ApiEndPoints.class);
        Call<Post> call = apiEndPoints.patchPost(1, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(getApplicationContext(), "Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Post patched: " + response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //endregion
}