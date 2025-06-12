package ar.edu.unq.integrador.estadoMuestra;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;

public interface EstadoMuestra {
	void recibirOpinion(Opinion o, Muestra m);
}
