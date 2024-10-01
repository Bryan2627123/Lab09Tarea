package com.example.lab09tarea

import retrofit2.http.GET
import com.example.lab09tarea.ProductsResponse

interface ProductsApiService {
    @GET("products")
    suspend fun getProducts(): ProductsResponse
}
