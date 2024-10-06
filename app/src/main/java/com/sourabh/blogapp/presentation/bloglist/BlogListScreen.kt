package com.sourabh.blogapp.presentation.bloglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sourabh.blogapp.data.remote.responseItem
import com.sourabh.blogapp.viewmodel.BlogViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BlogListScreen(
    navController: NavController,
    viewModel: BlogViewModel
) {
    val blogs by viewModel.blogs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(blogs) { blog ->
            BlogListItem(blog) {
                val encodedUrl = URLEncoder.encode(blog.link, StandardCharsets.UTF_8.toString())
                navController.navigate("blogDetail/$encodedUrl")
            }
        }

        item {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                // Trigger load more when the user scrolls to the end
                LaunchedEffect(blogs) {
                    if (blogs.isNotEmpty()) {
                        viewModel.loadMoreBlogs()
                    }
                }
            }
        }
    }
}

@Composable
fun BlogListItem(blog: responseItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = blog.title.rendered, style = MaterialTheme.typography.headlineSmall)
        }
    }
}
