package com.campbuddy.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.campbuddy.api.Endpoints
import com.campbuddy.api.Retrofit
import com.campbuddy.classes.Kid
import com.campbuddy.compose.FrameBox
import com.campbuddy.compose.Theme
import com.campbuddy.`object`.G.kid
import kotlinx.coroutines.launch

@Composable
@Preview
fun CardPreview() {
    kid = Kid(
        "06AC36A16B094A90A346AD22705AD74D",
        "Mikołaj Walczak",
        contact = mutableListOf(listOf("Alicja Walczak", "Opiekun", "742 728 959")),
        info = mutableListOf("alergia na orzechy", "cechy: energiczność"),
        history = mutableListOf("przewrócenie się"),
        needs = mutableListOf("test", "test2")
    )
    Theme(true) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        CardScreen(rememberNavController())
    }
}

//TODO: IMPLEMENT
@Composable
fun CardScreen(navController: NavController) = Column(
    Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 40.dp)
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center
) {
    val kid by remember { mutableStateOf(kid) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogAction by remember { mutableStateOf({}) }
    var dialogText by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Text(
        text = kid.name.split(" ").first(),
        fontSize = 35.sp,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.secondary
    )

    Text(
        text = kid.name.split(" ").last(),
        fontSize = 35.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.primary
    )

    Spacer(modifier = Modifier.height(10.dp))

    FrameBox(
        Modifier
            .fillMaxWidth(1f)
            .padding(15.dp), "Dane kontaktowe"
    ) {
        Column {
            kid.contact.forEach {
                Row {
                    Text(
                        text = "• ${it[0]}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .height(23.dp),
                        text = "(${it[1]})",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = it[2],
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )

                if (kid.contact.last() != it) Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    FrameBox(
        Modifier
            .fillMaxWidth(1f)
            .padding(15.dp), "Informacje dodatkowe"
    ) {
        Column {
            kid.info.forEach {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "• $it",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (kid.info.last() != it) Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    FrameBox(
        Modifier
            .fillMaxWidth(1f)
            .padding(15.dp), "Potrzeby"
    ) {
        Column {
            kid.needs.forEach {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "• $it",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (kid.needs.last() != it) Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Row(Modifier.align(Alignment.CenterEnd), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.Add,
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    showDialog = true
                    dialogTitle = "Dodaj nową potrzebę"
                    dialogAction = {
                        showDialog = false
                        if (dialogText.isNotBlank()) kid.needs.add(dialogText)
                        scope.launch {
                            val api = Retrofit.rf.create(Endpoints::class.java)
                            api.upsertKid(kid, kid.uuid)
                        }
                        dialogText = ""
                    }
                },
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    FrameBox(
        Modifier
            .fillMaxWidth(1f)
            .padding(15.dp), "Zdarzenia"
    ) {
        Column {
            kid.history.forEach {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "• $it",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (kid.history.last() != it) Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Row(Modifier.align(Alignment.CenterEnd), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.Add,
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    showDialog = true
                    dialogTitle = "Dodaj nowe zdarznie"
                    dialogAction = {
                        showDialog = false
                        if (dialogText.isNotBlank()) kid.history.add(dialogText)
                        scope.launch {
                            val api = Retrofit.rf.create(Endpoints::class.java)
                            api.upsertKid(kid, kid.uuid)
                        }
                        dialogText = ""
                    }
                },
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    FrameBox(
        Modifier
            .fillMaxWidth(1f)
            .padding(15.dp), "Notatki"
    ) {
        Column {
            kid.notes.forEach {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "• $it",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (kid.notes.last() != it) Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Row(Modifier.align(Alignment.CenterEnd), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.Add,
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    showDialog = true
                    dialogTitle = "Dodaj nową notatkę"
                    dialogAction = {
                        showDialog = false
                        if (dialogText.isNotBlank()) kid.notes.add(dialogText)
                        scope.launch {
                            val api = Retrofit.rf.create(Endpoints::class.java)
                            api.upsertKid(kid, kid.uuid)
                        }
                        dialogText = ""
                    }
                },
            )
        }
    }

    if (showDialog) AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 10.dp),
                value = dialogText,
                label = { Text(text = "Tekst") },
                singleLine = true,
                onValueChange = { dialogText = it }
            )
        },
        onDismissRequest = {
            showDialog = false
        },
        confirmButton = {
            FilledTonalButton(onClick = {
                dialogAction()
            }) {
                Text("Potwierdź")
            }
        },
        dismissButton = {
            FilledTonalButton(onClick = {
                showDialog = false
                dialogText = ""
            }) {
                Text("Anuluj")
            }
        }
    )
}