package com.chm.m.library.log;

/**
 * @Desc : 堆栈信息工具类
 * @Author : chenhongmou
 * @Time : 2022/4/20 23:15
 */
public class MStackTraceUtil {


    /**
     * 先忽略包名之外的多余堆栈信息,再裁剪指定长度,最终返回最简堆栈信息
     * @param stackTrace
     * @param igonrePackage
     * @param maxDepth
     * @return
     */
    public static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTrace, String igonrePackage,int maxDepth){
        return cropStackTrace(getRealStackTrack(stackTrace,igonrePackage),maxDepth);
    }


    /**
     * 获取忽略包名之外的堆栈信息
     * @param stackTrace
     * @param igonrePackage
     * @return
     */
    private static StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTrace, String igonrePackage){

        int igonreDepth = 0;
        int alldepth = stackTrace.length;
        String className;
        for (int i = alldepth - 1; i >= 0;i--){
            className = stackTrace[i].getClassName();
            if (igonrePackage!=null && className.startsWith(igonrePackage)){
                igonreDepth = i+1;
                break;
            }
        }
        int realDepth = alldepth - igonreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(stackTrace,igonreDepth,realStack,0,realDepth);
        return realStack;
    }


    /**
     * 裁剪堆栈信息
     * @param callStack
     * @param maxDepth
     * @return
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack,int maxDepth){

        int realDepth = callStack.length;

        if (maxDepth>0){
            //从两个数据里面取最小值
            realDepth=Math.min(maxDepth,realDepth);
        }

        //裁剪出指定长度
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack,0,realStack,0,realDepth);

        return realStack;
    }

}
