package com.campbuddy.compose

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView

fun composeConstruct(
    context: Context,
    isDark: Boolean = true, //TODO: IMPLEMENT
    content: @Composable () -> Unit
) = ComposeView(context).apply { setContent { ComposeTheme(content) } }


