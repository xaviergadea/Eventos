package info.wwwood.eventos.presentationlayer.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import info.wwwood.eventos.presentationlayer.androidextends.application.PueAndroidApplication;
import info.wwwood.eventos.presentationlayer.listeners.GeoPositionListener;

/**
 * Created by android-ed1 on 11/05/2016.
 */
public class GeoPositionService extends Service {

    private LocationManager locationManager;
    private GeoPositionListener geoPositionListener;

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        geoPositionListener=new GeoPositionListener(((PueAndroidApplication)getApplication()).getAsistenciaActual());
        iniciarPosicionamiento();
    }

    private void iniciarPosicionamiento(){
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0,geoPositionListener); //5000 segons i 0 metres de moviment
    }

    @Override
    public void onDestroy() {
        locationManager.removeUpdates(geoPositionListener);
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
