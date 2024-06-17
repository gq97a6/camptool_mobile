package com.camptool.classes

data class Kid(
    var uuid: String = "",
    val name: String = "",
    val contact: List<List<String>> = listOf(),
    val info: MutableList<String> = mutableListOf(),
    var needs: MutableList<String> = mutableListOf(),
    var history: MutableList<String> = mutableListOf(),
    var notes: MutableList<String> = mutableListOf()
)