package com.pachuho.lolworldview.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pachuho.lolworldview.Screen
import com.pachuho.lolworldview.data.model.ChampionDetail
import com.pachuho.lolworldview.data.repository.LOLRepository
import com.pachuho.lolworldview.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.lang.IllegalStateException
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    lolRepository: LOLRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val championId = savedStateHandle.get<String>(Screen.Detail.argument)
        ?: throw IllegalStateException("not found champion")


    val champion: StateFlow<UiState<ChampionDetail>> = lolRepository.getChampionInfo(championId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = UiState.Loading
        )
}