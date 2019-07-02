package ru.skillbranch.devintensive.models
import java.util.*
class ImageMessage(
    id: String?,
    from: User?,
    chat: Chat?,
    isIncoming: Boolean? = false,
    date: Date? = Date(),
    var image: String?
)
    : BaseMessage(id, from, chat, isIncoming, date) {
        override fun formatMessage(): String{
            val getOrSend: String =
                when(isIncoming) {
                    true -> "получил"
                    else -> {
                        "отправил" }}
            println("${from?.firstName} $getOrSend изображение $image $date")
            return "${from?.firstName} $getOrSend изображение $image $date"
        }

}