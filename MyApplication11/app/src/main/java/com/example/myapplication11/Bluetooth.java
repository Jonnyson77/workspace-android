package com.example.myapplication11;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Bluetooth extends Activity implements View.OnClickListener{
    String TAG ="Bluetooth";
    private BluetoothAdapter bluetoothAdapter  = BluetoothAdapter.getDefaultAdapter();
    private BluetoothBroadCast bluetoothBroadCast = new BluetoothBroadCast();
    private IntentFilter intentFilter = new IntentFilter();
    private BluetoothProfile.ServiceListener serviceListener;
    private BluetoothHeadset headset;
    private Button btn_on;
    private Button btn_off;
    private Button btn_scan;
    private List<BluetoothDevice> lists = new ArrayList<BluetoothDevice>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_on:
                Log.d(TAG, "onClick:   ------");
                OnBluetooth();
                break;
            case R.id.btn_off:
                OffBluetooth();
                break;
            case R.id.btn_scan:
                ScanBluetoothLe();
                break;
            default:
                break;
        }
    }

    public void ScanBluetooth() {
        Log.d(TAG, "OnBluetooth: ljq -- scan Bluetooth" );
        if (bluetoothAdapter!=null){
            boolean enabled = bluetoothAdapter.isEnabled();
            if (enabled){
                boolean discovering = bluetoothAdapter.isDiscovering();
                if (!discovering){
                    lists.clear();
                    bluetoothAdapter.startDiscovery();  // 开始扫描蓝牙设备
                }
            }else {
                Toast.makeText(this,"蓝牙未开启，请开启蓝牙",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"蓝牙不可用", Toast.LENGTH_SHORT).show();
        }
    }

    public void ScanBluetoothLe() {
        Log.d(TAG, "OnBluetooth: ljq -- Lescan Bluetooth" );
        if (bluetoothAdapter!=null){
            boolean enabled = bluetoothAdapter.isEnabled();
            if (enabled){
                boolean discovering = bluetoothAdapter.isDiscovering();
                if (!discovering){
                    lists.clear();
                    BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
                    bluetoothLeScanner.startScan(null,createScanSetting(),scanCallback);
                }
            }else {
                Toast.makeText(this,"蓝牙未开启，请开启蓝牙",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"蓝牙不可用", Toast.LENGTH_SHORT).show();
        }
    }

    public void OffBluetooth() {
        Log.d(TAG, "OnBluetooth: ljq -- close Bluetooth" );
        if (bluetoothAdapter!=null){
            boolean enabled = bluetoothAdapter.isEnabled();
            if (enabled){
                bluetoothAdapter.disable(); // 关闭蓝牙
            }
        }else {
            Toast.makeText(this,"蓝牙不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        Log.d(TAG, "Bluetooth: ljq -- init" );
        btn_on = (Button)findViewById(R.id.btn_on);
        btn_off = (Button)findViewById(R.id.btn_off);
        btn_scan = (Button)findViewById(R.id.btn_scan);

        btn_scan.setOnClickListener(this);
        btn_on.setOnClickListener(this);
        btn_off.setOnClickListener(this);


        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(bluetoothBroadCast,intentFilter);
    }

    public void OnBluetooth(){
        Log.d(TAG, "OnBluetooth: ljq -- onpen Bluetooth" );
        if (bluetoothAdapter!=null){
            boolean enabled = bluetoothAdapter.isEnabled();
            if (!enabled){
                bluetoothAdapter.enable(); // 打开蓝牙
            }
        }else {
            Toast.makeText(this,"蓝牙不可用", Toast.LENGTH_SHORT).show();
        }
    }
    private void printfBluetooth(){
        for (int i = 0; i < lists.size() ; i++ ){
            Log.d(TAG, "printfBluetooth: bluetooth : " + lists.get(i).getName() + "----" +  lists.get(i).getAddress() + "---" +
                    lists.get(i).getBluetoothClass() + "----" + lists.get(i).getBondState() + "-----" + lists.get(i) .getUuids()
            + "----" + lists.get(i).getType());
        }
    }
    class BluetoothBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive: action  ---" + action);
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {  //搜索到蓝牙设备
                BluetoothDevice btd = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                lists.add(btd);
            }else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
                Log.d(TAG, "onReceive:  lists " + lists.size());
                Log.d(TAG, "onReceive: --------");
                printfBluetooth();

            }
        }
    }
    public ScanSettings createScanSetting() {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        builder.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY);
        //builder.setReportDelay(100);//设置延迟返回时间
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES);
        }
        return builder.build();
    }

    public ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            Log.d(TAG, "onScanResult: == " + result.getScanRecord().getDeviceName().toString());
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };

}
