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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CombinedImage(
    modifier: Modifier,
    borderImage: Painter,
    championImageUrl: String,
    imageSize: Dp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {

        GlideImage(
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape),
            model = championImageUrl,
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .size(imageSize)
                .blur(1.dp),
            painter = borderImage,
            contentDescription = null
        )
    }
}