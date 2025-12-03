package com.example.gmailapp.data.remote.handler

import com.example.gmailapp.core.error.Failure
import com.example.gmailapp.core.funtational.Enter
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toEither(): Enter<Failure, Nothing> {
    return when (this) {
        is IOException -> Enter.Left(Failure.NetworkError(this))
        is HttpException -> {
            val code = code()
            val message = message()
            Enter.Left(Failure.ServerError(code, message))
        }
        else -> Enter.Left(Failure.UnknownError(this))
    }
}