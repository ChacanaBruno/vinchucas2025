package ar.edu.unq.integrador.busqueda;

import java.util.List;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroOr implements FiltroDeBusqueda {
	private List<FiltroDeBusqueda> filtros;

	public FiltroOr(List<FiltroDeBusqueda> filtro) {
		this.filtros = filtro;
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return filtros.stream().anyMatch(f -> f.cumple(muestra));
	}
}
