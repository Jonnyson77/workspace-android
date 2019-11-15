package com.example.myapplication11;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.myapplication11.controller.BluetoothController;
import java.lang.reflect.Method;

public class Bluetooth extends Activity implements View.OnClickListener{
    String TAG ="BluetoothController";


    public static final int REQUEST_CODE = 0;
    private BluetoothBroadCast bluetoothBroadCast = new BluetoothBroadCast();
    private Button btn_on;
    private Button btn_off;
    private Button btn_scan;
    private Button btn_stop;
    private Button btn_scanle;
    private Button btn_paired;
    private Button btn_paired_cancle;

    private BluetoothController mBluetoothController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        mBluetoothController = BluetoothController.getInstance();
        initUI();
        registerBluetoothReceiverr();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bluetoothBroadCast);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_on:
                OnBluetooth();
                break;
            case R.id.btn_off:
                OffBluetooth();
                break;
            case R.id.btn_scan:
                ScanBluetooth();
                break;
            case R.id.btn_stop :
                StopScanBluetooth();
                break;
            case R.id.btn_scanle :
                bluetoothCanSee();
                break;
            case R.id.btn_paired :
                PairBluetooth();
                break;
            case R.id.btn_paired_cancle :
                PairBluetoothCancle();
                break;
            default:
                break;
        }
    }

    private void PairBluetooth() {
        // iPhone----48:3B:38:E0:FF:20
        Log.d(TAG, "PairBluetooth: -- 开始配对" );
        String address = "48:3B:38:E0:FF:20";
       for (int i =0 ;i<mBluetoothController.getDiscoveredDevices().size();i++){
           if (  mBluetoothController.getDiscoveredDevices().get(i).getAddress().equals(address)){
               BluetoothDevice device = mBluetoothController.getDiscoveredDevices().get(i);
               device.createBond();
           }
        }

    }


    private void PairBluetoothCancle() {
        // iPhone----48:3B:38:E0:FF:20
        Log.d(TAG, "PairBluetoothCancle : -- 解除配对" );
        String address = "48:3B:38:E0:FF:20";
        for (int i =0 ;i<mBluetoothController.getDiscoveredDevices().size();i++){
            if (  mBluetoothController.getDiscoveredDevices().get(i).getAddress().equals(address)){
                BluetoothDevice device = mBluetoothController.getDiscoveredDevices().get(i);
                try {
                    Thread.sleep(3000);
                    Log.d(TAG, "PairBluetoothCancle : -- 开始解除配对" );
                    Method method = device.getClass().getMethod("removeBond", (Class[]) null);
                    method.invoke(device,(Object[]) null);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }


    public void ScanBluetooth() {
        Log.d(TAG, "OnBluetooth: ljq -- scan BluetoothController" );
        if (mBluetoothController.isBluetoothOpened()){
            mBluetoothController.findBluetoothDevice();
        }else {
            Toast.makeText(this,"蓝牙未开启用", Toast.LENGTH_SHORT).show();
        }
    }

    public void bluetoothCanSee() {
        Log.d(TAG, "OnBluetooth: ljq -- Bluetooth can see" );
        mBluetoothController.openBluetoothDiscoverable(this);
    }


    public void StopScanBluetooth(){
         Log.d(TAG, "OnBluetooth: ljq --stop scan BluetoothController" );
         mBluetoothController.stopFindBluetooth();
     }


    public void OffBluetooth() {
        Log.d(TAG, "OnBluetooth: ljq -- close BluetoothController" );
        mBluetoothController.closeBluetooth();
    }

    private void initUI(){
        Log.d(TAG, "Bluetooth : ljq -- initUI" );
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

    public void OnBluetooth(){
        Log.d(TAG, "OnBluetooth: ljq -- onpen BluetoothController" );
        if (mBluetoothController.isBluetoothEnable()){
            mBluetoothController.openBluetooth(this,REQUEST_CODE);
        }else {
            Toast.makeText(this,"蓝牙不可用", Toast.LENGTH_SHORT).show();
        }
    }
    private void printfBluetooth(){
        for (int i = 0; i < mBluetoothController.getDiscoveredDevices().size() ; i++ ){
            if (mBluetoothController.getDiscoveredDevices().get(i).getName() == null) {
                Log.d(TAG, "printfBluetooth: bluetooth : " + mBluetoothController.getDiscoveredDevices().get(i).getAddress()  + "----" +  mBluetoothController.getDiscoveredDevices().get(i).getAddress() + "---" +
                        mBluetoothController.getDiscoveredDevices().get(i).getBluetoothClass() + "----" + mBluetoothController.getDiscoveredDevices().get(i).getBondState() + "-----" + mBluetoothController.getDiscoveredDevices().get(i) .getUuids()
                        + "----" + mBluetoothController.getDiscoveredDevices().get(i).getType());
            }else {
                Log.d(TAG, "printfBluetooth: bluetooth : " + mBluetoothController.getDiscoveredDevices().get(i).getName() + "----" +  mBluetoothController.getDiscoveredDevices().get(i).getAddress() + "---" +
                        mBluetoothController.getDiscoveredDevices().get(i).getBluetoothClass() + "----" + mBluetoothController.getDiscoveredDevices().get(i).getBondState() + "-----" + mBluetoothController.getDiscoveredDevices().get(i) .getUuids()
                        + "----" + mBluetoothController.getDiscoveredDevices().get(i).getType());

            }
        }
    }
    class BluetoothBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive: action  ---" + action);

            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)){
                Log.d(TAG, "onReceive: 开始扫描");
                // 初始化列表
                mBluetoothController.cleanDeviceLists();

            }else if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                // 搜索到蓝牙设备
                BluetoothDevice btd = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 添加进容器
                mBluetoothController.addDevices(btd);
            }else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
                Log.d(TAG, "onReceive:  mBluetoothController.getDiscoveredDevices() " + mBluetoothController.getDiscoveredDevices().size());

                printfBluetooth();
            }else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED )){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device == null){
                    Toast.makeText(Bluetooth.this,"无设别",Toast.LENGTH_SHORT).show();
                    return;
                }
                int status = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, 0);
                if(status == BluetoothDevice.BOND_BONDED) {
                    // Toast.makeText(Bluetooth.this,"已经绑定"+device.getName(),Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onReceive:  已经绑定设备 ：" + device.getName() );
                } else if(status == BluetoothDevice.BOND_BONDING) {
                    Log.d(TAG, "onReceive:  正在绑定设备 ：" + device.getName() );

                } else if(status == BluetoothDevice.BOND_NONE) {
                    Log.d(TAG, "onReceive:  未绑定设备 ：" + device.getName() );

                }
            }else if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)){
                Log.d(TAG, "onReceive:  扫描模式 ：" );
                int extra = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE,0);
                Log.d(TAG, "onReceive: SCAN_MODE :" + extra);
            }
        }
    }

    private void registerBluetoothReceiverr(){
        IntentFilter intentFilter = new IntentFilter();
        // 查找设备
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        // 绑定状态改变
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED );
        // 查找完成
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        // 查找开始
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        // 扫描模式改变
        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);

        registerReceiver(bluetoothBroadCast,intentFilter);
    }
}
