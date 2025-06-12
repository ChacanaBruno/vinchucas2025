package ar.edu.unq.integrador.estadoMuestra;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;

public class NoVerificada implements EstadoMuestra{
	// Atributos
	
	// Constructor
	public NoVerificada() {}

	// Metodos
	@Override
	public void recibirOpinion(Opinion o, Muestra m) {
		if (o.esOpinionExperta()) {
			m.agregarOpinion(o);
			m.setEstadoMuestra(new EnEvaluacion());
		} else {
			m.agregarOpinion(o);
		}	
	};
	
	

}
