package com.sourabh.blogapp.presentation.bloglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
            if (blogs.isNotEmpty()) {
                LaunchedEffect(Unit) {
                    viewModel.loadMoreBlogs()
                }
                CircularProgressIndicator()
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
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = blog.excerpt.rendered, style = MaterialTheme.typography.headlineSmall)
        }
    }
}
