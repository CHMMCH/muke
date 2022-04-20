package com.chm.m.library.log;

/**
 * @Desc : 日志可视化接口
 * @Author: chenhongmou
 * @Time: 2022/4/20 17:14
 */
public interface MLogFormatter<T> {

    String format(T data);

}
