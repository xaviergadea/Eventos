package info.wwwood.eventos.model.servicelayer.manager;

import info.wwwood.eventos.model.servicelayer.api.IEventoService;
import info.wwwood.eventos.model.servicelayer.impl.EventoService;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class ServiceManager {
    //SERVICIOS
    private IEventoService eventoService;

    //INICIALIZACIÓN DEL GESTOR DE SERVICIOS
    public IEventoService getEventoService(){
        if (eventoService==null){
            eventoService = new EventoService();
        }
        return eventoService;
    }
   
}
