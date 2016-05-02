package info.wwwood.eventos.model.persistencelayer.manager;

import android.content.Context;

import info.wwwood.eventos.model.persistencelayer.api.IEventoDAO;
import info.wwwood.eventos.model.persistencelayer.impl.flatfile.manager.FlatFilePersistenceManager;
import info.wwwood.eventos.model.persistencelayer.impl.rest.manager.RestPersistenceManager;
import info.wwwood.eventos.model.persistencelayer.impl.sql.manager.SqlPersistenceManager;
import info.wwwood.eventos.utilitieslayer.AppUtils;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public abstract class PersistenceManager {

    public abstract IEventoDAO getEventoDao();

    public static PersistenceManager getPersistenceManger(AppUtils.PersistenceTechnologies persistenceTechnologies,Context context){
        PersistenceManager persistenceManager=null;
        switch (persistenceTechnologies){
            case FLAT_FILES:
                persistenceManager=new FlatFilePersistenceManager(context);
                break;
            case REST:
                persistenceManager=new RestPersistenceManager();
                break;
            case SQL:
                persistenceManager=new SqlPersistenceManager();
                break;
        }
        return persistenceManager;
    }

}
