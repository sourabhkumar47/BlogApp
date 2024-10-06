package com.sourabh.blogapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.blogapp.data.remote.responseItem
import com.sourabh.blogapp.data.repository.BlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel : ViewModel() {
    private val repository = BlogRepository()

    private val _blogs = MutableStateFlow<List<responseItem>>(emptyList())
    val blogs: StateFlow<List<responseItem>> = _blogs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var currentPage = 1
    private var perPage = 10
    private var isLastPage = false

    init {
        getBlogs()
    }

    private fun getBlogs() {
        if (isLastPage || _isLoading.value) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val blogResponse = repository.getBlogs(perPage, currentPage)

                if (blogResponse.isEmpty()) {
                    isLastPage = true
                } else {
                    _blogs.value += blogResponse
                    currentPage++
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Viewmodel", "getBlogs: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMoreBlogs() {
        getBlogs()
    }
}