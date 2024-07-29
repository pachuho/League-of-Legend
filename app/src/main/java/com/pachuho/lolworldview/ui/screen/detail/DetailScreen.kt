package com.pachuho.lolworldview.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.pachuho.lolworldview.data.remote.UrlConstants
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.ui.theme.Gold200
import com.pachuho.lolworldview.ui.theme.Gray500

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(champion: Champion) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray500)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // todo update
//            GlideImage(
//                modifier = Modifier.fillMaxWidth(),
//                model = Constants.getChampionSplashImage(champion.id),
//                contentDescription = null
//            )

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_aartrox_splash),
                contentDescription = null
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CombinedImage(
                    modifier = Modifier.offset(y = (-55).dp),
                    borderImage = painterResource(id = R.drawable.ic_border),
                    championImageUrl = UrlConstants.getSquareImage(champion.id),
                    imageSize = 100.dp
                )

                Text(
                    modifier = Modifier.offset(y = (-45).dp),
                    text = champion.name,
                    color = Gold200,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

    }

}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 640)
fun DetailScreenPreview() {
    DetailScreen(
        Champion(
            "Aatrox",
            "Aatrox",
            "the Darkin Blade",
        )
    )
}