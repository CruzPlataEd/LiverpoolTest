package com.example.liverpooltest.data.network

import com.example.liverpooltest.data.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(private val api: ApiClient) {
    suspend fun getInfo(page : Int, search : String, sortOption : String): ApiResponse {
        return withContext(Dispatchers.IO){
            val response = api.getInfoPage(page, search, sortOption, "false", 40, "false")
            response.body() ?: ApiResponse(null, null)
        }
    }
}