package com.campbuddy.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.campbuddy.classes.Event
import com.campbuddy.compose.Theme
import com.campbuddy.`object`.Mockup.events

@Composable
@Preview
fun PlanPreview() {
    Theme(true) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        PlanScreen(rememberNavController())
    }
}

@Composable
fun PlanScreen(navController: NavController) = Column(
    Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 40.dp)
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center
) {
    Text(
        text = "Plan dnia",
        fontSize = 40.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.primary
    )
    Text(
        text = "20 stycznia - dzień 6/10",
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.tertiary
    )

    Spacer(modifier = Modifier.height(30.dp))

    events.forEach {
        PlanRow(hour = it.key, it.value)
        HorizontalDivider()
    }
}

@Composable
fun PlanRow(hour: String, events: List<Event>) {
    Row(Modifier.height(40.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.width(65.dp),
            text = hour,
            textAlign = TextAlign.End,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        events.forEach { PlanRowItem(it) }
    }
}

@Composable
fun PlanRowItem(event: Event) = Row(
    Modifier
        .fillMaxHeight()
        .defaultMinSize(minWidth = 100.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    VerticalDivider(Modifier.padding(horizontal = 8.dp))
    Text(
        modifier = Modifier,
        text = event.name,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.primary
    )

    if (event.group != null) Text(
        modifier = Modifier.padding(start = 5.dp),
        text = event.group,
        fontSize = 15.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.tertiary
    )
}