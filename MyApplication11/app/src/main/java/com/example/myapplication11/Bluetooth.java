package com.example.myapplication11;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
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

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Bluetooth extends Activity implements View.OnClickListener{
    String TAG ="Bluetooth";
    BluetoothManager mBluetoothManager ;
    private BluetoothAdapter bluetoothAdapter ;
    private BluetoothBroadCast bluetoothBroadCast = new BluetoothBroadCast();
    private IntentFilter intentFilter = new IntentFilter();
    private BluetoothProfile.ServiceListener serviceListener;
    private BluetoothHeadset headset;
    private Button btn_on;
    private Button btn_off;
    private Button btn_scan;
    private Button btn_stop;
    private Button btn_scanle;
    private Button btn_paired;
    private BluetoothLeScanner bluetoothLeScanner;
    private List<BluetoothDevice> lists = new ArrayList<BluetoothDevice>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        bluetoothBroadCast = new BluetoothBroadCast();
        mBluetoothManager =(BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter =mBluetoothManager.getAdapter();
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
            case R.id.btn_stop :
                StopScanBluetooth();
                break;
            case R.id.btn_scanle :
                ScanBluetooth();
                break;
            case R.id.btn_paired :
                PairBluetooth();
                break;
            default:
                break;
        }
    }

    private void PairBluetooth() {
        // iPhone----48:3B:38:E0:FF:20
        Log.d(TAG, "PairBluetooth: --" );
        String address = "48:3B:38:E0:FF:20";
        for (int i =0 ;i<lists.size();i++){
           if (  lists.get(i).getAddress().equals(address)){
               BluetoothDevice device = lists.get(i);
               // device.createBond();

           }
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

     public void StopScanBluetooth(){
         Log.d(TAG, "OnBluetooth: ljq --stop scan Bluetooth" );
         if (bluetoothAdapter!=null){
             boolean enabled = bluetoothAdapter.isEnabled();
             if (enabled){
                 boolean discovering = bluetoothAdapter.isDiscovering();
                    Log.d(TAG, "OnBluetooth: ljq --discovering  "  + discovering );
                     bluetoothLeScanner.stopScan(scanCallback);
                     printfBluetooth();
             }else {
                 Toast.makeText(this,"蓝牙未扫描，请开启蓝牙扫描",Toast.LENGTH_SHORT).show();
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
                    Utils.checkPermission(Bluetooth.this,new String []{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    });
                    bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
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
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_scanle = (Button) findViewById(R.id.btn_scanle);
        btn_paired =(Button)findViewById(R.id.btn_paired);

        btn_paired.setOnClickListener(this);
        btn_scanle.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_scan.setOnClickListener(this);
        btn_on.setOnClickListener(this);
        btn_off.setOnClickListener(this);


        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED );
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
            if (lists.get(i).getName() == null) {
                Log.d(TAG, "printfBluetooth: bluetooth : " + lists.get(i).getAddress()  + "----" +  lists.get(i).getAddress() + "---" +
                        lists.get(i).getBluetoothClass() + "----" + lists.get(i).getBondState() + "-----" + lists.get(i) .getUuids()
                        + "----" + lists.get(i).getType());
            }else {
                Log.d(TAG, "printfBluetooth: bluetooth : " + lists.get(i).getName() + "----" +  lists.get(i).getAddress() + "---" +
                        lists.get(i).getBluetoothClass() + "----" + lists.get(i).getBondState() + "-----" + lists.get(i) .getUuids()
                        + "----" + lists.get(i).getType());

            }
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
            }else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED )){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d(TAG, "onReceive: device " +  device.getAddress());
                Log.d(TAG, "onReceive: device " +  device.getBondState());
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
            Log.d(TAG, "onScanResult: 1  == " + result.getDevice().getName());
            if (result.getDevice().getName() != null && result.getDevice().getAddress()!=null){
                if (!lists.contains(result.getDevice()))
                   lists.add(result.getDevice());
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            Log.d(TAG, "onScanResult:  2== " );
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.d(TAG, "onScanResult:  3== " );
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            Log.d(TAG, "onConnectionStateChange: status ; " +  status );
            Log.d(TAG, "onConnectionStateChange: newState ; " +  newState );
        }
    };
}
