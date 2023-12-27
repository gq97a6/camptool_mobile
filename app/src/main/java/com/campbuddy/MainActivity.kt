package com.campbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campbuddy.compose.Theme
import com.campbuddy.`object`.Mockup
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun NavigationDrawer(navController: NavHostController, content: @Composable () -> Unit) {
    val locations = listOf(
        "home",
        "login",
        "settings",
        "day",
        "planner",
        "card",
        "list",
    )

    val icons = listOf(
        Icons.Outlined.Home,
        Icons.AutoMirrored.Outlined.Login,
        Icons.Outlined.Settings,
        Icons.AutoMirrored.Outlined.ListAlt,
        Icons.AutoMirrored.Outlined.Assignment,
        Icons.Outlined.AccountBox,
        Icons.Outlined.ContactPage
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(content = content, drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(M.width(100.dp)) {
            repeat(locations.size) {
                IconButton(modifier = M
                    .fillMaxWidth()
                    .aspectRatio(1f), onClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(locations[it])
                    navController.clearBackStack(locations[it])
                }) {
                    Icon(
                        modifier = M.fillMaxSize(.5f),
                        imageVector = icons[it],
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    })
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavigationDrawer(navController) {
        NavHost(navController = navController, startDestination = "list") {
            composable("home") { HomeScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("settings") { Settings(navController) }
            composable("day") { DayPlan(navController) }
            composable("planner") { Planner(navController) }
            composable("card") { Card(navController) }
            composable("list") { CheckList(navController) }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Text(text = "home", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun LoginScreen(navController: NavController) {
    Text(text = "login", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun Settings(navController: NavController) {
    Text(text = "settings", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun DayPlan(navController: NavController) {
    Text(text = "day_plan", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun Planner(navController: NavController) {
    Text(text = "planner", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun Card(navController: NavController) {
    Text(text = "card", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckList(navController: NavController) = Column {

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var sheetStatus by remember { mutableStateOf(true) }
    val checkboxStates = remember { mutableStateListOf(*Array(100) { false }) }


    HorizontalDraggableSample {
        LazyColumn(M.padding(horizontal = 10.dp).fillMaxHeight().padding(bottom = 100.dp)) {
            itemsIndexed(items = Mockup.names) { i, name ->
                Row(
                    M.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = name,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = M.fillMaxWidth(.7f)
                    )
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.AccountBox,
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Checkbox(
                        checked = checkboxStates[i],
                        onCheckedChange = { checked ->
                            checkboxStates[i] = checked
                        }
                    )
                }
            }
        }
    }
}

enum class DragAnchors(val fraction: Float) {
    Start(0f),
    //Half(.5f),
    End(.9f),
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalDraggableSample(content: @Composable () -> Unit) {
    val size = 100.dp.toPx()

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
                val end = layoutSize.height - size
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
                .height(100.dp + state.offset.toDp())
                .fillMaxWidth()
                //.offset { IntOffset(x = 0, y = state.offset.roundToInt()) }
                .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.medium)
                .anchoredDraggable(state, Orientation.Vertical, reverseDirection = true)
        )
    }
}