package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {

    return if (this.length <= length) {
        this
    } else {
        this.take(length).trimEnd() + "..."
    }
}

fun String.stripHtml(): String =
    this.replace("<[^>]*>".toRegex(), "")
        .replace("&\\D+;".toRegex(), " ")
        .replace("\\s{2,}".toRegex(), " ")