package com.camptool.`object`

import com.camptool.`object`.G.path
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import java.io.FileReader

object Storage {
    val mapper: ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .findAndRegisterModules()

    //Serialize object to string
    fun Any.prepareSave(): String = mapper.writeValueAsString(this)

    //Save serialized object to file
    fun Any.saveToFile(save: String = this.prepareSave()) {
        try {
            val path = path[this::class]
            File(path!!).writeText(save)
        } catch (_: Exception) {
        }
    }

    //Save serialized collection of objects to file
    inline fun <reified T> Collection<T>.saveToFile(save: String = this.prepareSave()) {
        try {
            val path = path[T::class]
            File(path!!).writeText(save)
        } catch (_: Exception) {
        }
    }

    //Get string from file
    inline fun <reified T> getSave() = try {
        FileReader(path[T::class]).readText()
    } catch (e: Exception) {
        ""
    }

    //Deserialize object from string
    inline fun <reified T> parseSave(save: String = getSave<T>()): T? =
        try {
            mapper.readValue(save, T::class.java)
        } catch (e: Exception) {
            null
        }

    //Deserialize collection of objects from string
    inline fun <reified T> parseListSave(save: String = getSave<T>()): MutableList<T> =
        try {
            mapper.readerForListOf(T::class.java).readValue(save)
        } catch (e: Exception) {
            mutableListOf()
            //try {
            //    mapper.readerForListOf(T::class.java).readValue(save.fixSave())
            //} catch (e: Exception) {
            //    mutableListOf()
            //}
        }
}