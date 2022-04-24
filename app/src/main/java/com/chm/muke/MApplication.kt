package com.chm.muke

import android.app.Application
import com.chm.m.library.log.MConsolePrinter
import com.chm.m.library.log.MLogConfig
import com.chm.m.library.log.MLogManager
import com.google.gson.Gson

/**
 * @Desc :
 * @Author : chenhongmou
 * @Time : 2022/4/19 23:25
 */
class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MLogManager.init(object : MLogConfig(){

            override fun injectJsonParser(): JsonPareser {
                return JsonPareser { src -> Gson().toJson(src) }

            }

            override fun getGlobalTag(): String {
                return "MLog-chmT"
            }

            override fun enable(): Boolean {
                return true
            }

            override fun includeTread(): Boolean {
                return true
            }

        },MConsolePrinter())
    }

}