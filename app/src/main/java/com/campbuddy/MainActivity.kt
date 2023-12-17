package com.campbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campbuddy.compose.Theme
import com.campbuddy.`object`.Mockup

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
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("settings") { Settings(navController) }
        composable("day") { DayPlan(navController) }
        composable("planner") { Planner(navController) }
        composable("card") { Card(navController) }
        composable("list") { CheckList(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) = Column {

    // Remember a list that tracks the checked states of each checkbox
    val checkboxStates = remember { mutableStateListOf(*Array(100) { false }) }

    LazyColumn(M.padding(horizontal = 10.dp)) {
        itemsIndexed(items = Mockup.names) { i, name ->
            Row(
                M
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name)
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

    //val sheetState = rememberSheetState()
    //val scope = rememberCoroutineScope()
    //var showBottomSheet by remember { mutableStateOf(false) }

    //Button(onClick = { showBottomSheet = true }) {
    //    Text(text = "SHOW")
    //}

    //if (showBottomSheet) ModalBottomSheet(
    //    onDismissRequest = { showBottomSheet = false },
    //    sheetState = sheetState
    //) {
    //    Button(onClick = {
    //        scope.launch { sheetState.hide() }.invokeOnCompletion {
    //            if (!sheetState.isVisible) {
    //                showBottomSheet = false
    //            }
    //        }
    //    }) {
    //        Text("Hide bottom sheet")
    //    }
    //}
}

@Composable
fun LoginScreen(navController: NavController) {
    Text(text = "login", fontSize = 50.sp)
}

@Composable
fun Settings(navController: NavController) {
    Text(text = "settings", fontSize = 50.sp)
}

@Composable
fun DayPlan(navController: NavController) {
    Text(text = "day_plan", fontSize = 50.sp)
}

@Composable
fun Planner(navController: NavController) {
    Text(text = "planner", fontSize = 50.sp)
}

@Composable
fun Card(navController: NavController) {
    Text(text = "card", fontSize = 50.sp)
}

@Composable
fun CheckList(navController: NavController) {
    Text(text = "list", fontSize = 50.sp)
}