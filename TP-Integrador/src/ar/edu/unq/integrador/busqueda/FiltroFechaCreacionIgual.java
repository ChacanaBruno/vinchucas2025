package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroFechaCreacionIgual extends FiltroFecha {

	public FiltroFechaCreacionIgual(LocalDate fecha) {
		super(fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.getFecha().isEqual(fecha);
	}
}