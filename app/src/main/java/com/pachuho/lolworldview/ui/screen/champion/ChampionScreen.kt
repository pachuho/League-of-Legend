package com.pachuho.lolworldview.ui.screen.champion

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.data.model.ChampionTag
import com.pachuho.lolworldview.ui.utils.UiState
import com.pachuho.lolworldview.ui.utils.successOrNull
import com.pachuho.lolworldview.ui.utils.throwableOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChampionScreen(
    tag: String?,
    navHostController: NavHostController,
    championViewModel: ChampionViewModel = hiltViewModel()
) {
    val uiState by championViewModel.uiState.collectAsStateWithLifecycle()
    val localContext = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(10.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.ic_background3),
            contentDescription = null
        )

        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                uiState.throwableOrNull()?.let {
                    Toast.makeText(localContext, it.message, Toast.LENGTH_SHORT).show()
                }
                Log.i("asdf", "TagScreen: ${(uiState as UiState.Error).error?.message}")
            }

            is UiState.Success -> {
                Log.i("asdf", "TagScreen: Success!")
                uiState
                    .successOrNull()
                    ?.filter { it.tags?.first() == tag }
                    ?.let { champions ->
                        CompositionLocalProvider(
                            LocalOverscrollConfiguration provides null
                        ) {
                            LazyColumn {
                                items(champions.size) { index ->
                                    ChampionCard(champions[index])
                                }
                            }
                        }
                    }
            }
        }
    }
}