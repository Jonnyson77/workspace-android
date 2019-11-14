package com.example.myapplication11.controller;

import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;

import com.example.myapplication11.Bluetooth;

import java.util.ArrayList;
import java.util.List;

/**
 * Bluetooth controller center
 *
 * */

public class BluetoothController extends Application {

    private BluetoothManager mBluetoothManager ;
    private BluetoothAdapter mbluetoothAdapter ;
    private List<BluetoothDevice> lists = new ArrayList<>();

    private BluetoothController(){
        mBluetoothManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        mbluetoothAdapter = mBluetoothManager.getAdapter();
    }
    private static class SingleTonHoler{
        private static BluetoothController mBluetoothController = new BluetoothController();
    }

    public static BluetoothController getInstance(){
        return SingleTonHoler.mBluetoothController;
    }


     /**
      *
      * check bluetooth function is enable
      * */
    public boolean isBluetoothEnable(){
        if (mbluetoothAdapter != null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * check bluetooth switch is opened
     * */
    public boolean isBluetoothOpened(){
        return mbluetoothAdapter.isEnabled();
    }

    /**
     * check bluetooth is discovering
     *
     * */
    public boolean isBluetoothDiscovering(){
        assert (mbluetoothAdapter!=null);
        return mbluetoothAdapter.isDiscovering();
    }

    /**
     * open buletooth
     * */
    public void openBluetooth(Activity activity ,int requestCode){
        Intent intent =  new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent,requestCode);
    }

    /**
     *
     * open Bluetooth funnction of discovered
     * */

    public void openBluetoothDiscoverable(Context context){
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        context.startActivity(intent);
    }


    /**
     *
     * find Bluetooth device
     * */
    public void findBluetoothDevice(){
        assert (mbluetoothAdapter!=null);
        mbluetoothAdapter.startDiscovery();
    }

    /**
     * stop find bluetooth devices
     * */

    public void stopFindBluetooth(){
        assert(mbluetoothAdapter.isDiscovering());
        mbluetoothAdapter.cancelDiscovery();
    }

    /**
     * get bluetooth devices discovered;
     * */
    public List<BluetoothDevice> getDiscoveredDevices(){
       return lists;
    }

    /**
    *
    *  add bluetooth device discovered
    * */
    public void addDevices(BluetoothDevice device){
        lists.add(device);
    }

    /**
     * clean device lits
     * */

    public void cleanDeviceLists(){
        lists.clear();
    }

}
