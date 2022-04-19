package com.chm.muke

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author: chenhongmou
 * @description :
 * @time: 2022/4/14 15:44
 */

class MainActivity : AppCompatActivity(){

    private val TAG = "MainActivity-chm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.bt_demo).setOnClickListener {
            Log.d(TAG, "onCreate: 进来了")
        }

    }

}