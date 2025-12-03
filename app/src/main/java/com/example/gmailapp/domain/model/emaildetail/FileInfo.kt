package com.example.gmailapp.domain.model.emaildetail

data class FileInfo(
    val filename: String,
    val mimeType: String,
    val size: Long = 0L,
    val downLoadUrl: String? = null
)