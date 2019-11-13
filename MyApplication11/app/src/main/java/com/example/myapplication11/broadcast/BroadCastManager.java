package com.example.myapplication11.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadCastManager extends BroadcastReceiver {
    private String TAG = "BroadCastManager";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onReceive:  action --" + action);
        
    }
}
