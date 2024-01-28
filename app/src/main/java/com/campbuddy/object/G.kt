package com.campbuddy.`object`

import com.campbuddy.classes.Kid
import kotlin.reflect.KClass

object G {
    //Path to root folder
    var rootFolder: String = ""

    //Map of paths to serialized objects
    lateinit var path: Map<KClass<out Any>, String>

    lateinit var kid: Kid
}

