package com.example.liverpooltest.data.network

import com.example.liverpooltest.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {
    @Headers("Accept: application/json")
    @GET("appclienteservices/services/v7/plp/sf")
    suspend fun getInfoPage(
        @Query("page-number") page : Int,
        @Query("search-string") search : String,
        @Query("sort-option") sort : String,
        @Query("force-plp") force : String,
        @Query("number-of-items-per-page") items : Int,
        @Query("cleanProductName") clean : String): Response<ApiResponse>
}