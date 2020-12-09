package com.tencent.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tencent.mvvm.rxmvvm.RxHomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnrx.setOnClickListener {
            startActivity(Intent(this,RxHomeActivity::class.java))
        }


    }
}