package com.example.lab09tarea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import com.example.lab09tarea.Product
import com.example.lab09tarea.ProductsApiService
import com.example.lab09tarea.RetrofitInstance

class ProductsViewModel : ViewModel() {
    private val apiService: ProductsApiService = RetrofitInstance.api

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = apiService.getProducts()
                _products.value = response.products
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
