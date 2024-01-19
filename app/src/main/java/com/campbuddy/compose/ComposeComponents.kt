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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
        BottomDrawer {
            Box(modifier = Modifier.fillMaxWidth().height(500.dp).background(Color.Black))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomDrawer(
    anchors: List<Dp> = listOf(400.dp, 0.dp),
    content: @Composable (state: AnchoredDraggableState<*>) -> Unit = {}
) {
    val state = remember {
        AnchoredDraggableState(
            initialValue = anchors.first(),
            positionalThreshold = { it * 0.5f },
            velocityThreshold = { 0f },
            animationSpec = tween(),
        )
    }

    val anchorsPx = anchors.map { it.toPx() }

    state.updateAnchors(DraggableAnchors {
        anchors.forEachIndexed { i, anchor ->
            anchor at anchorsPx[i]
        }
    })

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .offset(y = state.offset.toDp())
                .fillMaxWidth()
                .anchoredDraggable(state, Orientation.Vertical, reverseDirection = false),
        ) {
            content(state)
        }
    }
}