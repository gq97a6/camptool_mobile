package com.campbuddy.compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

//TODO: IMPLEMENT
val DarkColorScheme: ColorScheme
    get() = darkColorScheme(
        primary = Color.White,
        secondary = Color.White,
        background = Color.Black,
        surface = Color.Transparent,
        onPrimary = Color.White,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White
    )

//TODO: IMPLEMENT
val LightColorScheme: ColorScheme
    get() = lightColorScheme(
        primary = Color.Black,
        secondary = Color.Black,
        background = Color.White,
        surface = Color.Transparent,
        onPrimary = Color.Black,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black
    )

var ColorScheme: ColorScheme = DarkColorScheme

@Composable
fun ComposeTheme(content: @Composable () -> Unit) = MaterialTheme(
    colorScheme = ColorScheme,
    typography = Typography,
    shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(4.dp)
    )
) {
    CompositionLocalProvider(
        LocalRippleTheme provides object : RippleTheme {
            @Composable
            override fun defaultColor(): Color = Color.Black

            @Composable
            override fun rippleAlpha(): RippleAlpha = RippleAlpha(.2f, .2f, .1f, 1f)
            //RippleTheme.defaultRippleAlpha(androidx.compose.ui.graphics.Color.Black, lightTheme = !isSystemInDarkTheme())
        },
        content = content
    )
}