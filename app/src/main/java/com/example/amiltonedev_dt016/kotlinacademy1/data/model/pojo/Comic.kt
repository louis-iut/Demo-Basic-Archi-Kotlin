package com.example.amiltonedev_dt016.kotlinacademy1.data.model.pojo

data class Comic(
        val id: String,
        val title: String,
        val date: String,
        val coverImagePath: String?,
        val creators: List<Creator>,
        val diamondCode: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Comic

        return id == other.id
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}