package com.pachuho.lolworldview.ui.screen.detail.component

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import animatePagerTransition
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.ChampionDetail.Skin
import com.pachuho.lolworldview.data.remote.UrlConstants
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChampionSkinPager(
    modifier: Modifier,
    championId: String,
    skins: List<Skin>
) {
    val pagerState = rememberPagerState(pageCount = { skins.size })

    Box(modifier = modifier.fillMaxWidth()) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState,
                    pagerSnapDistance = PagerSnapDistance.atMost(0)
                ),
                contentPadding = PaddingValues(horizontal = 90.dp),
                pageSpacing = (-30).dp
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        DrawImageFromUrl(
                            url = UrlConstants.getChampionLoadingImage("${championId}_${skins[page].num}"),
                            page = page,
                            pagerState = pagerState
                        )
                    }


                    // todo
//                     val offset = pagerState.calculateCurrentOffsetForPage(page)
//                    Log.e("asdf", "offset: $offset")
//                    Text(
//                        modifier = Modifier
////                            .alpha(computeOpacity(offset))
//                        ,
//                        text = offset.toString(),
//                        color = Gold400,
//                        style = MaterialTheme.typography.headlineSmall
//                    )
                }

            }
        }
    }
}

fun computeOpacity(offset: Float): Float {
    if (abs(offset) > 0.5f) {
        return 1f
    }

    return 1 - abs(offset) * 2
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun DrawImageFromUrl(
    url: String,
    page: Int,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    Box {
        GlideImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 6.dp)
                .animatePagerTransition(pagerState, page)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = true,
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(page)
                    }
                },
            model = url,
            loading = placeholder(R.drawable.ic_loading_aatrox_0),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
fun ChampionSkinsPreview() {
    ChampionSkinPager(
        modifier = Modifier,
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