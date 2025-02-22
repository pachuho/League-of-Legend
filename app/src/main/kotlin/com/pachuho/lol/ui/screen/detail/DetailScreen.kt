package com.pachuho.lol.ui.screen.detail

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
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.pachuho.lol.R
import com.pachuho.lol.data.remote.UrlConstants
import com.pachuho.lol.ui.screen.detail.component.ChampionElement
import com.pachuho.lol.ui.theme.Gray500
import com.pachuho.lol.ui.utils.Glide
import com.pachuho.lol.ui.utils.UiState
import com.pachuho.lol.ui.utils.successOrNull
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
    val animationDurationMillis = 1500
    val configuration = LocalContext.current.resources.configuration
    val screenHeightPx = with(LocalDensity.current) { configuration.screenHeightDp.dp.toPx() }
    val imageHeightPx = screenHeightPx * 0.3f
    val offsetPx = imageHeightPx * 0.75f

    LaunchedEffect(Unit) {
        delay(500)
        isContentVisible = true
        imageScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = animationDurationMillis)
        )
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
                .height(with(LocalDensity.current) { imageHeightPx.toDp() })
                .graphicsLayer(
                    scaleX = imageScale.value,
                    scaleY = imageScale.value
                ),
            imageUrl = UrlConstants.getChampionSplashImage(championId),
            loadingImageResource = R.drawable.ic_loading_splash
        )

        AnimatedVisibility(
            visible = isContentVisible,
            enter = fadeIn(animationSpec = tween(animationDurationMillis)) + slideInVertically(
                initialOffsetY = { it / 10 }
            ),
            exit = fadeOut(animationSpec = tween(animationDurationMillis)) + slideOutVertically(
                targetOffsetY = { it / 10 }
            )
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset { IntOffset(0, offsetPx.toInt()) }
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