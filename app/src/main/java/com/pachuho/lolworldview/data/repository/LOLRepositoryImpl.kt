package com.pachuho.lolworldview.data.repository

import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.data.model.ChampionInfo
import com.pachuho.lolworldview.data.remote.ChampionService
import com.pachuho.lolworldview.data.utils.EmptyBodyException
import com.pachuho.lolworldview.data.utils.NetworkFailureException
import com.pachuho.lolworldview.ui.utils.UiState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LOLRepositoryImpl @Inject constructor(
    private val championService: ChampionService
) : LOLRepository {

    override fun getAllChampions(): Flow<UiState<List<Champion>>> = flow<UiState<List<Champion>>> {
        val response = championService.fetchAllChampions()
        if (response.isSuccessful) {
            val champions: List<Champion> = response.body()?.toList()
                ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            emit(UiState.Success(champions))
        } else {
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }.catch { emit(UiState.Error(it)) }

    override fun getChampionInfo(championId: String): Flow<UiState<ChampionInfo>> =
        flow<UiState<ChampionInfo>> {
            val response = championService.fetchChampionInfo(championId)
            if (response.isSuccessful) {
                val championInfo: ChampionInfo = response.body()?.toList()?.get(0)
                    ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
                emit(UiState.Success(championInfo))
            } else {
                throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
            }
        }.catch { UiState.Error(it) }
}