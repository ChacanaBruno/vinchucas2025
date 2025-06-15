package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroFechaUltimaVotacionIgual implements FiltroDeBusqueda {
	private LocalDate fecha;

	public FiltroFechaUltimaVotacionIgual(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.getFechaUltimaVotacion().isEqual(fecha);
	}
}