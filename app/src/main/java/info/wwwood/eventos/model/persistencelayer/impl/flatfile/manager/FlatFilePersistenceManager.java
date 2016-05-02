package info.wwwood.eventos.model.persistencelayer.impl.flatfile.manager;

import android.content.Context;

import info.wwwood.eventos.model.persistencelayer.api.IEventoDAO;
import info.wwwood.eventos.model.persistencelayer.impl.flatfile.daos.EventoDAO;
import info.wwwood.eventos.model.persistencelayer.manager.PersistenceManager;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class FlatFilePersistenceManager extends PersistenceManager {

    private Context context;

    public FlatFilePersistenceManager(Context context){
        this.context=context;
    }
    private IEventoDAO eventoDAO;

    @Override
    public IEventoDAO getEventoDao() {
        if (eventoDAO==null){
            eventoDAO=new EventoDAO(context);
        }
        return eventoDAO;
    }
}
