package com.chm.m.library.log;

/**
 * @Desc : log配置类
 * @Author : chenhongmou
 * @Time : 2022/04/19 22:41
 */
public abstract class MLogConfig {

    public String getGlobalTag(){
        return "MLog";
    }

    public boolean enable(){
        return true;
    }

}
