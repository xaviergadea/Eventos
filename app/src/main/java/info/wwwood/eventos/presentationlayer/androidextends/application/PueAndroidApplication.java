package info.wwwood.eventos.presentationlayer.androidextends.application;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import info.wwwood.eventos.model.businesslayer.entities.Evento;
import info.wwwood.eventos.model.servicelayer.manager.ServiceManager;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class PueAndroidApplication extends Application {
    private ServiceManager serviceManager;
    private List<Evento> eventos;
    private Evento evento;

    public List<Evento> getEventos() {
        return eventos;
    }
    public Evento getEvento() {
        return evento;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    public void setEvento(Evento evento) {

        this.evento=evento;
    }
    /*public Evento getEventoByDorsal(String dorsal) {
        this.evento = evento;
    }*/
    public ServiceManager getServiceManager() {
        return serviceManager;
    }
    public PueAndroidApplication(){
        super(); // crida al constructor de la classe application
        eventos=new ArrayList<Evento>();
        serviceManager=new ServiceManager(this);
    }
}