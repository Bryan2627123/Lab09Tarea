package com.example.lab09tarea

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)

data class ProductsResponse(
    val products: List<Product>
)

