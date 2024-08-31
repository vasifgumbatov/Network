package com.vasifgumbatov.networking.Api

import com.vasifgumbatov.networking.Data.PostsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getPosts(): Call<List<PostsResponse>>
}