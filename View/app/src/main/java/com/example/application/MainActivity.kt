package com.example.application

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
    // 检查设备是否安装有该组件
    fun checkActivityEmpety(intent: Intent,flag: Int): Boolean{
        var packageManager = application.packageManager
        if (packageManager.resolveActivity(intent,PackageManager.MATCH_DEFAULT_ONLY) == null){
            return false
        }
        return true
    }

    fun checkServiceEmpety(intent: Intent,flag: Int): Boolean{
        var packageManager = application.packageManager
        if (packageManager.resolveService(intent,PackageManager.MATCH_DEFAULT_ONLY) == null){
            return false
        }
        return true
    }
    
}
