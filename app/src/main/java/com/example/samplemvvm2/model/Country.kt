package com.example.samplemvvm2.model

import com.google.gson.annotations.SerializedName

data class Country(
        @SerializedName("name")
        val countyName: String?,
        @SerializedName("capital")
        val capital: String?,
        @SerializedName("flagPNG")
        val flag: String?
)