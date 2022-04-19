package com.chm.aidlservice;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenhongmou
 * @description :
 * @time: 2022/2/7 16:43
 */
public class AIDLService extends Service {
    private static final String TAG = "AIDLService-chm";

    /**
     * 当客户端绑定到该服务时候 会执行
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder =  new AidlInterface.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {

            Log.d(TAG, "收到远程请求，输入的参数是 "+num1+" 和 "+num2);


            Log.d(TAG, "是否存活: "+isAppRunning(AIDLService.this,"com.chm.aidlservice"));

            if (isTopActivity()){
                num1 = num1*num2;
            }else{
                num1 = num1+num2;
            }

            return num1;
        }
    };

    private boolean isTopActivity()
    {
        boolean s = false;
        ActivityManager am = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        Log.d(TAG, "getPackageName = " + cn.getPackageName());
        Log.d(TAG, "getClassName = " + cn.getClassName());
        if (cn.getClassName().contains("AIDLClientActivity")) {
            s = true;
        }
        return s;
    }

    /**
     * 方法描述：判断某一应用是否正在运行
     * Created by cafeting on 2017/2/4.
     *
     * @param context     上下文
     * @param packageName 应用的包名
     * @return true 表示正在运行，false 表示没有运行
     */
    public static boolean isAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        if (list.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

}
