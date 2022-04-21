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

        viewPrinter = MViewPrinter(this)

        var bt_log = findViewById<View>(R.id.bt_log)
        bt_log.setOnClickListener { printLog()}

        viewPrinter!!.viewProvider.showFloatingView()

    }

    private fun printLog(){
        //添加可视化打印器
        MLogManager.getInstance().addPrinter(viewPrinter)

        MLog.log(object : MLogConfig(){
            override fun includeTread(): Boolean {
                return true
            }

            override fun stackTreceDepth(): Int {
                return 0
            }

        },MLogType.E,"chmt","8888")

        MLog.a(Math.min(6666, 9999))

    }

}