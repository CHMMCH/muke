package com.chm.m.library.log;

import androidx.annotation.NonNull;

/**
 * @Desc : log管理类
 * @Author : chenhongmou
 * @Time : 2022/4/19 22:58
 */
public class MLogManager {

    private MLogConfig config;
    private static MLogManager instance;

    private MLogManager(MLogConfig config){
        this.config = config;
    }

    public static MLogManager getInstance(){
        return instance;
    }

    public static void init(@NonNull MLogConfig config){
        instance = new MLogManager(config);
    }

    public MLogConfig getConfig(){
        return config;
    }

}
