package com.example.liverpooltest.data

import com.example.liverpooltest.data.model.ApiResponse
import com.example.liverpooltest.data.model.Products
import com.example.liverpooltest.data.model.SortOptions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor() {
    var products : List<Products> = emptyList()
    var sortOptions : List<SortOptions> = emptyList()

}