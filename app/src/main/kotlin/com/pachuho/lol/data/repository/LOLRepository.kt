package com.pachuho.lol.data.repository

import com.pachuho.lol.data.model.Champion
import com.pachuho.lol.data.model.ChampionDetail
import com.pachuho.lol.ui.utils.UiState
import kotlinx.coroutines.flow.Flow

interface LOLRepository {
    fun getAllChampions(): Flow<List<Champion>>
    fun getChampionInfo(championId: String): Flow<UiState<ChampionDetail>>
}