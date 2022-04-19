package com.chm.m.library.log;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Desc : Log实体类
 * @Author : chenhongmou
 * @Time : 2022/04/19 22:13
 */
public class MLogType {

    @IntDef({V,D,I,W,E,A}) //接受类型
    @Retention(RetentionPolicy.SOURCE) //注解的保留时期在源码级别
    public @interface TYPE{}
    public final static int V= Log.VERBOSE;
    public final static int D= Log.DEBUG;
    public final static int I= Log.INFO;
    public final static int W= Log.WARN;
    public final static int E= Log.ERROR;
    public final static int A= Log.ASSERT;

}
