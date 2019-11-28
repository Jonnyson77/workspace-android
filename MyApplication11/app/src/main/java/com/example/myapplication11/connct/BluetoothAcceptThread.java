package com.example.myapplication11.connct;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import com.example.myapplication11.Bluetooth;
import com.example.myapplication11.R;

import java.io.IOException;
import java.util.UUID;

public class BluetoothAcceptThread extends Thread {
    private final BluetoothServerSocket mmServerSocket;

    private  BluetoothManagerConnectedThread bluetoothManager;

    public BluetoothAcceptThread(BluetoothAdapter mBluetoothAdapter) {
        BluetoothServerSocket tmp = null;

        try {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("BluetoothServer", UUID.fromString("MyApplication11"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmServerSocket = tmp;
    }
    @Override
    public void run() {
        super.run();
        BluetoothSocket bluetoothSocket =null;

        while (true){
            try {
                bluetoothSocket = mmServerSocket.accept();
                if (bluetoothSocket!=null){
                    bluetoothManager = new BluetoothManagerConnectedThread(bluetoothSocket);
                    bluetoothManager.run();
                    mmServerSocket.close();
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
