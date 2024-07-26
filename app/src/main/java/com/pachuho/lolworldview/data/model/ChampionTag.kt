package com.pachuho.lolworldview.data.model

enum class ChampionTag {
    Fighter,
    Tank,
    Mage,
    Assassin,
    Marksman,
    Support;

    companion object {
        fun find(tag: String): ChampionTag? {
            return entries.find { it.name == tag }
        }
    }
}