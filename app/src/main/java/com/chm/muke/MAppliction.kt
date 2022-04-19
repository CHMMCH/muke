package com.chm.muke

import android.app.Application
import com.chm.m.library.log.MLogConfig
import com.chm.m.library.log.MLogManager

/**
 * @Desc :
 * @Author : chenhongmou
 * @Time : 2022/4/19 23:25
 */
class MAppliction : Application() {

    override fun onCreate() {
        super.onCreate()
        MLogManager.init(object : MLogConfig(){

            override fun getGlobalTag(): String {
                return "Application-chmt-"
            }

            override fun enable(): Boolean {
                return true
            }

        })
    }

}