package com.sourabh.blogapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.blogapp.data.remote.responseItem
import com.sourabh.blogapp.data.repository.BlogRepository
import com.sourabh.blogapp.utility.NetworkUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BlogRepository()
    private val networkUtils = NetworkUtils(application)

    private val _blogs = MutableStateFlow<List<responseItem>>(emptyList())
    val blogs: StateFlow<List<responseItem>> = _blogs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isOffline = MutableStateFlow(true)
    val isOffline: StateFlow<Boolean> = _isOffline

    private var currentPage = 1
    private var perPage = 10
    private var isLastPage = false

    init {
        viewModelScope.launch {
            networkUtils.isConnected.collect { isConnected ->
                _isOffline.value = !isConnected
                if (isConnected) {
                    getBlogs()
                }
            }
        }
    }

    private fun getBlogs() {
        if (isLastPage || _isLoading.value || _isOffline.value) return

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
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMoreBlogs() {
        getBlogs()
    }
}