package com.tencent.mvvm.rxmvvm
//显示loading状态，登录后的用户信息，出现异常问题
data class OneViewState(val showLoading:Boolean,val userInfo :String?,val throwable: Throwable?){

    companion object {
        //提供状态构造方法
        fun initial(): OneViewState {
            return OneViewState(showLoading = false, userInfo = null, throwable = null)
        }
    }


}