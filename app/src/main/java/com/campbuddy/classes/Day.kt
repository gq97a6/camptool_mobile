package com.campbuddy.classes

import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlin.random.Random

data class Day(
    var uuid: String = "",
    val date: LocalDate = LocalDate.MAX,
    val events: List<DayEvent> = listOf()
)

data class DayEvent(
    val time: LocalTime = LocalTime.of(0, 0),
    val des: String = "",
    val group: String = ""
)