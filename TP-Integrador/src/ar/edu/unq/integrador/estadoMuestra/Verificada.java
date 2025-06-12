package ar.edu.unq.integrador.estadoMuestra;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;

public class Verificada implements EstadoMuestra {
	// Atributos
	
	// Constructor
	public Verificada() {};
	
	// Metodos
	@Override
	public void recibirOpinion(Opinion o, Muestra m) {
		throw new RuntimeException("No se aceptan mas opiniones");
	}

}
