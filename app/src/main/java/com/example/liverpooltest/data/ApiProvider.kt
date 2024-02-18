package com.example.liverpooltest.data

import com.example.liverpooltest.data.model.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor() {
    var info : ApiResponse = ApiResponse(null,null)
}