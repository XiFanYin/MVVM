package com.tencent.mvvm.rxmvvm.two

data class TwoViewState(val allData: MutableList<String>?, val throwable: Throwable? ,val startIndex:Int)  {


    companion object {

        //提供状态构造方法
        fun initial(): TwoViewState {
            return TwoViewState(allData = null, throwable = null,startIndex = 0)
        }
    }
}

