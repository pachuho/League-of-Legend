package com.pachuho.lolworldview.data.repository

import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.data.model.ChampionDetail
import com.pachuho.lolworldview.ui.utils.UiState
import kotlinx.coroutines.flow.Flow

interface LOLRepository {
    fun getAllChampions(): Flow<List<Champion>>
    fun getChampionInfo(championId: String): Flow<UiState<ChampionDetail>>
}