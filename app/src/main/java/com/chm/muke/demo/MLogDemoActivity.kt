package com.chm.muke.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chm.m.library.log.*
import com.chm.muke.R

class MLogDemoActivity : AppCompatActivity() {

    var viewPrinter: MViewPrinter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mlog_demo)

        val bt_log = findViewById<View>(R.id.bt_log)
        bt_log.setOnClickListener { printLog()}


        //可视化log初始化
        viewPrinter = MViewPrinter(this)
        viewPrinter!!.viewProvider.showFloatingView()
        MLogManager.getInstance().addPrinter(viewPrinter)
    }

    private fun printLog(){

        MLog.log(object : MLogConfig(){
            override fun includeTread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }

        },MLogType.E,"chmt","8888")

        MLog.a("测试log")

        MLog.at("测试TAG","测试log")

    }

}