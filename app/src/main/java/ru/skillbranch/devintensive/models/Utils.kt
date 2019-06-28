package ru.skillbranch.devintensive.models

import android.service.autofill.Validators.or

object Utils {
    fun parseFullName(userName: String): Pair<String?, String?>{
        if (userName == "")
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
}