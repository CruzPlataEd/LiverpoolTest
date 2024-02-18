package com.example.liverpooltest.data

import com.example.liverpooltest.data.model.ApiResponse
import com.example.liverpooltest.data.network.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api : ApiService, private val apiProvider: ApiProvider) {
    suspend fun getInfoApi(page : Int, search : String, sortOption : String): ApiResponse {
        val response = api.getInfo(page,search, sortOption)
        apiProvider.info = response
        return response
    }
}