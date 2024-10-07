package com.sourabh.blogapp.presentation.bloglist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sourabh.blogapp.R
import com.sourabh.blogapp.data.remote.responseItem
import com.sourabh.blogapp.viewmodel.BlogViewModel
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogListScreen(
    navController: NavController,
    viewModel: BlogViewModel
) {
    val blogs by viewModel.blogs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isOffline by viewModel.isOffline.collectAsState()
    var showInitialLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(50)
        showInitialLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Blog App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.grey),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        // For further improvement
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_bookmark_added_24),
                            contentDescription = "Refresh",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (showInitialLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                if (isOffline) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(id = R.drawable.nointernet2),
                            contentDescription = "Error",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(blogs) { blog ->
                            BlogListItem(blog) {
                                val encodedUrl =
                                    URLEncoder.encode(blog.link, StandardCharsets.UTF_8.toString())
                                navController.navigate("blogDetail/$encodedUrl")
                            }
                        }
                    }

//                    if (isLoading) {
//                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                    }
                }
            }
        }

        LaunchedEffect(blogs) {
            if (blogs.isNotEmpty() && !isLoading && !isOffline) {
                viewModel.loadMoreBlogs()
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
            Text(text = blog.title.rendered, style = MaterialTheme.typography.titleLarge)
        }
    }
}