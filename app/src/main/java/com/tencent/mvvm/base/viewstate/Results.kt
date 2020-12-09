package com.tencent.mvvm.base.viewstate

import com.qingmei2.architecture.core.base.viewstate.IViewState


//定义ViewModel向UI传递数据的state类
sealed class Results<out T> : IViewState {
    //静态方法
    companion object {
        //成功返回成功对象
        fun <T> success(result: T): Results<T> = Success(result)
        fun <T> failure(error: Throwable): Results<T> = Failure(error)
    }

    data class Failure(val error: Throwable) : Results<Nothing>()
    data class Success<out T>(val data: T) : Results<T>()
}