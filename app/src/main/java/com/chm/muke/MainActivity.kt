package com.chm.muke

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chm.m.library.log.MLog
import com.chm.muke.demo.MLogDemoActivity

/**
 * @Desc :
 * @Author: chenhongmou
 * @Time: 2022/4/14 15:44
 */

class MainActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        findViewById<View>(R.id.bt_demo).setOnClickListener {
//           startActivity(Intent(this,MLogDemoActivity::class.java))
//        }

    }

    override fun onClick(p0: View?) {
        MLog.vt("进来了","chmt")
        when(p0!!.id){
            R.id.bt_demo ->{
                startActivity(Intent(this,MLogDemoActivity::class.java))
            }
        }
    }
}