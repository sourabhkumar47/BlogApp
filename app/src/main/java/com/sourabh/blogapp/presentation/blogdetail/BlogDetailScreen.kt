package com.sourabh.blogapp.presentation.blogdetail

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun BlogDetailScreen(
    url: String
) {
    val decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())

    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            loadUrl(decodedUrl)
        }
    })
}
