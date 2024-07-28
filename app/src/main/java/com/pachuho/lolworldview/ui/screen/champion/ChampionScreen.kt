package com.pachuho.lolworldview.ui.screen.champion

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pachuho.lolworldview.ui.utils.UiState

@Composable
fun ChampionScreen(
    championViewModel: ChampionViewModel = hiltViewModel(),
    onClickChampion: (id: String) -> Unit
) {
    val uiState by championViewModel.uiState.collectAsStateWithLifecycle()

    Column {
        when(uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Error -> {
                Log.i("asdf", "TagScreen: ${(uiState as UiState.Error).error?.message}")
            }
            is UiState.Success -> {
                Log.i("asdf", "TagScreen: ${(uiState as UiState.Success).data}")
            }
        }
    }

}