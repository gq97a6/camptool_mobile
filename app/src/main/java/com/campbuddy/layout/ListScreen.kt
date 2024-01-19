@file:OptIn(ExperimentalFoundationApi::class)

package com.campbuddy.layout

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.campbuddy.compose.BottomDrawer
import com.campbuddy.compose.Theme
import com.campbuddy.`object`.Mockup
import com.campbuddy.performClick

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
//@Preview
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(navController: NavController?) {

    //val scope = rememberCoroutineScope()
    var states = remember { mutableStateListOf(*Array(100) { true }) }
    var filter by remember { mutableStateOf(2) }

    val drawerState = remember {
        AnchoredDraggableState(
            initialValue = 0,
            positionalThreshold = { it * 0.5f },
            velocityThreshold = { 0f },
            animationSpec = tween(),
        )
    }

    LazyColumn(
        Modifier
            .padding(horizontal = 15.dp)
            .fillMaxHeight()
    ) {
        itemsIndexed(items = Mockup.names) { i, name ->
            when (filter) {
                0 -> if (!states[i]) return@itemsIndexed
                1 -> if (states[i]) return@itemsIndexed
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 15.dp)
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
                Text(
                    text = name.replace(" ", "\n"),
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(
                        fontSize = 22.sp,
                        shadow = Shadow(
                            color = MaterialTheme.colorScheme.scrim, //TODO: CHECK COLOR
                            offset = Offset(1.0f, 2.0f),
                            blurRadius = 1f
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
                                topEnd = CornerSize(0),
                                bottomEnd = CornerSize(0)
                            )
                        )
                        .then(
                            if (states[i]) Modifier.background(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.shapes.large.copy(
                                    topEnd = CornerSize(0),
                                    bottomEnd = CornerSize(0)
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
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.shapes.large.copy(
                        bottomEnd = CornerSize(0),
                        bottomStart = CornerSize(0)
                    )
                )
                .alpha(.98f)
                .background(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.shapes.large.copy(
                        bottomEnd = CornerSize(0),
                        bottomStart = CornerSize(0)
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
                    text = "100 / 100",
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
                    lineHeight = 34.sp
                )
            }

            Column(modifier = Modifier.fillMaxSize()) {
                listOf(
                    "Pokaż jedynie obecnych",
                    "Pokaż jedynie nieobecnych",
                    "Pokaż wszystkich"
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
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Odznacz wszystkich")
                }
            }
        }
    })
}