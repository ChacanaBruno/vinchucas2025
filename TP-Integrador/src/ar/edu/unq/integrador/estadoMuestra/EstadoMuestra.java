package ar.edu.unq.integrador.estadoMuestra;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;

public interface EstadoMuestra {
	void recibirOpinion(Opinion o, Muestra m);
	Concepto resultadoActual(Muestra m);
	boolean esVerificada();
}
