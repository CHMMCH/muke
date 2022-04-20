package com.chm.m.library.log;

/**
 * @Desc : 线程可视化器
 * @Author: chenhongmou
 * @Time: 2022/4/20 17:16
 */
public class MThreadFormatter implements MLogFormatter<Thread>{


    @Override
    public String format(Thread data) {
        return "Thread:"+data.getName();
    }

}
