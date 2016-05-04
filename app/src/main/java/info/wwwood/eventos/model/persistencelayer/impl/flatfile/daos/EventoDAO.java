package info.wwwood.eventos.model.persistencelayer.impl.flatfile.daos;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import info.wwwood.eventos.model.businesslayer.entities.Evento;
import info.wwwood.eventos.model.businesslayer.entities.Participante;
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
            osw.close();

        } catch (Exception ex){
            throw new RuntimeException("Error al guardar en disco el fichero eventos.json");
        }
    }
    public Evento getEventoByDorsal(String dorsal){
        Gson gson=new Gson();
        InputStreamReader isr=null;
        BufferedReader fin=null;

        try{
            int indexReturn=-1;
            List<Participante> participantes = new ArrayList<Participante>();
            Participante participante=new Participante();

            isr=new InputStreamReader(context.openFileInput("eventos.json"));
            fin=new BufferedReader(isr);
            String texto = fin.readLine();
            isr.close();

            Evento eventoReturn=new Evento();
            Evento[]  eventos = gson.fromJson(texto, Evento[] .class);
            for (int i=0;i<eventos.length;i++){
                participantes = eventos[i].getInscritos();

                for (int k=0;k<participantes.size();k++) {
                    participante=participantes.get(k);
                    if (participante.getDorsal().toString().equals(dorsal)){
                        Participante obj=eventos[i].getInscritos().get(k);
                        eventos[i].getInscritos().clear();
                        eventos[i].getInscritos().add(obj);
                        indexReturn=i;
                    }
                }
                int k=1;
            }
            if(indexReturn==-1){
                return null;
            } else {
                eventoReturn = eventos[indexReturn];
                return eventoReturn;
            }

        } catch (Exception ex){
            throw new RuntimeException("Error al leer el fichero eventos.json");

        }


    }
}
