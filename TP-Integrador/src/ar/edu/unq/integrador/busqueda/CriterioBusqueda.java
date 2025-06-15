package ar.edu.unq.integrador.busqueda;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.integrador.muestra.Muestra;

public class CriterioBusqueda {

	private List<FiltroDeBusqueda> filtros = new ArrayList<>();

	public CriterioBusqueda(List<FiltroDeBusqueda> filtros) {
		this.filtros = filtros;
	}

	public CriterioBusqueda agregarFiltro(FiltroDeBusqueda filtro) {
		filtros.add(filtro);
		return this;
	}

	public boolean cumple(Muestra muestra) {
		return filtros.stream().allMatch(f -> f.cumple(muestra));
	}
}
