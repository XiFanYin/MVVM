package com.tencent.mvvm.ext

import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
//扩展函数，抽取postValue方法
@AnyThread
inline fun <reified T> MutableLiveData<T>.postNext(map: (T) -> T) {
    postValue(map(verifyLiveDataNotEmpty()))
}
//扩展函数，抽取setValue方法
@MainThread
inline fun <reified T> MutableLiveData<T>.setNext(map: (T) -> T) {
    value = map(verifyLiveDataNotEmpty())
}

//判断value是否为null，如果为null，直接抛出异常
@AnyThread
inline fun <reified T> LiveData<T>.verifyLiveDataNotEmpty(): T {
    return value
        ?: throw NullPointerException("MutableLiveData<${T::class.java}> not contain value.")
}