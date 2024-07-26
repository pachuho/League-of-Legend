package com.pachuho.lolworldview.data.repository

import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.data.model.ChampionInfo
import com.pachuho.lolworldview.data.remote.ChampionService
import com.pachuho.lolworldview.data.utils.EmptyBodyException
import com.pachuho.lolworldview.data.utils.NetworkFailureException
import com.pachuho.lolworldview.data.utils.fetch
import com.pachuho.lolworldview.ui.utils.UiState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LOLRepositoryImpl @Inject constructor(
    private val championService: ChampionService
) : LOLRepository {
    override suspend fun getAllChampions() = championService.fetchAllChampions().fetch { it.toList() }
    override suspend fun getChampionInfo(championId: String) = championService.fetchChampionInfo(championId).fetch { it.toList().first() }
}