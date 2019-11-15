package com.example.myapplication11;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private String TAG = "MainActivity";
    private Button btn_bluetooth ;
    private Button btn_wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ljq  ");
        setContentView(R.layout.main);
        init();
    }
    private void init(){
        btn_bluetooth = (Button) findViewById(R.id.bluetooth);
        btn_bluetooth.setOnClickListener(this);

        btn_wifi = findViewById(R.id.wifi);
        btn_wifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bluetooth:
                StartBluetoothActivity();
                break;
            case R.id.wifi:
                StartWifiActivity();
                break;
             default:
                 break;
        }
    }

    private void StartBluetoothActivity(){
        Intent intent = new Intent(MainActivity.this,Bluetooth.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void StartWifiActivity(){
        Intent intent = new Intent(MainActivity.this,Wifi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
