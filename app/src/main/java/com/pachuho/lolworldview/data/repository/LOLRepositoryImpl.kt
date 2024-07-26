package com.pachuho.lolworldview.data.repository

import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.data.model.ChampionInfo
import com.pachuho.lolworldview.data.remote.ChampionService
import com.pachuho.lolworldview.data.utils.fetch
import com.pachuho.lolworldview.data.utils.fetchList
import com.pachuho.lolworldview.ui.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LOLRepositoryImpl @Inject constructor(
    private val championService: ChampionService
) : LOLRepository {
    override fun getAllChampions(): Flow<UiState<List<Champion>>>  = flow {
        fetchList(championService.fetchAllChampions())
    }.catch { emit(UiState.Error(it)) }

    override fun getChampionInfo(championId: String): Flow<UiState<ChampionInfo>> = flow {
        fetch(championService.fetchChampionInfo(championId))
    }.catch { UiState.Error(it) }
}