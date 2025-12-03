package com.example.gmailapp.core.funtational

sealed class Enter<out L, out R> {
    data class Left<out T>(val value:T) : Enter<T, Nothing>()
    data class Right<out T>(val value:T) : Enter<Nothing, T>()
}

inline fun <L, R, T> Enter<L,R>.fold(
    left: (L) -> T,
    right: (R) -> T,
)  = when(this) {
    is Enter.Left -> left(value)
    is Enter.Right -> right(value)
}


fun Boolean?.orDefault(default : Boolean = false) : Boolean = this ?: default

fun <T,R : Any> List<T>?.mapOrDefault(defaultListValue : List<R> = emptyList(), transform: (T) -> R): List<R> {
    return this?.filterNotNull()?.map(transform) ?: defaultListValue
}