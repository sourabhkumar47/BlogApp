package com.sourabh.blogapp.presentation.blogdetail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun BlogDetailScreen(
    navController: NavController,
    url: String
) {
    // Decode URL before loading it in WebView
    val decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())

    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient() // Ensure links open within WebView
            loadUrl(decodedUrl) // Load decoded URL
        }
    })
}
