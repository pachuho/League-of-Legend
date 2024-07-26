package com.pachuho.lolworldview.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pachuho.lolworldview.R

// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//)

val lol = FontFamily(
    Font(R.font.lol_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.lol_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.lol_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.lol_light, FontWeight.Light, FontStyle.Normal)
)

object AppFont {
    val lol = FontFamily(
        Font(R.font.lol_regular),
        Font(R.font.lol_italic, style = FontStyle.Italic),
        Font(R.font.lol_medium, FontWeight.Medium),
        Font(R.font.lol_medium_italic, FontWeight.Medium, style = FontStyle.Italic),
        Font(R.font.lol_light, FontWeight.Light),
        Font(R.font.lol_light_italic, FontWeight.Light, style = FontStyle.Italic),
        Font(R.font.lol_bold, FontWeight.Bold),
        Font(R.font.lol_bold_italic, FontWeight.Bold, style = FontStyle.Italic)
    )
}

private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.lol),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.lol),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.lol),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.lol),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.lol),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.lol),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.lol),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.lol),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.lol),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.lol),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.lol),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.lol),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.lol),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.lol),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.lol)
)