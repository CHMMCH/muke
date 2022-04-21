package com.chm.m.library.log;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @Desc : 日志model
 * @Author: chenhongmou
 * @Time: 2022/4/21 16:06
 */
public class MLogMo {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
    public long timeMillis;
    public int level;
    public String tag;
    public String log;

    public MLogMo(long timeMillis, int level, String tag, String log) {
        this.timeMillis = timeMillis;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String flattenedLog(){
        return getFlattened() +"\n" + log;
    }

    public String getFlattened(){
        return format(timeMillis) + '|' +level + '|' + tag +"|:";
    }

    public String format(long timeMillis){
        return sdf.format(timeMillis);
    }

}
