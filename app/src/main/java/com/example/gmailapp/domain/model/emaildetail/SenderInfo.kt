package com.example.gmailapp.domain.model.emaildetail

data class SenderInfoModel(
    val email: String,
    val name: String,
    val profileImage: String? = null
)