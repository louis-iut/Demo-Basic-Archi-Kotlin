package com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote

import com.google.gson.annotations.SerializedName

data class RemoteDate(
        @SerializedName("date")
        val date: String
)