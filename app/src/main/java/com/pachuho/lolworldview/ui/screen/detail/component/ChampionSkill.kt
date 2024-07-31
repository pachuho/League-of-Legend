package com.pachuho.lolworldview.ui.screen.detail.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.pachuho.lolworldview.R
import com.pachuho.lolworldview.data.model.ChampionDetail.Spell
import com.pachuho.lolworldview.data.model.ChampionDetail.Image
import com.pachuho.lolworldview.data.model.ChampionDetail.Passive
import com.pachuho.lolworldview.data.remote.UrlConstants
import com.pachuho.lolworldview.ui.theme.Gold100
import com.pachuho.lolworldview.ui.theme.Gold200

@Composable
fun PassiveDetail(passive: Passive) {
    ChampionSkill(
        isPassive = true,
        id = "P",
        name = passive.name,
        description = passive.description,
        fileName = passive.image.fileName
    )
}

@Composable
fun SpellsDetail(spells: List<Spell>) {
    spells.forEachIndexed { index, spell ->
        ChampionSkill(
            isPassive = false,
            id = getSpellTag(index),
            name = spell.name,
            description = spell.description,
            fileName = spell.image.fileName
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampionSkill(
    isPassive: Boolean,
    id: String,
    name: String,
    description: String,
    fileName: String
) {
    Row(
        modifier = Modifier
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
            GlideImage(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.2.dp, Gold200, RoundedCornerShape(4.dp)),
                model = getImageModel(isPassive, fileName),
                loading = placeholder(R.drawable.ic_passive_anivia), // todo fix
                contentDescription = null
            )

            Text(
                text = id.last().toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Gold200
            )
        }

        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Gold200
            )

            Text(
                text = description,
                style = MaterialTheme.typography.labelLarge,
                color = Gold100,
                textAlign = TextAlign.Start
            )
        }
    }
}

private fun getImageModel(
    isPassive: Boolean,
    fileName: String
): String {
    return when(isPassive) {
        true -> UrlConstants.getChampionPassiveImage(fileName)
        false -> UrlConstants.getChampionSpellImage(fileName)
    }
}

private fun getSpellTag(index: Int): String {
    return when(index) {
        0 -> "Q"
        1 -> "W"
        2 -> "E"
        3 -> "R"
        else -> "N"
    }
}

@Preview
@Composable
fun ChampionSpellPreview() {
    PassiveDetail(
        passive = Passive(
            name = "Deathbringer Stance",
            rawDescription = "Periodically, Aatrox's next basic attack deals bonus <physicalDamage>physical damage</physicalDamage> and heals him, based on the target's max health. ",
            image = Image("Aatrox_Passive.png")
        )
    )
}

@Preview
@Composable
fun SpellsDetailPreview() {
    SpellsDetail(
        spells = listOf(
            Spell(
                id = "AatroxQ",
                name = "The Darkin Blade",
                rawDescription = "Aatrox slams his greatsword down, dealing physical damage. He can swing three times, each with a different area of effect.",
                image = Image("AatroxQ.png")
            )
        )
    )
}