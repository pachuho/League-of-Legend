package com.pachuho.lolworldview.ui.screen.champion

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.Champion

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChampionScreen(
    champions: List<Champion>,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(10.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.ic_background2),
            contentDescription = null
        )

        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            LazyColumn {
                items(champions.size) { index ->
                    ChampionCard(champions[index], onClick)
                }
            }
        }
    }
}