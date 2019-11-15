package com.example.myapplication11;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Wifi extends Activity implements View.OnClickListener {
    private final static String TAG = "WIFI";

    private Button btn_on;
    private Button btn_off;
    private Button btn_scan;
    private Button btn_stop;
    private Button btn_scanle;
    private Button btn_paired;
    private Button btn_paired_cancle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi);
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
                //bluetoothCanSee();
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

    private void Scanwifi() {
    }

    private void StopScanwifi() {
    }

    private void Offwifi() {
    }

    private void Onwifi() {
    }

    private void initUI(){
        Log.d(TAG, "WIFI : ljq -- initUI" );
        btn_on = (Button)findViewById(R.id.btn_on);
        btn_off = (Button)findViewById(R.id.btn_off);
        btn_scan = (Button)findViewById(R.id.btn_scan);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_scanle = (Button) findViewById(R.id.btn_scanle);
        btn_paired =(Button)findViewById(R.id.btn_paired);
        btn_paired_cancle =(Button)findViewById(R.id.btn_paired_cancle);

        btn_paired_cancle.setOnClickListener(this);
        btn_paired.setOnClickListener(this);
        btn_scanle.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_scan.setOnClickListener(this);
        btn_on.setOnClickListener(this);
        btn_off.setOnClickListener(this);


    }
}
