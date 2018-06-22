package com.example.amiltonedev_dt016.kotlinacademy1.data.model.db

import com.example.amiltonedev_dt016.kotlinacademy1.data.ComicsDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table

@Table(database = ComicsDatabase::class)
class DBCreator {

    @PrimaryKey lateinit var id: String
    @Column lateinit var comicsId: String
    @Column lateinit var name: String
    @Column lateinit var role: String

    constructor()

    constructor(comicsId: String, name: String, role: String) {
        this.comicsId = comicsId
        this.name = name
        this.role = role
        id = comicsId + name + role
    }
}