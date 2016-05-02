package info.wwwood.eventos.model.businesslayer.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import info.wwwood.eventos.model.businesslayer.entities.base.EntityBase;

/**
 * Created by android-ed1 on 27/04/2016.
 */
public class Sesion extends EntityBase {
    private Date fechaInicio;
    private Date fechafin;
    private List<Asistencia> asistencias;


    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }
    public Sesion(){
        fechaInicio=null;
        fechafin=null;
        asistencias=new ArrayList<Asistencia>();
    }
}
