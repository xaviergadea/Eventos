package info.wwwood.eventos.model.servicelayer.manager;

import android.content.Context;

import info.wwwood.eventos.model.persistencelayer.impl.flatfile.manager.FlatFilePersistenceManager;
import info.wwwood.eventos.model.persistencelayer.impl.rest.manager.RestPersistenceManager;
import info.wwwood.eventos.model.persistencelayer.impl.sql.manager.SqlPersistenceManager;
import info.wwwood.eventos.model.persistencelayer.manager.PersistenceManager;
import info.wwwood.eventos.model.servicelayer.api.IEventoService;
import info.wwwood.eventos.model.servicelayer.impl.EventoService;
import info.wwwood.eventos.utilitieslayer.AppUtils;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class ServiceManager {
    //SINGLETONES DE GESTORES DE PERSISTENCIA
    private FlatFilePersistenceManager flatFilePersistenceManager;
    private RestPersistenceManager restPersistenceManager;
    private SqlPersistenceManager sqlPersistenceManager;

    public ServiceManager(Context context){
        this.context=context;
        flatFilePersistenceManager=(FlatFilePersistenceManager) PersistenceManager.getPersistenceManger(AppUtils.PersistenceTechnologies.FLAT_FILES,context);
        restPersistenceManager=(RestPersistenceManager) PersistenceManager.getPersistenceManger(AppUtils.PersistenceTechnologies.REST,context);
        sqlPersistenceManager=(SqlPersistenceManager) PersistenceManager.getPersistenceManger(AppUtils.PersistenceTechnologies.SQL,context);
                
    }

    private Context context;
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
