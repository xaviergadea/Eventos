package info.wwwood.eventos.presentationlayer.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import java.util.Date;

import info.wwwood.eventos.model.businesslayer.entities.Asistencia;
import info.wwwood.eventos.model.businesslayer.entities.Posicion;

/**
 * Created by android-ed1 on 11/05/2016.
 */
public class GeoPositionListener implements LocationListener {

    private Asistencia asistencia;
    public GeoPositionListener(Asistencia asistencia){
        this.asistencia=asistencia;
    }

    @Override
    public void onLocationChanged(Location location) {
        Posicion posicion=new Posicion();
        posicion.setLatitud(location.getLatitude());
        posicion.setLongitud(location.getLongitude());
        posicion.setPrecision((int)location.getAccuracy());
        posicion.setFecha(new Date());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
