package com.example.gmailapp.data.remote.handler

import com.example.gmailapp.core.error.Failure
import com.example.gmailapp.core.funtational.Enter
import com.example.gmailapp.core.mapper.ResultMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T, R> safeApiCall(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>,
    mapper: ResultMapper<T, R>
): Enter<Failure, R> {
    return withContext(ioDispatcher) {
        runCatching {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    Enter.Right(mapper.map(it))
                } ?: Enter.Left(
                    Failure.ServerError(
                        code = response.code(),
                        message = response.message()
                    )
                )
            } else {
                Enter.Left(Failure.ServerError(response.code(), response.message()))
            }
        }
    }.getOrElse {
        it.toEither()
    }
}