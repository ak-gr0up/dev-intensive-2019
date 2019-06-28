package ru.skillbranch.devintensive.models
import java.util.*

class User(
    val id : String? = null,
    var firstName : String?,
    var lastName : String?,
    var avatar : String? = null,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = null,
    var isOnline : Boolean = false){

    val info: String = "Name: $firstName Surname: $lastName"

    companion object Factory{
        fun makeUser(fullName : String) : User {
            val name: List<String>? = fullName.split(" ")
            val firstName = name?.getOrNull(0)
            val lastName = name?.getOrNull(1)
            return User(firstName = firstName, lastName = lastName)


        }
    }
}


