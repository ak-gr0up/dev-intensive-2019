package ru.skillbranch.devintensive.models
import ru.skillbranch.devintensive.models.User.Builder.*
import java.util.*

data class User(
    var id : String? = null,
    var firstName : String?,
    var lastName : String?,
    var avatar : String? = null,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = null,
    var isOnline : Boolean = false){

    data class Builder(
        var id : String? = null,
        var firstName : String? = null,
        var lastName : String? = null,
        var avatar : String? = null,
        var rating : Int = 0,
        var respect : Int = 0,
        var lastVisit : Date? = null,
        var isOnline : Boolean = false)
    {
        fun id(Id: String?) = apply{this.id = Id}
        fun firstName(firstName: String?) = apply{this.firstName = firstName}
        fun lastName(lastName: String?) = apply{this.lastName = lastName}
        fun avatar(avatar: String?) = apply{this.avatar = avatar}
        fun rating(rating: Int) = apply{this.rating = rating}
        fun respect(respect: Int) = apply{this.respect = respect}
        fun lastVisit(lastVisit: Date?) = apply{this.lastVisit = lastVisit}
        fun isOnline(isOnline: Boolean) = apply{this.isOnline = isOnline}
        fun build(): User{
            return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }
    companion object Factory{
        fun makeUser(fullName : String) : User {
            val userName = Utils.parseFullName(fullName)
            return User(firstName = userName.first, lastName = userName.second)


        }
    }
}


