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

val primaryDark = Color(0xFFFFCB98)
val onPrimaryDark = Color(0xFF4A2800)
val primaryContainerDark = Color(0xFFF79400)
val onPrimaryContainerDark = Color(0xFF331B00)
val secondaryDark = Color(0xFFF6BB81)
val onSecondaryDark = Color(0xFF4A2800)
val secondaryContainerDark = Color(0xFF5B3505)
val onSecondaryContainerDark = Color(0xFFFFC790)
val tertiaryDark = Color(0xFFAFD44B)
val onTertiaryDark = Color(0xFF283500)
val tertiaryContainerDark = Color(0xFF627E00)
val onTertiaryContainerDark = Color(0xFFFFFFFF)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF1A120A)
val onBackgroundDark = Color(0xFFF1DFD1)
val surfaceDark = Color(0xFF1A120A)
val onSurfaceDark = Color(0xFFF1DFD1)
val surfaceVariantDark = Color(0xFF554434)
val onSurfaceVariantDark = Color(0xFFDBC2AD)
val outlineDark = Color(0xFFA38D7A)
val outlineVariantDark = Color(0xFF554434)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFF1DFD1)
val inverseOnSurfaceDark = Color(0xFF392E25)
val inversePrimaryDark = Color(0xFF8A5100)
val surfaceDimDark = Color(0xFF1A120A)
val surfaceBrightDark = Color(0xFF42372D)
val surfaceContainerLowestDark = Color(0xFF140D06)
val surfaceContainerLowDark = Color(0xFF231A11)
val surfaceContainerDark = Color(0xFF271E15)
val surfaceContainerHighDark = Color(0xFF32281F)
val surfaceContainerHighestDark = Color(0xFF3D3329)

val primaryLight = Color(0xFF8A5100)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFFFA743)
val onPrimaryContainerLight = Color(0xFF452600)
val secondaryLight = Color(0xFF815524)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFFFCB99)
val onSecondaryContainerLight = Color(0xFF5D3707)
val tertiaryLight = Color(0xFF4F6600)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFF88AA23)
val onTertiaryContainerLight = Color(0xFF0C1200)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFFFF8F5)
val onBackgroundLight = Color(0xFF231A11)
val surfaceLight = Color(0xFFFFF8F5)
val onSurfaceLight = Color(0xFF231A11)
val surfaceVariantLight = Color(0xFFF8DEC8)
val onSurfaceVariantLight = Color(0xFF554434)
val outlineLight = Color(0xFF887361)
val outlineVariantLight = Color(0xFFDBC2AD)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF392E25)
val inverseOnSurfaceLight = Color(0xFFFFEEE0)
val inversePrimaryLight = Color(0xFFFFB86F)
val surfaceDimLight = Color(0xFFE9D7C9)
val surfaceBrightLight = Color(0xFFFFF8F5)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFFFF1E7)
val surfaceContainerLight = Color(0xFFFDEBDC)
val surfaceContainerHighLight = Color(0xFFF7E5D7)
val surfaceContainerHighestLight = Color(0xFFF1DFD1)

private val lightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
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