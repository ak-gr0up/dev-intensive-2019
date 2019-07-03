package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

enum class TimeUnits{SECOND, MINUTE, HOUR, DAY}

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String="HH:mm:ss dd.MM.yy") : String{
    val dateFromat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFromat.format(this)
}
fun Date.add(value:Int, units:TimeUnits): Date{
    var time = this.time
    time +=when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}

fun Sign(value: Long) : Int{
    if (value > 0)
        return 1
    else
        return -1

}


fun Date.humanizeDiff(dt: Date? = null): String {
    val real_date: Date = Date()
    var date: Date
    if (dt == null)
        date = this
    else
        date = dt
    var diff : Long = (real_date.getTime() - date.getTime()) / 1000
    var _prev: String = ""
    var _future: String = ""
    var minutes: String = "минут"
    var hours: String = "часов"
    var days: String = "дней"
    if (diff > 1)
        _prev = " назад"
    else if (diff < 1)
        _future = "через "

    if (Math.abs(diff) < 2)
        return "только что"
    else if (Math.abs(diff) <= 45)
        return "${_future}несколько секунд$_prev"
    else if (Math.abs(diff) <= 75)
        return "${_future}минуту$_prev"
    else if (Math.abs(diff) <= 2700){
        val left = Math.abs(diff) % 60
        diff = diff - Sign(diff) * left + (if (left > 45) Sign(diff) * 60 else 0)
        if((Math.abs(diff) / 60) % 10 == 1.toLong() && (Math.abs(diff) / 600) % 10 != 1.toLong())
            minutes = "минуту"
        else if ((Math.abs(diff) / 60) % 10 == 2.toLong() || (Math.abs(diff) / 60) % 10 == 3.toLong() || (Math.abs(diff) / 60) % 10 == 4.toLong())
            minutes = "минуты"
        return "${_future}${Math.abs(diff) / 60} $minutes$_prev"}
    else if (Math.abs(diff) <= 4500)
        return "${_future}час$_prev"
    else if (Math.abs(diff) <= 79200){
        val left = Math.abs(diff) % 3600
        diff = diff - Sign(diff) * left + (if (left > 3585) Sign(diff) * 3600 else 0)
        if((Math.abs(diff) / 3600) % 10 == 1.toLong() && (Math.abs(diff) / 36000) % 10 != 1.toLong())
            hours = "час"
        else if ((Math.abs(diff) / 3600) % 10 == 2.toLong() || (Math.abs(diff) / 3600) % 10 == 3.toLong() || (Math.abs(diff) / 3600) % 10 == 4.toLong())
            hours = "часа"
        return "$_future${Math.abs(diff) / 3600} $hours$_prev"}
    else if (Math.abs(diff) <= 93600)
        return "${_future}день$_prev"
    else if (Math.abs(diff) <= 31104000){
        val left = Math.abs(diff) % 86400
        diff = diff - Sign(diff) * left + (if (left > 86385) Sign(diff) * 86400 else 0)
        if((Math.abs(diff) / 86400) % 10 == 1.toLong() && (Math.abs(diff) / 864000) % 10 != 1.toLong())
            days = "день"
        else if ((Math.abs(diff) / 86400) % 10 == 2.toLong() || (Math.abs(diff) / 86400) % 10 == 3.toLong() || (Math.abs(diff) / 86400) % 10 == 4.toLong())
            days = "дня"
        return "$_future${Math.abs(diff) / 86400} $days$_prev"}
    else
        if (diff < 0)
            return "более чем через год"
        else
            return "более года назад"
}


fun TimeUnits.plural(value: Int): String {
    val unit: String
    if (this == TimeUnits.SECOND) {
        if ((value % 10 == 1 && value % 100 != 1) || (value == 1)){
            unit = "секунду"}
        else if (value % 10 == 2 || value % 10 == 3 || value % 10 == 4){
            unit = "секунды"}
        else{
            unit = "секунд"}
    }
    else if (this == TimeUnits.MINUTE){
        if (value % 10 == 1 && value % 100 != 1){
            unit = "минуту"}
        else if (value % 10 == 2 || value % 10 == 3 || value % 10 == 4){
            unit = "минуты"}
        else{
            unit = "минут"}
    }
    else if (this == TimeUnits.HOUR){
        if (value % 10 == 1 && value % 100 != 1){
            unit = "час"}
        else if (value % 10 == 2 || value % 10 == 3 || value % 10 == 4){
            unit = "часа"}
        else{
            unit = "часов"}
    }
    else {
        if (value % 10 == 1 && value % 100 != 1){
            unit = "день"}
        else if (value % 10 == 2 || value % 10 == 3 || value % 10 == 4){
            unit = "дня"}
        else{
            unit = "дней"}
    }
    return "$value $unit"

}