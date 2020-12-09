package com.tencent.mvvm.rxmvvm

import android.content.Intent
import android.os.Bundle
import com.qingmei2.architecture.core.base.view.activity.BaseActivity
import com.tencent.mvvm.R
import com.tencent.mvvm.rxmvvm.one.OneActivity
import com.tencent.mvvm.rxmvvm.two.TwoActivity
import kotlinx.android.synthetic.main.activity_home.*

class RxHomeActivity() : BaseActivity() {

    override val layoutId: Int = R.layout.activity_home
   // ViewModel要向Acitivy提供可订阅的State对象，
    // 如果我定义为（val currentPage:Int,val pageData:List<String>）其中，currentPage，pageData是页面对应的数据
    // 那么activity根据page可以向adapter去setNewData（）或者concatData()
//    假如请求第三页完成后，用户旋转了屏幕，activity销毁重新创建，由于adapter重新创建和State重新订阅
//    导致列表会只渲染第三页数据，又由于我把currentPage维护到ViewModel中，activity会走concatData()方法

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btn1.setOnClickListener {  startActivity(Intent(this, OneActivity::class.java)) }
        btn2.setOnClickListener {  startActivity(Intent(this, TwoActivity::class.java)) }
    }



}