package com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote

import android.media.Image
import com.google.gson.annotations.SerializedName
import java.util.*

data class RemoteComic(
        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("dates")
        val dates: List<RemoteDate>,
        @SerializedName("images")
        val images: List<RemoteImage>,
        @SerializedName("creators")
        val creators: RemoteCreators,
        @SerializedName("diamondCode")
        val diamondCode: String
)