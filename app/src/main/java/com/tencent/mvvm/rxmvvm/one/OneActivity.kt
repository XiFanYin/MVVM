package com.tencent.mvvm.rxmvvm.one

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonParseException
import com.qingmei2.architecture.core.base.view.activity.BaseActivity
import com.tencent.mvvm.R
import com.tencent.mvvm.base.Errors
import com.tencent.mvvm.ext.observe
import com.tencent.mvvm.ext.toast
import com.tencent.mvvm.rxmvvm.OneViewModel
import com.tencent.mvvm.rxmvvm.OneViewModelFactory
import com.tencent.mvvm.rxmvvm.OneViewState
import kotlinx.android.synthetic.main.one_activity.*
import org.json.JSONException
import retrofit2.HttpException

/**
 *  模拟登录
 */
class OneActivity : BaseActivity() {

    lateinit var oneViewModel: OneViewModel

    override val layoutId = R.layout.one_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取ViewModel对象
        oneViewModel = ViewModelProvider(this, OneViewModelFactory()).get(OneViewModel::class.java)
        //监听数据改变
        observe(oneViewModel.stateLiveData, this::onNewState)
        //点击事件监听
        login.setOnClickListener {
            oneViewModel.login(
                username.text.toString(),
                password.text.toString()
            )
        }
    }

    //当state改变的时候，调用这个方法来处理ui
    private fun onNewState(state: OneViewState) {
        //异常处理
        if (state.throwable != null) {
            when (state.throwable) {
                is Errors.UiError -> "用户名或者密码为空" //用户ui操作有误会提示
                is Errors.ApiException -> state.throwable.desc//适配国内http响应码是200的情况，但是数据code标识却不是200的情况，这个异常在解析器中抛出
                is HttpException -> "网络出现异常"//这里是retrofit2内部抛出的网络异常
                is JSONException -> "数据解析异常"//这是Gosn解析的异常
                is JsonParseException->"接受的bean和后台返回的bean类型不符"
                else -> "未知错误"
            }.also { str ->
                toast { str }
            }
        }
        //登录状态的处理
        if (state.showLoading) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
        //拿到登录返回的数据
        state.userInfo?.let { userinfo.setText("$it") }

    }

}