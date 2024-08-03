package com.pachuho.lolworldview.ui.screen.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.remote.UrlConstants
import com.pachuho.lolworldview.ui.utils.Glide

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CombinedImage(
    championImageUrl: String,
    imageSize: Dp
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {

        Glide(
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape),
            imageUrl = championImageUrl,
            loadingImageResource = R.drawable.ic_loading_square
        )
        Image(
            modifier = Modifier
                .size(imageSize)
                .blur(1.dp),
            painter = painterResource(id = R.drawable.ic_border),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun CombineImagePreview() {
    CombinedImage(
        championImageUrl = UrlConstants.getChampionSquareImage(""),
        imageSize = 100.dp
    )
}