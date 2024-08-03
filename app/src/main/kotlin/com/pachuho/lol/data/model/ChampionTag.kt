package com.pachuho.lol.data.model

import com.pachuho.lol.R

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

        fun getImageResource(tag: String): Int {
            return when(find(tag)) {
                Fighter -> R.drawable.ic_role_fighter
                Tank -> R.drawable.ic_role_tank
                Mage -> R.drawable.ic_role_mage
                Assassin -> R.drawable.ic_role_assassin
                Marksman -> R.drawable.ic_role_marksman
                Support -> R.drawable.ic_role_support
                null -> R.drawable.ic_role_fighter
            }
        }
    }
}