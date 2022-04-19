package com.chm.aidlservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @desc : AIDL客户端
 * @author : chenhongmou
 * @time : 2022/2/7 16:47
 */
public class AIDLClientActivity extends AppCompatActivity {
    private static final String TAG = "AIDLClientActivity-chm";

    private EditText et_num1;
    private EditText et_num2;
    private TextView tv_results;
    private Button bt_compute;

    AidlInterface aidlInterface;


    private ServiceConnection conn =  new ServiceConnection() {

        //绑定上服务的时候执行
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Log.d(TAG, "绑定了");
            //拿到远程的服务
            aidlInterface = AidlInterface.Stub.asInterface(iBinder);

        }

        //断开服务的时候执行
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            //回收资源
            aidlInterface = null;

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_client);

        initView();

        initEvent();

        bindService();

    }

    private void initView() {
        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        tv_results = findViewById(R.id.tv_results);
        bt_compute = findViewById(R.id.bt_compute);
    }


    private void initEvent() {

        bt_compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                isTopActivity();
//
//                checkIsAlive();

                int num1 = Integer.parseInt(et_num1.getText().toString());
                int num2 = Integer.parseInt(et_num2.getText().toString());

                try {
                    //调用远程服务
                    int res = aidlInterface.add(num1, num2);
                    tv_results.setText(res+"");
                } catch (RemoteException e) {
                    e.printStackTrace();
                    tv_results.setText("出错:"+e.toString());
                }
            }
        });
    }


    /**
     * 检测应用是否活着
     */
    private void checkIsAlive() {
        String pName = "com.zszy.idl.face.demo";
        int uid = getPackageUid(getApplicationContext(), pName);
        Log.d(TAG, "uid: "+uid);
        if (uid > 0) {
            boolean rstA = isAppRunning(getApplicationContext(), pName);
            boolean rstB = isProcessRunning(getApplicationContext(), uid);
            boolean rstC = isRunningAppProcess(getApplicationContext(), uid);
            Log.e(TAG, "应用程序正在运行:" + rstA + ", 进程正在运行:" + rstB + ", 正在运行应用程序进程:" + rstC );

            if (rstA) {
                //指定包名的程序正在运行中
                Log.d(TAG, "App is running...");
            } else {
                //指定包名的程序未在运行中
                Log.d(TAG, "App is not running, restart app...");
//                Intent intent = new Intent();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setClass(MonitoringService.this, MainActivity.class);
//                startActivity(intent);
            }

        } else {
            //应用未安装
            Log.e(TAG, "App is not installed.");
        }

    }


    /**
     * 判断最上层activity是什么 初测无法跨app判断 待详细测试
     * @return
     */
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
     * 判断app是否在运行  不可跨app检测
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        if (list.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo info : list) {
            Log.d(TAG, "getClassName "+info.baseActivity.getClassName());
            Log.d(TAG, "getPackageName "+info.baseActivity.getPackageName());
            if (info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断某个进程是否在运行 可以跨app检测
     * @param context
     * @param uid
     * @return
     */
    public static boolean isProcessRunning(Context context, int uid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() > 0) {
            for (ActivityManager.RunningServiceInfo appProcess : runningServiceInfos) {
                if (uid == appProcess.uid) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 判断某个app是否在运行 可以跨app检测
     * @param context
     * @param uid
     * @return
     */
    public static boolean isRunningAppProcess(Context context, int uid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        if (list.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo info : list) {
            if (uid == info.uid) {
                return true;
            }
        }
        return false;
    }


    //获取已安装应用的 uid，-1 表示未安装此应用或程序异常
    public static int getPackageUid(Context context, String packageName) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 0);
            if (applicationInfo != null) {
                Log.d(TAG, "app uid:" + applicationInfo.uid);
                return applicationInfo.uid;
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }


    private void bindService() {
        //获取服务端
        Intent intent =  new Intent();
        //显示Intent启动绑定服务 5.0以后不可用隐示
        intent.setComponent(new ComponentName("com.chm.aidlservice","com.chm.aidlservice.AIDLService"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
