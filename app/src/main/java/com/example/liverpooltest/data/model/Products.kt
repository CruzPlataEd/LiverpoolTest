package com.example.liverpooltest.data.model

import android.graphics.Color
import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("productId")
    val productId : String,

    @SerializedName("productDisplayName")
    val productName : String,

    @SerializedName("listPrice")
    val price : Double,

    @SerializedName("promoPrice")
    val promoPrice : Double,

    @SerializedName("brand")
    val brand : String,

    @SerializedName("smImage")
    val smallImage : String,

    @SerializedName("lgImage")
    val largeImage : String,

    @SerializedName("xlImage")
    val xlImage : String,

    @SerializedName("variantsColor")
    val colors : List<ColorsOptions>
)

data class ColorsOptions(
    @SerializedName("colorName")
    val colorName : String,

    @SerializedName("colorHex")
    val colorHex : String
)
