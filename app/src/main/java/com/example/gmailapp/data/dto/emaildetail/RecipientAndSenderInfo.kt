package com.example.gmailapp.data.dto.emaildetail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipientInfo(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String
)

@Serializable
data class SenderInfo(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String?
)