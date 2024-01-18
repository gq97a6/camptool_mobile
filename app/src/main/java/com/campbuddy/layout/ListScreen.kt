@file:OptIn(ExperimentalFoundationApi::class)

package com.campbuddy.layout

import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.widget.FrameLayout
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.campbuddy.compose.BottomDrawer
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
            Modifier
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
            Modifier
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

    LazyColumn(
        Modifier
            .padding(horizontal = 15.dp)
            .fillMaxHeight()
    ) {
        itemsIndexed(items = Mockup.names) { i, name ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.shapes.small
                        )
                        .padding(start = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = name.replace(" ", "\n"),
                        color = MaterialTheme.colorScheme.primary,
                        style = TextStyle(
                            fontSize = 44.sp,
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.scrim, //TODO: CHECK COLOR
                                offset = Offset(1.0f, 2.0f),
                                blurRadius = 1f
                            )
                        ),
                        fontSize = 24.sp,
                        softWrap = true,
                        lineHeight = 34.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Box(
                    Modifier
                        .padding(start = 15.dp)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.primary,
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
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(115.dp)) }
    }

    BottomDrawer(100.dp, 2.dp) {
        Column(Modifier.fillMaxSize().padding(15.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                ListScreenDrawerContentSmall(states.size, states.count { it })
            }

            Box(modifier = Modifier.fillMaxSize()) {
                //ListScreenDrawerContentBig(states)
            }
        }
    }
}

@Composable
fun ListScreenDrawerContentSmall(all: Int, checked: Int) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "$checked / $all",
            color = MaterialTheme.colorScheme.primary,
            style = TextStyle(
                fontSize = 44.sp,
                shadow = Shadow(
                    color = MaterialTheme.colorScheme.scrim, //TODO: CHECK COLOR
                    offset = Offset(1.0f, 2.0f),
                    blurRadius = 1f
                )
            ),
            textAlign = TextAlign.Start,
            fontSize = 35.sp,
            softWrap = true,
            lineHeight = 34.sp,
            modifier = Modifier.fillMaxWidth()
        )

    }
}

@Composable
fun ListScreenDrawerContentBig(states: SnapshotStateList<Boolean>) {
    Column(Modifier.fillMaxSize()) {
        Mockup.names.forEachIndexed { i, name ->
            if (states[i]) {
                Text(
                    text = "$i. $name",
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        fontSize = 44.sp,
                        shadow = Shadow(
                            color = MaterialTheme.colorScheme.scrim, //TODO: CHECK COLOR
                            offset = Offset(1.0f, 2.0f),
                            blurRadius = 1f
                        )
                    ),
                    textAlign = TextAlign.Start,
                    fontSize = 25.sp,
                    softWrap = true,
                    lineHeight = 34.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}