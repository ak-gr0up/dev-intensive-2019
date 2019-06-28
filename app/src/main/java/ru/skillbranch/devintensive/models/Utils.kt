package ru.skillbranch.devintensive.models

class Utils {
    fun parseFullName(userName: String): Unit{
        val name: List<String>? = userName.split(" ")
        var firstName = name?.getOrNull(0)
        var lastName = name?.getOrNull(1)


    }
}