package ru.skillbranch.devintensive.models

import kotlinx.android.synthetic.main.activity_profile_constraint.*
import ru.skillbranch.devintensive.utils.Utils

data class Profile
(
    val firstName: String,
    val lastName: String,
    val about: String,
    val rating: Int = 0,
    val respect: Int = 0,
    val repository: String
)
{
    val rank: String = "Junior Android Developer"
    val nickname = "John Doe"
    fun toMap() : Map<String, Any> = mapOf(
        "nickname" to nickname,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "rating" to rating,
        "respect" to respect,
        "repository" to repository
    )



    fun nickName(firstName: String, lastName:String): String = "${Utils.transliteration(firstName)}_${Utils.transliteration(lastName)}"



}