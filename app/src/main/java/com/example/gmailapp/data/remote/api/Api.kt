package com.example.gmailapp.data.remote.api

import com.example.gmailapp.data.dto.emaildetail.EmailDetailsDto
import com.example.gmailapp.data.dto.emaillist.EmailListItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/emaillist")
    suspend fun getEmailList(): Response<ArrayList<EmailListItemDto>>

    @GET("api/v1/emaildetails")
    suspend fun getEmailDetail(): Response<ArrayList<EmailDetailsDto>>
}