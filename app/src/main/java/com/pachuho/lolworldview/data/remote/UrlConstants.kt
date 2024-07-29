package com.pachuho.lolworldview.data.remote

object UrlConstants {
    const val BASE_URL = "https://ddragon.leagueoflegends.com/cdn"
    const val VERSION = "14.14.1"

    fun getSplashImage(id: String) = "${BASE_URL}/img/champion/splash/${id}_0.jpg"
    fun getSquareImage(id: String) = "${BASE_URL}/${VERSION}/img/champion/$id.png"
}