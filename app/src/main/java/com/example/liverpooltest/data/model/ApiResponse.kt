package com.example.liverpooltest.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("status")
    val status: StatusResponse?,

    @SerializedName("plpResults")
    val information: InfoResponse?
)

data class StatusResponse(
    @SerializedName("status")
    val status : String,

    @SerializedName("statusCode")
    val statusCode : Int
)

data class InfoResponse(
    @SerializedName("sortOptions")
    val sortOptions : SortOptions,

    @SerializedName("records")
    val products : Products
)