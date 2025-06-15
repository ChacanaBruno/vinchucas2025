package ar.edu.unq.integrador.busqueda;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroNivelVerificacion implements FiltroDeBusqueda {
	// Ej: "verificada" o "votada"

	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.estaVerificada();
	}

}