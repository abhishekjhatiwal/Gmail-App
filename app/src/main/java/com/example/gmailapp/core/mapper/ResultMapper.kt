package com.example.gmailapp.core.mapper

fun interface ResultMapper<T, R> {
    fun map(input: T): R
}