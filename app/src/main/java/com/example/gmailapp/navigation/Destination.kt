package com.example.gmailapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object EmailList

//@Serializable
//data class EmailList(
//    val dummy: String = ""   // no real args, just to satisfy serialization
//)

@Serializable
data class EmailDetails(
    val from :String,
    val profileImage: String?,
    val subject: String,
    val isPromotional:Boolean,
    val isStarred: Boolean
)

enum class TopAppbarState {
    HOME,
    DETAILS
}