package com.example.zxingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;
import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity {
    private Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScan = findViewById(R.id.btn_scan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission();
            }
        });
    }

    // 动态申请权限
    private void checkCameraPermission() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    //多个权限全部允许
                    startScanActivity();
                } else {
                    //只要有一个权限禁止，返回false，
                    //下一次申请只申请没通过申请的权限
                   Toast.makeText(MainActivity.this,"该操作需要允许权限",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startScanActivity() {
        startActivity( new Intent(this,ScanActivity.class));
    }
}