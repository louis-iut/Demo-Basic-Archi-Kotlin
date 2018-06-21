package com.example.amiltonedev_dt016.kotlinacademy1.ui.datawrapper

import com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo.Creator

class CreatorViewDataWrapper(val creator: Creator) {
    val name: String = creator.name
    val role: String = creator.role
}

fun mapCreatorsListToDataWrapper(creators: List<Creator>): List<CreatorViewDataWrapper> {
    val creatorsDataWrapper = ArrayList<CreatorViewDataWrapper>()
    creators.forEach { creatorsDataWrapper.add(CreatorViewDataWrapper(it)) }

    return creatorsDataWrapper
}