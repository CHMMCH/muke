package com.chm.m.library.log;

/**
 * @Desc : 堆栈信息可视化器
 * @Author: chenhongmou
 * @Time: 2022/4/20 17:17
 */
public class MStackTraceFormatter implements MLogFormatter<StackTraceElement[]>{

    @Override
    public String format(StackTraceElement[] stackTrace) {
        StringBuffer sb = new StringBuffer(128);
        if (stackTrace == null || stackTrace.length==0){
            return null;
        }else if (stackTrace.length == 1){
            return "\t─ " + stackTrace[0].toString();
        }else{
            for (int i=0,len = stackTrace.length;i < len; i++){
                //第一个
                if (i == 0){
                    sb.append("stackTrace: \n");
                }
                //中间和最后一个
                if (i!=len-1){
                    sb.append("\t├ ");
                    sb.append(stackTrace[i].toString());
                    sb.append("\n");
                }else{
                    sb.append("\t└ ");
                    sb.append(stackTrace[i].toString());
                }
            }
            return sb.toString();
        }
    }
}
