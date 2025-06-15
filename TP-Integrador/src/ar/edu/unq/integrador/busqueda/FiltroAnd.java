package ar.edu.unq.integrador.busqueda;


import java.util.List;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroAnd implements FiltroDeBusqueda {
	private List<FiltroDeBusqueda> filtros;

	public FiltroAnd(List<FiltroDeBusqueda> filtros) {
	    this.filtros = filtros;
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return filtros.stream().allMatch(f -> f.cumple(muestra));
	}
}
