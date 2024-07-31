package com.pachuho.lolworldview.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.remote.UrlConstants
import com.pachuho.lolworldview.ui.screen.detail.component.ChampionElement
import com.pachuho.lolworldview.ui.theme.Gray500
import com.pachuho.lolworldview.ui.utils.UiState
import com.pachuho.lolworldview.ui.utils.successOrNull

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    championId: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.champion.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500)
    ) {

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GlideImage(
                modifier = Modifier.fillMaxWidth(),
                model = UrlConstants.getChampionSplashImage(championId),
                loading = placeholder(R.drawable.ic_background3),
                contentDescription = null
            )

            when (uiState.value) {
                is UiState.Loading -> {}
                is UiState.Error -> {}
                is UiState.Success -> {
                    uiState.value.successOrNull()?.let {
                        ChampionElement(champion = it)
                    } ?: run {

                    }
                }
            }
        }

    }

}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 640)
fun DetailScreenPreview() {
    DetailScreen("Aatrox")
}