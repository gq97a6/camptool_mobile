@file:OptIn(ExperimentalFoundationApi::class)

package com.camptool.layout

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.camptool.api.Endpoints
import com.camptool.api.Retrofit
import com.camptool.classes.Kid
import com.camptool.compose.BottomDrawer
import com.camptool.compose.Theme
import com.camptool.`object`.G
import com.camptool.performClick
import com.camptool.toDp
import com.camptool.toPx

@Composable
@Preview
fun PreviewDark() {
    Theme(true) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        ListScreen(rememberNavController())
    }
}

@Composable
fun PreviewLight() {
    Theme(false) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        ListScreen(rememberNavController())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(navController: NavController) {

    var kids by remember { mutableStateOf(listOf<Kid>()) }
    var filter by remember { mutableStateOf(2) }
    var states = remember { List(50) { true }.toMutableStateList() }
    val anchors = remember { listOf(0.dp, 100.dp) }
    val anchorsPx = anchors.map { it.toPx() }

    var dragStates = remember {
        List(50) {
            AnchoredDraggableState(
                initialValue = anchors.first(),
                positionalThreshold = { it * 0.9f },
                velocityThreshold = { Float.MAX_VALUE },
                animationSpec = tween(),
            )
        }.toMutableStateList()
    }

    remember {
        dragStates.forEach {
            it.updateAnchors(DraggableAnchors {
                anchors.forEachIndexed { i, anchor ->
                    anchor at anchorsPx[i]
                }
            })
        }
    }

    LaunchedEffect(Unit) {
        val api = Retrofit.rf.create(Endpoints::class.java)
        kids = api.getAllKids().body() ?: listOf()
    }

    LazyColumn(
        Modifier
            .padding(horizontal = 15.dp)
            .fillMaxHeight()
    ) {
        itemsIndexed(items = kids) { i, kid ->
            when (filter) {
                0 -> if (!states[i]) return@itemsIndexed
                1 -> if (states[i]) return@itemsIndexed
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 15.dp)
                .offset(x = dragStates[i].offset.toDp())
                .anchoredDraggable(dragStates[i], Orientation.Horizontal, reverseDirection = false)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.shapes.small
                )
                .pointerInput(Unit){
                    detectTapGestures(
                        onTap = {
                            states[i] = !states[i]
                            navController.context.apply { performClick(this) }
                        },
                        onLongPress = {
                            G.kid = kid
                            navController.navigate("card")
                        }
                    )
                }) {
                Text(
                    text = kid.name.replace(" ", "\n"),
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        fontSize = 22.sp, shadow = Shadow(
                            color = MaterialTheme.colorScheme.scrim, //TODO: CHECK COLOR
                            offset = Offset(1.0f, 2.0f), blurRadius = 1f
                        )
                    ),
                    softWrap = true,
                    lineHeight = 34.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                        .padding(start = 30.dp)
                )

                Box(
                    Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxHeight()
                        .width(20.dp)
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.shapes.large.copy(
                                topEnd = CornerSize(0), bottomEnd = CornerSize(0)
                            )
                        )
                        .then(
                            if (states[i]) Modifier.background(
                                MaterialTheme.colorScheme.primary, MaterialTheme.shapes.large.copy(
                                    topEnd = CornerSize(0), bottomEnd = CornerSize(0)
                                )
                            ) else Modifier
                        )
                )
            }
        }

        item { Spacer(modifier = Modifier.height(115.dp)) }
    }

    //DRAWER ---------------------------------------------------------------------------------------

    BottomDrawer(listOf(220.dp, 0.dp), {
        Column(
            Modifier
                .fillMaxWidth()
                .height(320.dp)
                .offset(y = 2.dp)
                .border(
                    2.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.large.copy(
                        bottomEnd = CornerSize(0), bottomStart = CornerSize(0)
                    )
                )
                .alpha(.98f)
                .background(
                    MaterialTheme.colorScheme.background, MaterialTheme.shapes.large.copy(
                        bottomEnd = CornerSize(0), bottomStart = CornerSize(0)
                    )
                )
                .padding(horizontal = 15.dp)
        ) {
            Box(
                Modifier
                    .height(90.dp)
                    .padding(top = 10.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "${states.filter { it }.size} / ${states.size}",
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        fontSize = 44.sp, shadow = Shadow(
                            color = MaterialTheme.colorScheme.scrim, //TODO: CHECK COLOR
                            offset = Offset(1.0f, 2.0f), blurRadius = 1f
                        )
                    ),
                    textAlign = TextAlign.Start,
                    fontSize = 35.sp,
                    softWrap = true,
                    lineHeight = 34.sp
                )
            }

            Column(modifier = Modifier.fillMaxSize()) {
                listOf(
                    "Pokaż jedynie obecnych", "Pokaż jedynie nieobecnych", "Pokaż wszystkich"
                ).forEachIndexed { index, s ->
                    FilterChip(
                        selected = filter == index,
                        onClick = { filter = index },
                        label = { Text(s) },
                        leadingIcon = {
                            if (filter == index) Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        },
                    )
                }

                OutlinedButton(
                    onClick = {
                        repeat(states.size) { states[it] = false }
                        filter = 2
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Odznacz wszystkich")
                }
            }
        }
    })
}