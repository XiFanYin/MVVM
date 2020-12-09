package com.tencent.mvvm.base

sealed class Errors : Throwable() {
    data class UiError(val code: Int = 2, val desc: String = "") : Errors()
    data class ApiException(val code: Int = 3, val desc: String = "") : Errors()
}