package com.pachuho.lolworldview.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Champion(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "tags") val tags: List<String>? = null
) {
    fun getTag(): ChampionTag? {
        return this.tags?.let {
            ChampionTag.find(it.first())
        }
    }
}