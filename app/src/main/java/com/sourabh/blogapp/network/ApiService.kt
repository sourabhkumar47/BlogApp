package com.sourabh.blogapp.network

import com.sourabh.blogapp.data.remote.response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("wp-json/wp/v2/posts")
    suspend fun getBlogs(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1
    ): response
}