package com.pachuho.lol.data.repository

import com.pachuho.lol.data.model.Champion
import com.pachuho.lol.data.model.ChampionDetail
import com.pachuho.lol.data.remote.ChampionService
import com.pachuho.lol.data.utils.fetchList
import com.pachuho.lol.data.utils.fetchWithUiState
import com.pachuho.lol.ui.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LOLRepositoryImpl @Inject constructor(
    private val championService: ChampionService
) : LOLRepository {
    override fun getAllChampions(): Flow<List<Champion>> = flow {
        fetchList(championService.fetchAllChampions())
    }.catch { it.message }

    override fun getChampionInfo(championId: String): Flow<UiState<ChampionDetail>> = flow {
        fetchWithUiState(championService.fetchChampionInfo(championId))
    }.catch { UiState.Error(it) }
}