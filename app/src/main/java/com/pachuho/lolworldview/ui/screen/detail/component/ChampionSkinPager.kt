package com.pachuho.lolworldview.ui.screen.detail.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import animatePagerTransition
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.ChampionDetail.Skin
import com.pachuho.lolworldview.data.remote.UrlConstants
import com.pachuho.lolworldview.ui.theme.Gold400
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun ChampionSkinPager(
    championId: String,
    skins: List<Skin>
) {
    val pagerState = rememberPagerState(pageCount = { skins.size })

    Box(modifier = Modifier.padding(top = 80.dp, bottom = 220.dp)) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 80.dp),
                pageSpacing = (-30).dp
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GlideImage(
                        modifier = Modifier
                            .size(400.dp)
                            .animatePagerTransition(pagerState, page),
                        model = UrlConstants.getChampionLoadingImage("${championId}_${skins[page].num}"),
                        loading = placeholder(R.drawable.ic_loading_aatrox_0),
                        failure = placeholder(R.drawable.ic_loading_aatrox_0),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .alpha(getTextAlpha(pagerState, page)),
                        text = skins[page].name,
                        color = Gold400,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        minLines = 2,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Clip
                    )

                }

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun getTextAlpha(
    pagerState: PagerState,
    page: Int
): Float {
    val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
    return if (abs(pageOffset) < 0.25f) {
        1f - (abs(pageOffset) / 0.25f)
    } else {
        0f
    }
}

@Preview
@Composable
fun ChampionSkinsPreview() {
    ChampionSkinPager(
        championId = "Aatrox",
        skins = listOf(
            Skin(0, "default"),
            Skin(1, "Justicar Aatrox"),
            Skin(2, "Mecha Aatrox"),
            Skin(3, "Sea Hunter Aatrox"),
            Skin(4, "Blood Moon Aatrox"),
            Skin(5, "Prestige Blood Moon Aatrox"),
        )
    )
}