package com.pachuho.lol.data.remote

object UrlConstants {
    const val BASE_URL = "https://ddragon.leagueoflegends.com/cdn"
    const val VERSION = "14.14.1"

    fun getChampionSplashImage(id: String) = "${BASE_URL}/img/champion/splash/${id}_0.jpg"
    fun getChampionSquareImage(id: String) = "${BASE_URL}/${VERSION}/img/champion/$id.png"
    fun getChampionPassiveImage(fileName: String) = "${BASE_URL}/${VERSION}/img/passive/$fileName"
    fun getChampionSpellImage(spell: String) = "${BASE_URL}/${VERSION}/img/spell/$spell"
    fun getChampionLoadingImage(fileName: String) = "${BASE_URL}/img/champion/loading/$fileName.jpg"
}