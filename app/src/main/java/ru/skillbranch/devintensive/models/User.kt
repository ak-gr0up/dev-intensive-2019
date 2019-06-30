package ru.skillbranch.devintensive.models
import java.util.*

data class User(
    val id : String? = null,
    var firstName : String?,
    var lastName : String?,
    var avatar : String? = null,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = null,
    var isOnline : Boolean = false){

    companion object Factory{
        fun makeUser(fullName : String) : User {
            val userName = Utils.parseFullName(fullName)
            return User(firstName = userName.first, lastName = userName.second)


        }
    }
}


