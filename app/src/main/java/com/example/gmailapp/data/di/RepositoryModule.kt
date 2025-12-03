package com.example.gmailapp.data.di

import com.example.gmailapp.data.repository.EmailRepositoryImpl
import com.example.gmailapp.domain.repository.EmailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindEmailRepository(emailRepositoryImpl: EmailRepositoryImpl): EmailRepository
}