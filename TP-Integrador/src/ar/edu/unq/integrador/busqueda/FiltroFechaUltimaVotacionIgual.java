package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroFechaUltimaVotacionIgual extends FiltroFecha {

	public FiltroFechaUltimaVotacionIgual(LocalDate fecha) {
		super(fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.getFechaUltimaVotacion().isEqual(fecha);
	}
}