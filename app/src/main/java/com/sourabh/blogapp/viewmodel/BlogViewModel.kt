package com.sourabh.blogapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.blogapp.data.remote.responseItem
import com.sourabh.blogapp.data.repository.BlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel: ViewModel() {
    private val repository = BlogRepository()

    private val _blogs = MutableStateFlow<List<responseItem>>(emptyList())
    val blogs: StateFlow<List<responseItem>> = _blogs

    private var currentPage = 1
    private var perPage = 10

    init {
        getBlogs()
    }

    private fun getBlogs() {
        viewModelScope.launch {
            try {
                val blogResponse = repository.getBlogs(perPage, currentPage)
                _blogs.value = blogResponse
                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Viewmodel", "getBlogs: ${e.message}")
            }
        }
    }

    fun loadMoreBlogs() {
        getBlogs()
    }
}