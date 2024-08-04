package com.pachuho.lol.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@ExperimentalGlideComposeApi
@Composable
fun Glide(
    modifier: Modifier,
    imageUrl: String,
    @DrawableRes loadingImageResource: Int,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val placeholder = placeholder(loadingImageResource)
    GlideImage(
        modifier = modifier,
        model = imageUrl,
        loading = placeholder,
        failure = placeholder,
        contentScale = contentScale,
        contentDescription = null
    )
}