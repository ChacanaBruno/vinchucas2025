package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroFechaCreacionAnteriorA extends FiltroFecha {
	
	public FiltroFechaCreacionAnteriorA(LocalDate fecha) {
		super(fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.getFecha().isBefore(fecha);
	}
}