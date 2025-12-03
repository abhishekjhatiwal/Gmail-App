package com.example.gmailapp.data.repository

import com.example.gmailapp.core.error.Failure
import com.example.gmailapp.core.funtational.Enter
import com.example.gmailapp.data.mapper.EmailDetailsMapper
import com.example.gmailapp.data.mapper.EmailListMapper
import com.example.gmailapp.data.remote.api.ApiService
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val emailListMapper: EmailListMapper,
    private val emailDetailsMapper: EmailDetailsMapper
) : EmailRepository {
    override suspend fun getEmailList(): Enter<Failure, List<EmailListItemModel>> =
        safeApiCall(
            apiCall = { apiService.getEmailList() },
            mapper = { emailListMapper.map(it) }
        )

    override suspend fun getEmailDetails() =
        safeApiCall(
            apiCall = { apiService.getEmailDetail() },
            mapper = { emailDetailsMapper.map(it) }
        )
}
