package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extentions.humanizeDiff
import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncomihg: Boolean = false,
    date: Date = Date(),
    var text: String?
) : BaseMessage(id, from, chat, isIncomihg, date) {
    override fun formatMassage(): String = "id:$id ${from?.firstName}" +
                " ${if (isIncomihg) "получил" else "отправил"} сообщение \"$text\" ${date.humanizeDiff()}"
}
