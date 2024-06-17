package com.camptool.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.camptool.api.Endpoints
import com.camptool.api.Retrofit
import com.camptool.classes.Day
import com.camptool.classes.DayEvent
import com.camptool.compose.Theme

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

//TODO: IMPLEMENT
@Composable
fun PlanScreen(navController: NavController) = Column(
    Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 40.dp)
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center
) {
    var days by remember { mutableStateOf(listOf<Day>()) }
    var index by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        val api = Retrofit.rf.create(Endpoints::class.java)
        days = api.getAllDays().body()?.sortedBy { it.date } ?: listOf()
    }

    Text(
        text = "Plan dnia",
        fontSize = 40.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.primary
    )

    if (days.isNotEmpty()) {
        val dayIndex = days[index].date.dayOfMonth - 9
        Text(
            text = "${days[index].date.dayOfMonth} Lipiec - dzień $dayIndex/10",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.tertiary
        )

        Spacer(modifier = Modifier.height(30.dp))

        days[index].events.groupBy { it.time }.forEach {
            val hour = it.key.hour.toString()
            val minute = it.key.minute.toString().padStart(2, '0')
            val time = "$hour:$minute"

            PlanRow(time, it.value)
            HorizontalDivider()
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
        if (index != 0) OutlinedButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {
                index -= 1
            }
        ) {
            Icon(
                Icons.Filled.ChevronLeft,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(35.dp)
            )
        }

        if (index != days.lastIndex) OutlinedButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {
                index += 1
            }
        ) {
            Icon(
                Icons.Filled.ChevronRight,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}

@Composable
fun PlanRow(time: String, events: List<DayEvent>) {

    Row(Modifier.height(40.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.width(65.dp),
            text = time,
            textAlign = TextAlign.End,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        events.forEach { PlanRowItem(it) }
    }
}

@Composable
fun PlanRowItem(event: DayEvent) = Row(
    Modifier
        .fillMaxHeight()
        .defaultMinSize(minWidth = 100.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    VerticalDivider(Modifier.padding(horizontal = 8.dp))
    Text(
        modifier = Modifier,
        text = event.des,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.primary
    )

    if (event.group.isNotBlank()) Text(
        modifier = Modifier.padding(start = 5.dp),
        text = event.group,
        fontSize = 15.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.tertiary
    )
}