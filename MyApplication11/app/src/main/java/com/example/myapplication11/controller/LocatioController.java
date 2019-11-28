package com.example.myapplication11.controller;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import java.util.List;

public class LocatioController implements LocationListener {
    private LocationManager locationManager;

    public LocatioController (Context context){
        locationManager =(LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void getCurrentLocation(){
        List<String> providers = locationManager.getProviders(true);
        for (int i = 0 ;i< providers.size();i++){
            Location location = locationManager.getLastKnownLocation(providers.get(i));
        }

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
