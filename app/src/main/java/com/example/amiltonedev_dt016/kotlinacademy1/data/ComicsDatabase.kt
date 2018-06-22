package com.example.amiltonedev_dt016.kotlinacademy1.data

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = ComicsDatabase.DB_NAME, version = ComicsDatabase.VERSION)
class ComicsDatabase {
    companion object {
        const val DB_NAME = "Comics"
        const val VERSION = 1
    }
}