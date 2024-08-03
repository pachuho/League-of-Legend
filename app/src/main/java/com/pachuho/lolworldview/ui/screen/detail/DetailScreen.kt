package com.pachuho.lolworldview.ui.screen.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.remote.UrlConstants
import com.pachuho.lolworldview.ui.screen.detail.component.ChampionElement
import com.pachuho.lolworldview.ui.theme.Gray500
import com.pachuho.lolworldview.ui.utils.Glide
import com.pachuho.lolworldview.ui.utils.UiState
import com.pachuho.lolworldview.ui.utils.successOrNull
import kotlinx.coroutines.delay

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    championId: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.champion.collectAsStateWithLifecycle()
    val imageScale = remember { Animatable(1.3f) }
    var isContentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(500)
        imageScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
        isContentVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500)
            .verticalScroll(rememberScrollState()),
    ) {
        Glide(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(
                    scaleX = imageScale.value,
                    scaleY = imageScale.value
                ),
            imageUrl = UrlConstants.getChampionSplashImage(championId),
            loadingImageResource = R.drawable.ic_loading_splash
        )

        AnimatedVisibility(
            visible = isContentVisible,
            enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(
                initialOffsetY = { it / 10 } // 살짝 아래에서 시작
            ),
            exit = fadeOut(animationSpec = tween(1000)) + slideOutVertically(
                targetOffsetY = { it / 10 } // 살짝 아래로 사라짐
            )
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 190.dp)
                    .zIndex(1f)
            ) {

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
}