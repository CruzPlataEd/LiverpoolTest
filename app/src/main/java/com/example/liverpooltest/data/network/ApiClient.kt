package com.example.liverpooltest.data.network

import com.example.liverpooltest.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("")
    suspend fun getInfoPage(
        @Query("page-number") page : Int,
        @Query("search-string") search : String,
        @Query("sort-option") sort : String,
        @Query("force-plp") force : Boolean,
        @Query("number-of-items-per-page") items : Int,
        @Query("cleanProductName") clean : Boolean): Response<ApiResponse>
}