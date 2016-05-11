package info.wwwood.eventos.model.persistencelayer.impl.sql.manager;

import android.content.Context;

import info.wwwood.eventos.model.persistencelayer.api.IEventoDAO;
import info.wwwood.eventos.model.persistencelayer.impl.sql.daos.EventoDAO;
import info.wwwood.eventos.model.persistencelayer.impl.sql.helpers.SQLiteDBHelper;
import info.wwwood.eventos.model.persistencelayer.manager.PersistenceManager;
import info.wwwood.eventos.utilitieslayer.AppUtils;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class SqlPersistenceManager extends PersistenceManager {

    private IEventoDAO eventoDAO;
    private SQLiteDBHelper dbHelper;
    private Context context;

    public SqlPersistenceManager(Context context){
        this.context=context;
        dbHelper=new SQLiteDBHelper(context, AppUtils.EVENTOS_DB,null,AppUtils.EVENTOS_DB_VERSION);
    }

    @Override
    public IEventoDAO getEventoDao() {
        if (eventoDAO==null ) {
            eventoDAO = new EventoDAO(dbHelper);
        }
        return eventoDAO;
    }
}
