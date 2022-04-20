package com.chm.m.library.log;

import androidx.annotation.NonNull;

/**
 * @Desc : 日志打印接口
 * @Author: chenhongmou
 * @Time: 2022/4/20 16:40
 */
public interface MLogPrinter {

    /**
     * 日志打印接口
     * @param config 配置
     * @param level 等级
     * @param tag 标签
     * @param printString 内容
     */
    void print(@NonNull MLogConfig config,int level,String tag,@NonNull String printString);

}
