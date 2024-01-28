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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.campbuddy.R
import com.campbuddy.api.Endpoints
import com.campbuddy.api.Retrofit
import com.campbuddy.compose.Theme
import com.campbuddy.createToast
import kotlinx.coroutines.launch

@Composable
@Preview
fun LoginPreview() {
    Theme(true) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        LoginScreen(rememberNavController())
    }
}

@Composable
fun LoginScreen(navController: NavController) = Column(
    Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 40.dp)
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center
) {
    var login by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

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
        var isWorking by remember { mutableStateOf(false) }

        FilledTonalButton(
            onClick = {
                isWorking = true
                scope.launch {
                    val api = Retrofit.rf.create(Endpoints::class.java)
                    val result = api.login(mapOf("username" to login, "password" to pass)).code()

                    if (result == 200) navController.navigate("home")
                    else createToast(navController.context, "Nieprawidłowe dane logowania")
                    isWorking = false
                }
            },
            enabled = !isWorking,
        ) { Text(text = "Zaloguj się") }

        FilledTonalButton(onClick = {
            createToast(navController.context, "Opcja niedostępna")
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
