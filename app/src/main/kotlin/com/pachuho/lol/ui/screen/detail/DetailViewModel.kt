package com.pachuho.lol.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pachuho.lol.Screen
import com.pachuho.lol.data.model.ChampionDetail
import com.pachuho.lol.data.repository.LOLRepository
import com.pachuho.lol.ui.utils.UiState
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