package com.chm.m.library.log;

import static com.chm.m.library.log.MLogConfig.MAX_LEN;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @Desc : 控制台打印器
 * @Author: chenhongmou
 * @Time: 2022/4/20 17:36
 */
public class MConsolePrinter implements MLogPrinter{


    @Override
    public void print(@NonNull MLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int countOfSub = len/MAX_LEN;//得到行数

        if (countOfSub>0) {
            int index = 0;

            //遍历输出每一次 一次MAX_LEN个字符
            for (int i = 0; i < countOfSub; i++) {
                Log.println(level, tag, printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }

            //无法整除的剩余部分
            if (index != len) {
                Log.println(level, tag, printString.substring(index, len));
            }

        }else{
            //行数不足1，直接全部输出
            Log.println(level,tag,printString);
        }
    }


}
