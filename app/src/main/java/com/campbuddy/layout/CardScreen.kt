package com.campbuddy.layout

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CardScreen(navController: NavController?) {
    Text(text = "card", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
}

//name
//events
//notes
//general info
//medical info
//contact info
//custom blocks