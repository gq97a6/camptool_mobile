package com.camptool.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.camptool.compose.Theme

@Composable
@Preview
fun HomePreview() {
    Theme(true) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        HomeScreen(rememberNavController())
    }
}

//TODO: IMPLEMENT
@Composable
fun HomeScreen(navController: NavController) = Column(
    Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 40.dp)
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center
) {
    Text(text = "home", fontSize = 50.sp, color = MaterialTheme.colorScheme.onBackground)
}