package com.example.gmailapp.domain.usecase

import com.example.gmailapp.domain.repository.EmailRepository
import javax.inject.Inject

class EmailDetailsUseCase @Inject constructor(private val repository: EmailRepository) {
    suspend operator fun invoke() = repository.getEmailDetails()
}