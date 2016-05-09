package info.wwwood.eventos.presentationlayer.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import info.wwwood.eventos.R;
import info.wwwood.eventos.model.businesslayer.entities.Asistencia;
import info.wwwood.eventos.model.businesslayer.entities.Evento;
import info.wwwood.eventos.model.businesslayer.entities.Participante;
import info.wwwood.eventos.model.businesslayer.entities.Sesion;
import info.wwwood.eventos.model.servicelayer.manager.ServiceManager;
import info.wwwood.eventos.presentationlayer.androidextends.application.PueAndroidApplication;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private PueAndroidApplication app;
    private ServiceManager serviceManager;

    private Button main_btBuscar=null;
    private EditText main_etDorsal=null;
    private TextView main_tvNombre=null;
    private ImageButton main_ibStartStop=null;

    private Timer timer=new Timer();
    private final long DELAY = 1000; // milliseconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app=(PueAndroidApplication) getApplication();
        serviceManager=app.getServiceManager();

        try {
            app.setEventos(serviceManager.getEventoService().createInitialLocalEventos());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        main_etDorsal=(EditText) findViewById(R.id.main_etDorsal);
        main_tvNombre=(TextView) findViewById(R.id.main_tvNombre);

        main_btBuscar=(Button) findViewById((R.id.main_btBuscar));
        main_btBuscar.setOnClickListener(this);

        main_ibStartStop=(ImageButton) findViewById(R.id.main_ibStartStop);
        main_ibStartStop.setOnClickListener(this);

        main_etDorsal.addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btBuscar:
                String dorsal = main_etDorsal.getText().toString();
                List<Evento> evento = null;
                app = (PueAndroidApplication) getApplication();
                serviceManager = app.getServiceManager();

                try {
                    app.setEvento(serviceManager.getEventoService().getEventoByDorsal(dorsal));
                    Participante participante = new Participante();
                    participante = app.getEvento().getInscritos().get(0);
                    main_tvNombre.setText(participante.getNombre());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.main_ibStartStop:
                try {
                    app.setAsistenciaActual(serviceManager.getEventoService().addCurrentAsistenciaToEvent(app.getEvento()));
                } catch (Exception ex) {

                }
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (getCurrentFocus()==main_etDorsal) {
            timer.cancel();
            timer = new Timer();
            timer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            new SearchCorredor().execute(main_etDorsal.getText().toString(), app, main_tvNombre, main_ibStartStop);
                        }
                    },
                    DELAY
            );
        }
    }
}
class SearchCorredor extends AsyncTask<Object, Object, Evento> //els 3 objects són per als 3 mètodes implementats a la classe
{
    private PueAndroidApplication app;
    private ServiceManager serviceManager;

    private TextView main_tvNombre=null;
    private ImageButton main_ibStartStop=null;
    private Evento evento=null;
    @Override
    protected Evento doInBackground(Object... params) { //params és un array de paràmetres de 0 a n
        app=(PueAndroidApplication) params[1];
        //PueAndroidApplication app=(PueAndroidApplication) params[1];
        serviceManager=app.getServiceManager();
        String dorsal=params[0].toString();
        main_tvNombre=(TextView) params[2];
        main_ibStartStop=(ImageButton) params[3];

        try {
            evento=serviceManager.getEventoService().getEventoByDorsal(dorsal);
            app.setEvento(evento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return evento;

    }

    @Override
    protected void onProgressUpdate(Object... values) {

    }

    @Override
    protected void onPostExecute(Evento result) {

        super.onPostExecute(result);

        try {
            if (app.getEvento()==null) {
                main_tvNombre.setText("");
                main_ibStartStop.setVisibility(View.INVISIBLE);
            } else {
                Participante participante=new Participante();
                participante = app.getEvento().getInscritos().get(0);
                main_tvNombre.setText(participante.getNombre());
                main_ibStartStop.setVisibility(View.VISIBLE);
            }

        } catch (Exception ex){


        }

    }

}
