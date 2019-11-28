package com.example.myapplication11.controller;


import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WifiController {

    private final static String TAG = "WifiController";
    private WifiManager mWifiManager;
    private WifiInfo mWifiInfo;
    private WifiManager.WifiLock mWifiLock;
    private ScanResult mScanResult ;
    private List<ScanResult> lists ;
    private StringBuffer mStringBuffer = new StringBuffer();

    public WifiController(Context context){
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        mWifiInfo =mWifiManager.getConnectionInfo();
    }


    /**
     * open netCard
     * */

    public void openNetCard(){
        if (!mWifiManager.isWifiEnabled()){
            Log.d(TAG, "openNetCard: net card is not opened");
            mWifiManager.setWifiEnabled(true);
        }else {
            Log.d(TAG, "openNetCard: net card is opened");
        }
    }

    /**
     * close netCard
     * */

    public void closeNetCard(){
        if (mWifiManager.isWifiEnabled()){
            mWifiManager.setWifiEnabled(false);
        }else {
            Log.d(TAG, "openNetCard: net card is not opened");
        }
    }
    /**
     * check net card state ..
     *
     */
    
    public void checkNetCardState(){
        if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING){
            Log.d(TAG, "checkNetCardState: net card is closing....");
        }else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED){
            Log.d(TAG, "checkNetCardState: net card is closed.");
        }else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING){
            Log.d(TAG, "checkNetCardState: net card is opening...");
        }else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED){
            Log.d(TAG, "checkNetCardState: net card is opened.");
        }else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_UNKNOWN){
            Log.d(TAG, "checkNetCardState: unknown wifistate ....");
        }else {
            Log.d(TAG, "checkNetCardState:  default ...........");
        }
    }
    
    /**
     * 
     * scan wifi net  
     * */
    
    public void scan(){
        if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED){
            mWifiManager.startScan();
            lists = mWifiManager.getScanResults();
            if (lists !=null){
                Log.d(TAG, "scan: there is have wifi net ");
            }else {
                Log.d(TAG, "scan: there is not have wifi net ");
            }        
        }else {
            Log.d(TAG, "scan: wifi is not opened ");
        }
    }

    /**
     * get scan results
     * */
    public String getScanResult(){
       if (mStringBuffer != null){
           mStringBuffer = new StringBuffer();
       }
         scan();
        if (lists.size() > 0){
            lists.clear();
        }
        lists = mWifiManager.getScanResults();
       if (lists !=null){
           for (int i = 0; i < lists.size();i++ ){
               mScanResult = lists.get(i);
               mStringBuffer = mStringBuffer.append("NO.").append(i+1).append(":")
                       .append(mScanResult.SSID).append("->")
                       .append(mScanResult.BSSID).append("->")
                       .append(mScanResult.capabilities).append("->")
                       .append(mScanResult.frequency).append("->")
                       .append(mScanResult.level).append("->")
                       .append(mScanResult.describeContents()).append("\n\n");

           }
       }
        Log.d(TAG, "getScanResult:  scanResults  :    " + mStringBuffer.toString());
       return mStringBuffer.toString();
    }
    /**
     * 
     * 
     * check wifi state 
     * 
     * */
    
    public void checkWifiState(){
        if (mWifiInfo != null){
            Log.d(TAG, "checkWifiState: wifi is normal");
        } else {
            Log.d(TAG, "checkWifiState: wifi is error");

        }    
    }
    
}



