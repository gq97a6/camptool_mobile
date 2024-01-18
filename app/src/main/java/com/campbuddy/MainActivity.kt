package com.campbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campbuddy.compose.Theme
import com.campbuddy.layout.CardScreen
import com.campbuddy.layout.ListScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Theme {
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                    AppNavigation()
                }
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
        ModalDrawerSheet(Modifier.width(100.dp)) {
            repeat(locations.size) {
                IconButton(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f), onClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(locations[it])
                    navController.clearBackStack(locations[it])
                }) {
                    Icon(
                        modifier = Modifier.fillMaxSize(.5f),
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
        NavHost(
            navController = navController,
            startDestination = "list",
            exitTransition = { ExitTransition.None },
            enterTransition = { EnterTransition.None }
        ) {
            composable("home") { HomeScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("settings") { Settings(navController) }
            composable("day") { DayPlan(navController) }
            composable("planner") { Planner(navController) }
            composable("card") { CardScreen(navController) }
            composable("list") { ListScreen(navController) }
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