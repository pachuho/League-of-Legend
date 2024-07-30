package com.pachuho.lolworldview.ui.screen.tag

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.data.model.ChampionTag
import com.pachuho.lolworldview.ui.utils.showToast
import com.pachuho.lolworldview.ui.utils.successOrNull

@Composable
fun TagScreen(
    viewModel: TagViewModel = hiltViewModel(),
    onClick: (List<Champion>) -> Unit
) {
    val context = LocalContext.current
    val champions = viewModel.champions.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var currentPage by remember { mutableIntStateOf(0) }
        var offset by remember { mutableIntStateOf(0) }
        val offsetStep = 80

        ImageWithoutUnBounds(R.drawable.ic_background1, offset.dp)
        PagerHelper(
            listOf(
                R.drawable.ic_role_fighter,
                R.drawable.ic_role_tank,
                R.drawable.ic_role_mage,
                R.drawable.ic_role_assassin,
                R.drawable.ic_role_marksman,
                R.drawable.ic_role_support,
            ),
            onPageChanged = {
                when {
                    currentPage > it -> offset += offsetStep
                    currentPage < it -> offset -= offsetStep
                }
                currentPage = it
            },
            onClick = { page ->
                when (champions.value.isNotEmpty()) {
                    true -> champions.value.filter { it.tags?.first() == ChampionTag.entries[page].name }.let(onClick)
                    false -> context.showToast(context.getString(R.string.fail_champions))
                }
            }
        )
    }
}

@Composable
private fun ImageWithoutUnBounds(
    @DrawableRes id: Int,
    offset: Dp
) {
    val animatedOffset by animateDpAsState(
        targetValue = offset, label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Image(
        modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally, unbounded = true)
            .heightIn(min = 10000.dp)
            .offset(animatedOffset),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        painter = painterResource(id = id)
    )
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 640)
fun TagScreenPreview() {
    TagScreen {}
}