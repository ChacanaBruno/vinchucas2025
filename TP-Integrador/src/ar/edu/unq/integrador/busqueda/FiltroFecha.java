package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public abstract class FiltroFecha implements FiltroDeBusqueda {
	protected LocalDate fecha;

	public FiltroFecha(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}

	public abstract boolean cumple(Muestra muestra);
}
