package com.campbuddy.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.campbuddy.R
import com.campbuddy.compose.Theme

@Composable
@Preview
fun LoginPreview() {
    Theme(true) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        LoginScreen(navController = null)
    }
}

@Composable
fun LoginScreen(navController: NavController?) = Column(
    Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 40.dp)
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center
) {
    var login by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Text(
        text = "Camp",
        fontSize = 65.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.primary
    )
    Text(
        text = "Buddy",
        fontSize = 65.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.tertiary
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.75f),
        value = login,
        singleLine = true,
        label = { Text(text = "Login") },
        onValueChange = { login = it }
    )

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 10.dp),
        value = pass,
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(text = "Hasło") },
        onValueChange = { pass = it }
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FilledTonalButton(onClick = {

        }) { Text(text = "Zaloguj się") }

        FilledTonalButton(onClick = {

        }) {
            Text(text = "Użyj klucza")
            Icon(
                painterResource(R.drawable.passkey),
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 5.dp),
            )
        }
    }
}
