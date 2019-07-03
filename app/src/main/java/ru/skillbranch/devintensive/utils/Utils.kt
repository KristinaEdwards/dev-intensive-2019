package ru.skillbranch.devintensive.utils

import android.service.voice.AlwaysOnHotwordDetector
import android.service.voice.AlwaysOnHotwordDetector.EventPayload
import java.util.*

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divvider:String = ""): String {
            TODO("not implemented")
        }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO("not implemented")

    }

}