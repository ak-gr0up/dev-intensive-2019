package ru.skillbranch.devintensive.models

import java.util.*
class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String?
)
    : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String {
        val getOrSend: String =
            when(isIncoming) {
                true -> "получил"
                else -> {
                    "отправил" }}
        println("${from?.firstName} $getOrSend изображение $text $date")
        return "${from?.firstName} $getOrSend изображение $text $date"
    }
}