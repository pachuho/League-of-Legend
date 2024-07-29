package com.pachuho.lolworldview.ui.screen.champion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.data.repository.LOLRepository
import com.pachuho.lolworldview.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChampionViewModel @Inject constructor(
    lolRepository: LOLRepository
): ViewModel() {

    val uiState: StateFlow<UiState<List<Champion>>> = lolRepository.getAllChampions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = UiState.Loading
        )
}