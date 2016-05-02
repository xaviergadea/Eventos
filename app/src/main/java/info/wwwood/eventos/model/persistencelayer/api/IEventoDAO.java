package info.wwwood.eventos.model.persistencelayer.api;

import java.util.List;

import info.wwwood.eventos.model.businesslayer.entities.Evento;

/**
 * Created by android-ed1 on 02/05/2016.
 */
public interface IEventoDAO {
    void eventosSave(List<Evento>eventos);


}
