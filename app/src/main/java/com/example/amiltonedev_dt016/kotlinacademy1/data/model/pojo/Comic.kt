package com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo

import java.util.*

data class Comic(
        val id: String,
        val title: String,
        val date: Date,
        val coverImagePath: String?,
        val creators: List<Creator>,
        val diamondCode: String
)