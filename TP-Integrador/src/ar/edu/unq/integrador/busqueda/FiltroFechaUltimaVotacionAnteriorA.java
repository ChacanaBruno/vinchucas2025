package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroFechaUltimaVotacionAnteriorA extends FiltroFecha {

	public FiltroFechaUltimaVotacionAnteriorA(LocalDate fecha) {
		super(fecha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.getFechaUltimaVotacion().isBefore(fecha);
	}
}
