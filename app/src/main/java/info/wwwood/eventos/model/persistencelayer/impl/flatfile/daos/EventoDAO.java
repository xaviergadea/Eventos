package info.wwwood.eventos.model.persistencelayer.impl.flatfile.daos;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.util.List;

import info.wwwood.eventos.model.businesslayer.entities.Evento;
import info.wwwood.eventos.model.persistencelayer.api.IEventoDAO;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public class EventoDAO implements IEventoDAO {

    private Context context;

    public EventoDAO(Context context){
        this.context=context; //HEM DE PASSAR CONTEXT PER PODER GUARDAR EL FITXER AL DIRECTORI DE L'APP
    }

    @Override
    public void eventosSave(List<Evento> eventos) {
        Gson gson=new Gson();
        String eventosJSON=gson.toJson(eventos);

        OutputStreamWriter osw=null;
        try {
            osw=new OutputStreamWriter(
                    context.openFileOutput("eventos.json",
                    Context.MODE_PRIVATE) //MODE_PRIVATE SOBREESCRIU L'ARXIU SENCER. MODE_APPEND AFEGEIX CONTINGUT A L'ARXIU.
            );
            osw.write(eventosJSON); //escribim json a l'arxiu

        } catch (Exception ex){
            throw new RuntimeException("Error al guardar en disco el fichero eventos.json");
        }
    }
}
