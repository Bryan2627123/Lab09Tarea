package com.example.lab09tarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab09tarea.ui.theme.Lab09TareaTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab09TareaTheme {
                val viewModel: ProductsViewModel = viewModel()
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    viewModel.fetchProducts()
                }

                @OptIn(ExperimentalMaterial3Api::class)
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Products") }
                        )
                    },
                    content = { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = "products",
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable("products") {
                                ProductsScreen(viewModel = viewModel, navController = navController)
                            }
                            composable("productDetail/{productId}") { backStackEntry ->
                                val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: 0
                                ProductDetailScreen(productId = productId, viewModel = viewModel, navController = navController)
                            }
                        }
                    }
                )
            }
        }
    }
}

