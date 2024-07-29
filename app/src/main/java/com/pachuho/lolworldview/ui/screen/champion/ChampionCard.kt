package com.pachuho.lolworldview.ui.screen.champion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.Champion
import com.pachuho.lolworldview.ui.theme.Gold200

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampionCard(
    champion: Champion
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {

        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${champion.id}_0.jpg",
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 20.dp)
        ) {
            Text(
                text = champion.title,
                color = Gold200,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = champion.name,
                color = Gold200,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview
@Composable
fun ChampionCardPreview() {
    ChampionCard(
        champion = Champion(
            "Aatrox",
            "Aatrox",
            "the Darkin Blade",
            listOf("Fighter"),
        )
    )
}