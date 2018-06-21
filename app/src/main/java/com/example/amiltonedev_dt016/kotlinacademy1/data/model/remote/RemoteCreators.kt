package com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote

import com.google.gson.annotations.SerializedName

data class RemoteCreators(
        @SerializedName("items")
        val items: List<RemoteCreator>
)