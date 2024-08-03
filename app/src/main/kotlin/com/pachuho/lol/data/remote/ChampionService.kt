package com.pachuho.lol.data.remote

import com.pachuho.lol.data.model.Champion
import com.pachuho.lol.data.model.ChampionDetail
import com.pachuho.lol.data.model.ChampionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionService {

    @GET("data/en_US/champion.json")
    suspend fun fetchAllChampions(): Response<ChampionResponse<Champion>>

    @GET("data/en_US/champion/{id}.json")
    suspend fun fetchChampionInfo(@Path("id") id: String): Response<ChampionResponse<ChampionDetail>>
}