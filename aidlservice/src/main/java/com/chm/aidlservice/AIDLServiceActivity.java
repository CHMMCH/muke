package com.chm.aidlservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

/**
 * @desc : AIDL服务端
 * @author : chenhongmou
 * @time : 2022/2/7 16:48
 */
public class AIDLServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_service);

//        Intent service = new Intent(AIDLServiceActivity.this, AIDLService.class);
//        startService(service);

    }
}
