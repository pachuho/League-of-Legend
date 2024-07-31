package com.pachuho.lolworldview.ui.screen.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.ChampionDetail
import com.pachuho.lolworldview.data.model.ChampionTag
import com.pachuho.lolworldview.data.remote.UrlConstants
import com.pachuho.lolworldview.ui.theme.Blue700
import com.pachuho.lolworldview.ui.theme.Gold200
import com.pachuho.lolworldview.ui.theme.Gray100

@Composable
fun ChampionElement(champion: ChampionDetail) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CombinedImage(
            modifier = Modifier.offset(y = (-55).dp),
            borderImage = painterResource(id = R.drawable.ic_border),
            championImageUrl = UrlConstants.getChampionSquareImage(champion.id),
            imageSize = 100.dp
        )

        Text(
            modifier = Modifier.offset(y = (-50).dp),
            text = champion.name,
            color = Gold200,
            style = MaterialTheme.typography.headlineMedium
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = (-50).dp),
                style = MaterialTheme.typography.bodyLarge,
                text = champion.title,
                color = Gold200,
                textAlign = TextAlign.Start
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    champion.tags.forEach {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier.size(70.dp),
                                painter = painterResource(id = ChampionTag.getImageResource(it)),
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.offset(y = (-6).dp),
                                text = it,
                                color = Gold200,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 60.dp)
                    .wrapContentHeight()
                    .background(Blue700, RoundedCornerShape(6.dp))
                    .border(2.dp, Gray100, RoundedCornerShape(6.dp))
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 14.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    text = champion.lore,
                    color = Gold200,
                    textAlign = TextAlign.Start
                )
            }

            PassiveDetail(champion.passive)
            SpellsDetail(champion.spells)

            ChampionSkinPager(
                modifier = Modifier.padding(vertical = 100.dp),
                championId = champion.id,
                skins = champion.skins
            )
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 640)
fun ChampionDetailPreview() {
    ChampionElement(
        ChampionDetail(
            id = "Aatrox",
            name = "Aatrox",
            title = "the Darkin Blade",
            lore = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find freedom once more, corrupting and transforming those foolish enough to try and wield the magical weapon that contained his essence. Now, with stolen flesh, he walks Runeterra in a brutal approximation of his previous form, seeking an apocalyptic and long overdue vengeance.",
            image = ChampionDetail.Image("Aatrox.png"),
            tags = listOf("Fighter", "Support"),
            skins = listOf(
                ChampionDetail.Skin(
                    0,
                    "default"
                ),
                ChampionDetail.Skin(
                    1,
                    "Justicar Aatrox"
                ),
                ChampionDetail.Skin(
                    2,
                    "Mecha Aatrox"
                ),
                ChampionDetail.Skin(
                    3,
                    "Sea Hunter Aatrox"
                ),
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
            ),
            passive = ChampionDetail.Passive(
                name = "Deathbringer Stance",
                rawDescription = "Periodically, Aatrox's next basic attack deals bonus physical damage and heals him, based on the target's max health. ",
                image = ChampionDetail.Image("Aatrox_Passive.png")
            )
        )
    )
}