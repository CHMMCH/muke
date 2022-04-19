package com.chm.m.library.log;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @Desc : log打印类
 * @Author : chenhongmou
 * @Time : 2022/04/19 22:12
 */
public class MLog {


    public static void v(Object... contents){
        log(MLogType.V,contents);
    }
    public static void vt(String tag,Object... contents){
        log(MLogType.V,tag,contents);
    }


    public static void d(Object... contents){
        log(MLogType.D,contents);
    }
    public static void dt(String tag,Object... contents){
        log(MLogType.D,tag,contents);
    }


    public static void i(Object... contents){
        log(MLogType.I,contents);
    }
    public static void it(String tag,Object... contents){
        log(MLogType.I,tag,contents);
    }


    public static void w(Object... contents){
        log(MLogType.W,contents);
    }
    public static void wt(String tag,Object... contents){
        log(MLogType.W,tag,contents);
    }


    public static void e(Object... contents){
        log(MLogType.E,contents);
    }
    public static void et(String tag,Object... contents){
        log(MLogType.E,tag,contents);
    }


    public static void a(Object... contents){
        log(MLogType.A,contents);
    }
    public static void at(String tag,Object... contents){
        log(MLogType.A,tag,contents);
    }

    //没TAG就用全局TAG
    public static void log(@MLogType.TYPE int type, Object... contents){
        log(type,MLogManager.getInstance().getConfig().getGlobalTag(),contents);
    }

    public static void log(@MLogType.TYPE int type,@NonNull String tag, Object... contents){
        log(MLogManager.getInstance().getConfig(),type,tag,contents);
    }

    public static void log(@NonNull MLogConfig config,@MLogType.TYPE int type,@NonNull String tag, Object... contents){
        if (!config.enable()){
            return;
        }

        StringBuilder sb = new StringBuilder();
        String body = parseBody(contents);
        sb.append(body);
        Log.println(type,tag,body);

    }

    private static String parseBody(@NonNull Object[] contents){
        StringBuffer sb = new StringBuffer();
        for(Object o:contents){
            sb.append(o.toString()).append(";");
        }
        //删除最后一个分号
        if (sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }



}
