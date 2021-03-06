package com.chm.m.library.log;

/**
 * @Desc : log配置类
 * @Author : chenhongmou
 * @Time : 2022/04/19 22:41
 */
public abstract class MLogConfig {

    //这个是打印字符上限，如果输出log信息过多将建议调高
    static int MAX_LEN = 1024;
    static MThreadFormatter M_THREAD_FORMATTER = new MThreadFormatter();
    static MStackTraceFormatter M_STACK_TRACE_FORMATTER = new MStackTraceFormatter();

    //序列器注入
    public JsonPareser injectJsonParser(){
        return null;
    }

    public String getGlobalTag(){
        return "MLog-chmT";
    }

    public boolean enable(){
        return true;
    }


    //日志是否包含线程信息
    public boolean includeTread(){
        return false;
    }

    //堆栈信息深度 默认最多6行
    public int stackTraceDepth(){
        return 6;
    }

    public MLogPrinter[] printers(){
        return null;
    }

    //序列化接口 把序列化交给调用者实现
    public interface JsonPareser{
        String toJsonn(Object src);
    }



}
