@file:OptIn(ExperimentalFoundationApi::class)

package com.campbuddy.layout

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.campbuddy.M
import com.campbuddy.compose.Theme
import com.campbuddy.`object`.Mockup
import com.campbuddy.performClick
import com.campbuddy.toDp
import com.campbuddy.toPx

enum class DragAnchors(val fraction: Float) {
    Start(0f),

    //Mid(.3f),
    End(.9f)
}

@Composable
@Preview
fun PreviewDark() {
    Theme(true) {
        Box(
            M
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        ListScreen(navController = null)
    }
}

@Composable
@Preview
fun PreviewLight() {
    Theme(false) {
        Box(
            M
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        ListScreen(navController = null)
    }
}

@Composable
fun ListScreen(navController: NavController?) {

    //val scope = rememberCoroutineScope()
    var states = remember { mutableStateListOf(*Array(100) { true }) }

    ListScreenBottomDrawer(size = 100.dp) {
        LazyColumn(
            M
                .padding(horizontal = 15.dp)
                .fillMaxHeight()
                .padding(bottom = 100.dp)
        ) {
            itemsIndexed(items = Mockup.names) { i, name ->
                Row(
                    modifier = M
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    //horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = M
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                MaterialTheme.colorScheme.secondaryContainer,
                                MaterialTheme.shapes.small
                            )
                            .padding(start = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = name.replace(" ", "\n"),
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontSize = 24.sp,
                            softWrap = true,
                            lineHeight = 34.sp,
                            modifier = M.fillMaxWidth()
                        )
                    }

                    Box(
                        Modifier
                            .padding(start = 15.dp)
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.outline,
                                MaterialTheme.shapes.small
                            )
                            .clickable {
                                states[i] = !states[i]
                                navController?.context?.apply { performClick(this) }
                            }
                    ) {
                        if (states[i]) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "checkbox",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = M.fillMaxSize().padding(10.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreenBottomDrawer(size: Dp = 100.dp, content: @Composable () -> Unit) {
    val sizePx = size.toPx()

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThreshold = { it * 0.5f },
            velocityThreshold = { 0f },
            animationSpec = tween(),
        )
    }

    Box(
        modifier = M
            .onSizeChanged { layoutSize ->
                val end = layoutSize.height - sizePx
                state.updateAnchors(DraggableAnchors {
                    DragAnchors
                        .values()
                        .forEach { anchor ->
                            anchor at end * anchor.fraction
                        }
                })
            }
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        content()

        Box(
            modifier = Modifier
                .height(size + state.offset.toDp())
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.medium)
                .anchoredDraggable(state, Orientation.Vertical, reverseDirection = true)
        ) {
            //Text(text = state.currentValue.ordinal.toString())
        }
    }
}