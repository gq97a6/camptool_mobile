package com.campbuddy.classes

data class Event(
    val hour: String,
    val name: String,
    val group: String? = null
)