package com.campbuddy.layout

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.campbuddy.compose.FrameBox
import com.campbuddy.compose.Theme

@Composable
@Preview
fun CardPreview() {
    Theme(true) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        CardScreen(rememberNavController())
    }
}

@Composable
fun CardScreen(navController: NavController) = Column(
    Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 40.dp)
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center
) {
    Text(
        text = "Jakub Sebastian",
        fontSize = 35.sp,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.secondary
    )

    Text(
        text = "Nowak",
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
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "• Kamil Nowak",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .height(23.dp),
                    text = "(Tata)",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "591 491 321",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "• Aneta Nowak",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .height(23.dp),
                    text = "(Mama)",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "691 492 518",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
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
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "• alergia na APAP",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "• nosi okulary",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    FrameBox(
        Modifier
            .fillMaxWidth(1f)
            .padding(15.dp), "Dane kontaktowe"
    ) {
        Column {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "• podać leki",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "• zmienić opatrunek",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    FrameBox(
        Modifier
            .fillMaxWidth(1f)
            .height(50.dp), "Zdarzenia"
    ) {
        Row(Modifier.align(Alignment.CenterEnd), verticalAlignment = Alignment.CenterVertically) {
            VerticalDivider(color = MaterialTheme.colorScheme.primary)
            Icon(
                Icons.Filled.Add,
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(5.dp),
            )
        }
    }

    Spacer(modifier = Modifier.height(10.dp))

    FrameBox(
        Modifier
            .fillMaxWidth(1f)
            .height(50.dp), "Notatki"
    ) {
        Row(Modifier.align(Alignment.CenterEnd), verticalAlignment = Alignment.CenterVertically) {
            VerticalDivider(color = MaterialTheme.colorScheme.primary)
            Icon(
                Icons.Filled.Add,
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(5.dp),
            )
        }
    }
}