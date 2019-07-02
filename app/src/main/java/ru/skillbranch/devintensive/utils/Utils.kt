package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(userName: String?): Pair<String?, String?>{
        if (userName == "" || userName == null)
            return Pair(null, null)
        val name: List<String>? = userName.split(" ")
        val firstName = name?.getOrNull(0)
        val lastName = name?.getOrNull(1)


        if ((firstName == "" && lastName == "") or (firstName == " " && lastName == " ") or (userName == ""))
            return Pair(null, null)
        else if (lastName == "")
            return Pair(firstName, null)
        else if (lastName == " ")
            return Pair(firstName, null)
        else
            return Pair(firstName, lastName)

    }

    fun toInitials(firstName: String?, lastName: String?): String?{
        var first_char: Char? = firstName?.getOrNull(0)
        var second_char: Char? = lastName?.getOrNull(0)
        if (first_char == ' ')
            first_char = null
        else if(second_char == ' ')
            second_char = null

        if(first_char == null && second_char == null)
            return null
        else if(second_char == null)
            return "${first_char!!.toUpperCase()}"
        else if(first_char == null)
            return "${second_char!!.toUpperCase()}"
        else
            return "${first_char!!.toUpperCase()}${second_char!!.toUpperCase()}"


        }

    fun transliteration(payload: String?, divider: String? = " "): String{
        var result:String = ""
        val string_length: Int = payload!!.length - 1
        for(i in 0..string_length){
            when(payload[i].toString()){
                "а" -> result += "a"
                "б" -> result += "b"
                "в" -> result += "v"
                "г" -> result += "g"
                "д" -> result += "d"
                "е" -> result += "e"
                "ё" -> result += "e"
                "ж" -> result += "zh"
                "з" -> result += "z"
                "и" -> result += "i"
                "й" -> result += "i"
                "к" -> result += "k"
                "л" -> result += "l"
                "м" -> result += "m"
                "н" -> result += "n"
                "о" -> result += "o"
                "п" -> result += "p"
                "р" -> result += "r"
                "с" -> result += "s"
                "т" -> result += "t"
                "у" -> result += "u"
                "ф" -> result += "f"
                "х" -> result += "h"
                "ц" -> result += "c"
                "ч" -> result += "ch"
                "ш" -> result += "sh"
                "щ" -> result += "sh'"
                "ъ" -> result += ""
                "ы" -> result += "i"
                "ь" -> result += ""
                "э" -> result += "e"
                "ю" -> result += "yu"
                "я" -> result += "ya"
                "А" -> result += "A"
                "Б" -> result += "B"
                "В" -> result += "V"
                "Г" -> result += "G"
                "Д" -> result += "D"
                "Е" -> result += "E"
                "Ё" -> result += "E"
                "Ж" -> result += "Zh"
                "З" -> result += "Z"
                "И" -> result += "I"
                "Й" -> result += "I"
                "К" -> result += "K"
                "Л" -> result += "L"
                "М" -> result += "M"
                "Н" -> result += "N"
                "О" -> result += "O"
                "П" -> result += "P"
                "Р" -> result += "R"
                "С" -> result += "S"
                "Т" -> result += "T"
                "У" -> result += "U"
                "Ф" -> result += "F"
                "Х" -> result += "H"
                "Ц" -> result += "C"
                "Ч" -> result += "Ch"
                "Ш" -> result += "Sh"
                "Щ" -> result += "Sh'"
                "Ъ" -> result += ""
                "Ы" -> result += "I"
                "Ь" -> result += ""
                "Э" -> result += "E"
                "Ю" -> result += "Yu"
                "Я" -> result += "Ya"
                " " -> result += "$divider"
                else -> result += "${payload[i]}"
            }
        }
        return result
    }
}