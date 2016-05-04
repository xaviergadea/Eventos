package info.wwwood.eventos.model.servicelayer.api;

import java.text.ParseException;
import java.util.List;

import info.wwwood.eventos.model.businesslayer.entities.Evento;
import info.wwwood.eventos.model.businesslayer.entities.Participante;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public interface IEventoService {
    List<Evento> createInitialLocalEventos() throws ParseException;
    Evento getEventoByDorsal(String Dorsal) throws ParseException;
}
