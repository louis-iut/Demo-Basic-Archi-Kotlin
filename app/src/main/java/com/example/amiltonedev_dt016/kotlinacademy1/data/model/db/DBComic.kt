package com.example.amiltonedev_dt016.kotlinacademy1.data.model.db

import com.example.amiltonedev_dt016.kotlinacademy1.data.ComicsDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.OneToMany
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.oneToMany
import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.kotlinextensions.where

@Table(database = ComicsDatabase::class)
class DBComic {

    @PrimaryKey lateinit var id: String
    @Column lateinit var title: String
    @Column lateinit var date: String
    @Column var coverImagePath: String? = null
    @Column lateinit var diamondCode: String

    @get:OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "creators")
    var creators by oneToMany { select from DBCreator::class where (DBCreator_Table.comicsId.eq(id)) }

    constructor()

    constructor(id: String, title: String, date: String, coverImagePath: String? = null, diamondCode: String, creators: List<DBCreator>) {
        this.id = id
        this.title = title
        this.date = date
        this.coverImagePath = coverImagePath
        this.diamondCode = diamondCode
        this.creators = creators
    }
}