package com.camptool.compose

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camptool.toDp
import com.camptool.toPx

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

        FrameBox(
            Modifier
                .fillMaxWidth(.7f)
                .padding(10.dp), "Dane kontaktowe") {
            Text(
                text = "Nowak",
                fontSize = 35.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}



@SuppressLint("RememberReturnType")
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

    remember {
        state.updateAnchors(DraggableAnchors {
            anchors.forEachIndexed { i, anchor ->
                anchor at anchorsPx[i]
            }
        })
    }

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

@Composable
fun FrameBox(modifier: Modifier, label: String, content: @Composable BoxScope.() -> Unit) {
    Column {
        Text(
            modifier = Modifier
                .height(20.dp)
                .padding(start = 3.dp),
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.tertiary
        )
        Box(
            Modifier
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.shapes.medium
                )
                .then(modifier)
        ) {
            content()
        }
    }
}