package com.campbuddy.compose

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat


val seed = Color(0xFFFF9900)

val md_theme_light_primary = Color(0xFF8A5100)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFFFDCBD)
val md_theme_light_onPrimaryContainer = Color(0xFF2C1600)
val md_theme_light_secondary = Color(0xFF725A42)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFFEDCBE)
val md_theme_light_onSecondaryContainer = Color(0xFF291806)
val md_theme_light_tertiary = Color(0xFF58633A)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFDBE8B5)
val md_theme_light_onTertiaryContainer = Color(0xFF161F01)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_onBackground = Color(0xFF201B16)
val md_theme_light_surface = Color(0xFFFFFBFF)
val md_theme_light_onSurface = Color(0xFF201B16)
val md_theme_light_surfaceVariant = Color(0xFFF2DFD1)
val md_theme_light_onSurfaceVariant = Color(0xFF51453A)
val md_theme_light_outline = Color(0xFF837468)
val md_theme_light_inverseOnSurface = Color(0xFFFAEFE7)
val md_theme_light_inverseSurface = Color(0xFF352F2B)
val md_theme_light_inversePrimary = Color(0xFFFFB86F)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF8A5100)
val md_theme_light_outlineVariant = Color(0xFFD5C3B5)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFFFFB86F)
val md_theme_dark_onPrimary = Color(0xFF4A2800)
val md_theme_dark_primaryContainer = Color(0xFF693C00)
val md_theme_dark_onPrimaryContainer = Color(0xFFFFDCBD)
val md_theme_dark_secondary = Color(0xFFE1C1A4)
val md_theme_dark_onSecondary = Color(0xFF402C18)
val md_theme_dark_secondaryContainer = Color(0xFF59422C)
val md_theme_dark_onSecondaryContainer = Color(0xFFFEDCBE)
val md_theme_dark_tertiary = Color(0xFFBFCC9A)
val md_theme_dark_onTertiary = Color(0xFF2A3410)
val md_theme_dark_tertiaryContainer = Color(0xFF404B25)
val md_theme_dark_onTertiaryContainer = Color(0xFFDBE8B5)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF201B16)
val md_theme_dark_onBackground = Color(0xFFEBE0D9)
val md_theme_dark_surface = Color(0xFF201B16)
val md_theme_dark_onSurface = Color(0xFFEBE0D9)
val md_theme_dark_surfaceVariant = Color(0xFF51453A)
val md_theme_dark_onSurfaceVariant = Color(0xFFD5C3B5)
val md_theme_dark_outline = Color(0xFF9D8E81)
val md_theme_dark_inverseOnSurface = Color(0xFF201B16)
val md_theme_dark_inverseSurface = Color(0xFFEBE0D9)
val md_theme_dark_inversePrimary = Color(0xFF8A5100)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFFFFB86F)
val md_theme_dark_outlineVariant = Color(0xFF51453A)
val md_theme_dark_scrim = Color(0xFF000000)

private val lightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

private val darkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

//TODO: IMPLEMENT
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

@Composable
fun Theme(isDark: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    //val context = LocalContext.current
    //val lightColorScheme = dynamicLightColorScheme(context)
    //val darkColorScheme = dynamicDarkColorScheme(context)

    val colorScheme = if (isDark) darkColorScheme else lightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
        }
    }

    MaterialTheme(
        content = content,
        colorScheme = colorScheme,
        typography = Typography,
        //shapes = Shapes(
        //    small = RoundedCornerShape(10.dp),
        //    medium = RoundedCornerShape(10.dp),
        //    large = RoundedCornerShape(10.dp)
        //)
    )
}