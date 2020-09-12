package com.example.retrofitexample.APIs;

import com.example.retrofitexample.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndPoints {

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @GET("posts/{id}")
    Call<Post> getSinglePost(@Path("id") int postId);

    @POST("posts")
    Call<Post> postNewCommentary(@Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int postId);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int postId, @Body Post post);

}
