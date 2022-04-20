package com.chm.muke.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chm.m.library.log.MLog
import com.chm.m.library.log.MLogConfig
import com.chm.m.library.log.MLogType
import com.chm.muke.R

class MLogDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mlog_demo)

        var bt_log = findViewById<View>(R.id.bt_log)

        bt_log.setOnClickListener { printLog()}

    }

    private fun printLog(){

        MLog.log(object : MLogConfig(){
            override fun includeTread(): Boolean {
                return true
            }

            override fun stackTreceDepth(): Int {
                return 0
            }

        },MLogType.E,"-----","5566")

        MLog.a("9900")
    }

}