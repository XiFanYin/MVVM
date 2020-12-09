package com.tencent.mvvm.util


import com.tencent.mvvm.app.App
import com.tencent.mvvm.ext.toast

inline fun toast(value: () -> String): Unit =
        App.INSTANCE.toast(value)