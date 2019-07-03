package ru.skillbranch.devintensive.extentions

fun String.truncate(length: Int = 16): String {
    val trimmedString = this.trim()

    return if (trimmedString.length <= length) {
        trimmedString
    } else {
        trimmedString.take(length).trimEnd() + "..."
    }
}

fun String.stripHtml(): String =
    this.replace("<[^>]*>".toRegex(), "")
        .replace("&\\D+;".toRegex(), " ")
        .replace("\\s{2,}".toRegex(), " ")