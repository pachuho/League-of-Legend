package com.pachuho.lolworldview.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pachuho.lolworldview.R

val lolFontFamily = FontFamily(
    Font(R.font.lol_regular, FontWeight.Normal),
    Font(R.font.lol_bold, FontWeight.Bold),
    Font(R.font.lol_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.lol_light, FontWeight.Light),
    Font(R.font.lol_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.lol_medium, FontWeight.Medium),
    Font(R.font.lol_medium_italic, FontWeight.Medium, FontStyle.Italic)
)

val lolShadow = Shadow(
    color = Color.Black,
    offset = Offset(2f, 2f),
    blurRadius = 4f
)

val lolTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        shadow = lolShadow
    ),
    headlineMedium = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        shadow = lolShadow
    ),
    headlineSmall = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        shadow = lolShadow
    ),

    titleLarge = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        shadow = lolShadow
    ),
    titleMedium = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        shadow = lolShadow
    ),
    titleSmall = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        shadow = lolShadow
    ),

    bodyLarge = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        shadow = lolShadow
    ),
    bodyMedium = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        shadow = lolShadow
    ),
    bodySmall = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        shadow = lolShadow
    ),

    labelLarge = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        shadow = lolShadow
    ),
    labelMedium = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        shadow = lolShadow
    ),
    labelSmall = TextStyle(
        fontFamily = lolFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        shadow = lolShadow
    )
)
