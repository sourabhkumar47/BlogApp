package com.sourabh.blogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sourabh.blogapp.ui.theme.BlogAppTheme
import com.sourabh.blogapp.viewmodel.BlogViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlogAppTheme {

                val navController = rememberNavController()
                val blogViewModel: BlogViewModel = viewModel()

                BlogAppNavHost(
                    navController = navController,
                    viewModel = blogViewModel
                )
            }
        }
    }
}