package com.pachuho.lol.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@ExperimentalGlideComposeApi
@Composable
fun Glide(
    modifier: Modifier,
    imageUrl: String,
    @DrawableRes loadingImageResource: Int
) {
    val placeholder = placeholder(loadingImageResource)
    GlideImage(
        modifier = modifier,
        model = imageUrl,
        loading = placeholder,
        failure = placeholder,
        contentDescription = null
    )
}