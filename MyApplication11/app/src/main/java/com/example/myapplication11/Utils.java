package com.example.myapplication11;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Utils {
    public static void checkPermission(Activity activity, String[] permissions) {
        int permission_granted = PackageManager.PERMISSION_GRANTED;
        boolean flag = false;
        for (int i=0;i<permissions.length;i++){
            int checkPermission = ActivityCompat.checkSelfPermission(activity,permissions[i]);
            if(permission_granted != checkPermission){
                flag = true;
                break;
            }
        }
        if(flag){
            ActivityCompat.requestPermissions(activity,permissions,111);
            return;
        }
    }
}
