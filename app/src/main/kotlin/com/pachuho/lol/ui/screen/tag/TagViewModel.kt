package com.pachuho.lol.ui.screen.tag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pachuho.lol.data.model.Champion
import com.pachuho.lol.data.repository.LOLRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    lolRepository: LOLRepository
): ViewModel() {

    val champions: StateFlow<List<Champion>> = lolRepository.getAllChampions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )
}