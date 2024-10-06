package com.sourabh.blogapp.data.repository

import com.sourabh.blogapp.data.remote.response
import com.sourabh.blogapp.network.RetrofitInstance

class BlogRepository {

    private val apiService = RetrofitInstance.apiService

    suspend fun getBlogs(perPage: Int, page: Int): response {
        return apiService.getBlogs(perPage, page)
    }
}