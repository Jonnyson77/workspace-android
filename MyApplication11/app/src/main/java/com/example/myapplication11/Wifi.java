package com.example.myapplication11;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication11.controller.WifiController;

public class Wifi extends Activity implements View.OnClickListener {
    private final static String TAG = "WIFI";

    private Button btn_on;
    private Button btn_off;
    private Button btn_scan;
    private Button btn_stop;
    private Button btn_scanle;
    private Button btn_paired;
    private Button btn_paired_cancle;

    private WifiController wifiController ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi);
        wifiController = new WifiController(Wifi.this);

        requestLocationPermission();
        regesterWifiBroadcast();
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_on:
                Onwifi();
                break;
            case R.id.btn_off:
                Offwifi();
                break;
            case R.id.btn_scan:
                Scanwifi();
                break;
            case R.id.btn_stop :
                StopScanwifi();
                break;
            case R.id.btn_scanle :
                checkWifiState();
                break;
            case R.id.btn_paired :
                //Pairwifi();
                break;
            case R.id.btn_paired_cancle :
                //PairwifiCancle();
                break;
            default:
                break;
        }

    }

    private void checkWifiState() {
        wifiController.checkNetCardState();
    }

    private void Scanwifi() {
        wifiController.getScanResult();

    }

    private void StopScanwifi() {

    }

    private void Offwifi() {
        wifiController.closeNetCard();
    }

    private void Onwifi() {
        wifiController.openNetCard();
    }

    private void initUI(){

        Log.d(TAG, "WIFI : ljq -- initUI" );
        btn_on = (Button)findViewById(R.id.btn_on);
        btn_off = (Button)findViewById(R.id.btn_off);
        btn_scan = (Button)findViewById(R.id.btn_scan);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_scanle = (Button) findViewById(R.id.btn_scanle);
        btn_paired =(Button)findViewById(R.id.btn_paired);



        btn_paired.setOnClickListener(this);
        btn_scanle.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_scan.setOnClickListener(this);
        btn_on.setOnClickListener(this);
        btn_off.setOnClickListener(this);


    }
    public void regesterWifiBroadcast(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(new WifiBroadCast(),intentFilter);
    }

    class WifiBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
               wifiController.getScanResult();
            }
        }
    }

    public void requestLocationPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this, "自Android 6.0开始需要打开位置权限才可以搜索到WIFI设备", Toast.LENGTH_SHORT);

                }
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        127);
            }
        }
    }
}
