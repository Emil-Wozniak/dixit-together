package pl.ejdev.dixittogether.features.core.shared

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import pl.ejdev.dixittogether.R
import pl.ejdev.dixittogether.R.font

private fun ttf(fontId: Int): FontFamily = FontFamily(Font(fontId, Normal))

internal val KeltWide = ttf(font.kelt_wide_normal)
internal val Friedrich = ttf(font.friedrich)
internal val Garamond = ttf(font.garamond)