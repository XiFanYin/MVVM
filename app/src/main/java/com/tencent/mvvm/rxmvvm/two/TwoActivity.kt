package com.tencent.mvvm.rxmvvm.two

import android.os.Bundle
import android.util.Log
import android.widget.BaseAdapter
import androidx.lifecycle.ViewModelProvider
import com.qingmei2.architecture.core.base.view.activity.BaseActivity
import com.tencent.mvvm.R
import com.tencent.mvvm.ext.observe
import com.tencent.mvvm.ext.toast
import kotlinx.android.synthetic.main.activity_two.*

class TwoActivity : BaseActivity() {

    override val layoutId = R.layout.activity_two

    lateinit var twoViewModel: TwoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        twoViewModel = ViewModelProvider(this, TwoViewModelFactory()).get(TwoViewModel::class.java)
        observe(twoViewModel.stateLiveData, this::onNewState)
        next.setOnClickListener {
            twoViewModel.page = twoViewModel.page + 1
        }
        top.setOnClickListener {
            twoViewModel.page =1
        }
    }


    private fun onNewState(state: TwoViewState) {

        Log.e("rrrrrr",state.toString())
        if (state.throwable != null) {
            toast { "发生错误了" }
            return
        }

        state.allData?.let {
            if (mRecyclerView.adapter == null){
                mRecyclerView.adapter = ListAdapter(this,it)
            }else{
                if (state.startIndex==0){
                    mRecyclerView.adapter = ListAdapter(this,it)
                }else{
                    (mRecyclerView.adapter as ListAdapter).notifyDataSetChanged()
                }
            }
        }



    }


}