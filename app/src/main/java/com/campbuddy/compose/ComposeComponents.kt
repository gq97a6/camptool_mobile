package com.campbuddy.compose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.campbuddy.layout.DragAnchors
import com.campbuddy.layout.ListScreen
import com.campbuddy.toDp
import com.campbuddy.toPx

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun PreviewDark() {
    Theme(true) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        BottomDrawer()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomDrawer(
    height: Dp = 100.dp,
    border: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    alpha: Float = .98f,
    shape: Shape = MaterialTheme.shapes.large.copy(
        bottomEnd = CornerSize(0),
        bottomStart = CornerSize(0)
    ),
    content: @Composable (state: AnchoredDraggableState<DragAnchors>) -> Unit = {}
) {
    val sizePx = height.toPx()

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThreshold = { it * 0.5f },
            velocityThreshold = { 0f },
            animationSpec = tween(),
        )
    }

    Box(
        modifier = Modifier
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
        Box(
            modifier = Modifier
                .height(height + state.offset.toDp())
                .fillMaxWidth()
                .offset(y = border)
                .border(border, borderColor, shape)
                .alpha(alpha)
                .background(backgroundColor, shape)
                .anchoredDraggable(state, Orientation.Vertical, reverseDirection = true),
        ) {
            content(state)
        }
    }
}