package com.example.amiltonedev_dt016.kotlinacademy1.data.manager

import com.example.amiltonedev_dt016.kotlinacademy1.data.ComicsDatabase
import com.example.amiltonedev_dt016.kotlinacademy1.data.model.db.DBComic
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.save
import com.raizlabs.android.dbflow.kotlinextensions.select

class DBManagerImpl: DBManager {
    override fun getComics(): List<DBComic> {
        return (select from DBComic::class).queryList()
    }

    override fun setComics(comics: List<DBComic>) {
        FlowManager.getDatabase(ComicsDatabase::class.java).executeTransaction {
            comics.forEach {
                it.save()
            }
        }
    }
}