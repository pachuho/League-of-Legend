package com.pachuho.lolworldview.ui.screen.tag

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import animatePagerTransition
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.ChampionTag
import com.pachuho.lolworldview.ui.theme.Gold400
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerHelper(
    @DrawableRes images: List<Int>,
    modifier: Modifier = Modifier,
    onPageChanged: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    LaunchedEffect(pagerState.currentPage) {
        onPageChanged(pagerState.currentPage)
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {

            HorizontalPager(
                modifier = modifier,
                state = pagerState,
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState,
                    pagerSnapDistance = PagerSnapDistance.atMost(0)
                ),
                contentPadding = PaddingValues(horizontal = 80.dp),
                pageSpacing = (-10).dp
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        DrawImage(images[page], page, pagerState) {
                            onClick.invoke(page)
                        }
                    }
                    Text(
                        modifier = Modifier.animatePagerTransition(pagerState, page),
                        text = ChampionTag.entries[page].name,
                        color = Gold400,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrawImage(
    imageId: Int,
    page: Int,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    Box {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .animatePagerTransition(pagerState, page)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = true,
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(page)
                        onClick()
                    }
                }
        )
    }
}

@Composable
@Preview
fun PagerHelperPreview() {
    PagerHelper(
        listOf(
            R.drawable.ic_role_fighter,
            R.drawable.ic_role_tank,
            R.drawable.ic_role_marksman,
            R.drawable.ic_role_mage,
            R.drawable.ic_role_assassin,
            R.drawable.ic_role_support,
        ),
        onPageChanged = {},
        onClick = {}
    )
}