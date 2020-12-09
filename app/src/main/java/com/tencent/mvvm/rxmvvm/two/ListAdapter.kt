package com.tencent.mvvm.rxmvvm.two

import android.content.Context
import com.ehealth.machine.base.adapter.BaseAdapter
import com.ehealth.machine.base.adapter.CommonViewHolder
import com.tencent.mvvm.R

class ListAdapter (mContext: Context, data: MutableList<String>?):BaseAdapter<String>(mContext, R.layout.adapter_item, data) {
    override fun convert(holder: CommonViewHolder, data: String, position: Int) {
        holder.setText(R.id.text,data)
    }
}