package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroFechaCreacionAnteriorA implements FiltroDeBusqueda {
	private LocalDate fecha;

	public FiltroFechaCreacionAnteriorA(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.getFecha().isBefore(fecha);
	}
}