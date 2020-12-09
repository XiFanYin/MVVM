package com.tencent.mvvm.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(c: CompositeDisposable){
    c.add(this)
}
