package com.pachuho.lolworldview.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.ChampionDetail
import com.pachuho.lolworldview.ui.theme.Gold100
import com.pachuho.lolworldview.ui.theme.Gold200

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampionSkill(
    modifier: Modifier,
    passive: ChampionDetail.Passive,
    spells: List<ChampionDetail.Spell>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.2f)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            GlideImage(
//                model = UrlConstants.getChampionPassiveImage(passive.image.fileName),
//                contentDescription = null
//            )

            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.2.dp, Gold200, RoundedCornerShape(4.dp)),
                painter = painterResource(id = R.drawable.ic_passive_anivia),
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = "P",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Gold200
            )

        }

        Column(
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = passive.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Gold200
            )

            Text(
                modifier = Modifier.padding(4.dp),
                text = passive.description,
                style = MaterialTheme.typography.labelLarge,
                color = Gold100
            )
        }
    }

}

@Preview
@Composable
fun ChampionSpellPreview() {
    ChampionSkill(
        modifier = Modifier,
        passive = ChampionDetail.Passive(
            name = "Deathbringer Stance",
            rawDescription = "Periodically, Aatrox's next basic attack deals bonus <physicalDamage>physical damage</physicalDamage> and heals him, based on the target's max health. ",
            image = ChampionDetail.Image("Aatrox_Passive.png")
        ),
        spells = listOf(
            ChampionDetail.Spell(
                id = "AatroxQ",
                name = "The Darkin Blade",
                description = "Aatrox slams his greatsword down, dealing physical damage. He can swing three times, each with a different area of effect.",
                image = ChampionDetail.Image("AatroxQ.png")
            ),
            ChampionDetail.Spell(
                id = "AatroxW",
                name = "The Darkin Blade",
                description = "Aatrox slams his greatsword down, dealing physical damage. He can swing three times, each with a different area of effect.",
                image = ChampionDetail.Image("AatroxW.png")
            ),
            ChampionDetail.Spell(
                id = "AatroxE",
                name = "The Darkin Blade",
                description = "Aatrox slams his greatsword down, dealing physical damage. He can swing three times, each with a different area of effect.",
                image = ChampionDetail.Image("AatroxE.png")
            ),
            ChampionDetail.Spell(
                id = "AatroxR",
                name = "The Darkin Blade",
                description = "Aatrox slams his greatsword down, dealing physical damage. He can swing three times, each with a different area of effect.",
                image = ChampionDetail.Image("AatroxR.png")
            )
        )
    )
}