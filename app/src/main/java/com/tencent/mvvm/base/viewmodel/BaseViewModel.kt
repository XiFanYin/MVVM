package com.qingmei2.architecture.core.base.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(){

    var cacheCompositeDisposable: CompositeDisposable? = null
    //提供给切断流的方法
    fun getCompositeDisposable(): CompositeDisposable {
        if (cacheCompositeDisposable == null) {
            cacheCompositeDisposable = CompositeDisposable()
            return cacheCompositeDisposable!!
        }
        return cacheCompositeDisposable!!
    }

    override fun onCleared() {
        unDisposable()
    }

    fun unDisposable() {
        cacheCompositeDisposable?.clear()
        cacheCompositeDisposable = null
    }


}