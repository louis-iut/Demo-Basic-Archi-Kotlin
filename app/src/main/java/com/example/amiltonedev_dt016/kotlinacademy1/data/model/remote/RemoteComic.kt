package com.example.amiltonedev_dt016.kotlinacademy1.data.model.remote

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Creator
import java.util.*

data class RemoteComic(
    val id: String,
    val title: String,
    val date: Date,
    val coverImagePath: String?,
    val creators: List<RemoteCreator>,
    val diamondCode: String
)