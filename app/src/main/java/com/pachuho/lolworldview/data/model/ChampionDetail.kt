package com.pachuho.lolworldview.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChampionDetail (
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "image") val image: Image,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "tags") val tags: List<String>,
    @field:Json(name = "lore") val lore: String,
    @field:Json(name = "spells") val spells: List<Spell>,
    @field:Json(name = "passive") val passive: Passive,
    @field:Json(name = "skins") val skins: List<Skin>,
) {

    @JsonClass(generateAdapter = true)
    data class Skin(
        @field:Json(name = "num") val num: Int,
        @field:Json(name = "name") val name: String
    )

    @JsonClass(generateAdapter = true)
    data class Spell(
        @field:Json(name = "id") val id: String,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "description") val description: String,
        @field:Json(name = "image") val image: Image
    )

    @JsonClass(generateAdapter = true)
    data class Passive(
        @field:Json(name = "name") val name: String,
        @field:Json(name = "description") val rawDescription: String,
        @field:Json(name = "image") val image: Image
    ) {
        private fun removeTags(input: String): String {
            val regex = "<[^>]+>".toRegex()
            return input.replace(regex, "")
        }
        val description: String
            get() = removeTags(rawDescription)

    }

    @JsonClass(generateAdapter = true)
    data class Image(
        @field:Json(name = "full") val fileName: String
    )
}
