package com.example.liverpooltest.data.model

import com.google.gson.annotations.SerializedName

data class SortOptions(
    @SerializedName("sortBy")
    val sortUrl : String,

    @SerializedName("lavel")
    val sortText : String
)
