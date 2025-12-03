package com.example.gmailapp.domain.repository

import com.example.gmailapp.core.error.Failure
import com.example.gmailapp.core.funtational.Enter
import com.example.gmailapp.domain.model.emaildetail.EmailDetailsModel
import com.example.gmailapp.domain.model.emaillist.EmailListItemModel

interface EmailRepository {
    suspend fun getEmailList(): Enter<Failure, List<EmailListItemModel>>
    suspend fun getEmailDetails(): Enter<Failure, EmailDetailsModel>
}