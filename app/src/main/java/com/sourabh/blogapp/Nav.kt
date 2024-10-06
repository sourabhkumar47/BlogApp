package com.sourabh.blogapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sourabh.blogapp.presentation.blogdetail.BlogDetailScreen
import com.sourabh.blogapp.presentation.bloglist.BlogListScreen
import com.sourabh.blogapp.viewmodel.BlogViewModel

@Composable
fun BlogAppNavHost(navController: NavHostController, viewModel: BlogViewModel) {
    NavHost(navController = navController, startDestination = "blogList") {
        composable("blogList") {
            BlogListScreen(navController, viewModel)
        }
        composable("blogDetail/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            BlogDetailScreen(navController, url ?: "")
        }
    }
}
