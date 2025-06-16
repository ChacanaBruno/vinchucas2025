package ar.edu.unq.integrador.busqueda;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.integrador.muestra.Muestra;

public class CriterioBusqueda {

	public List<FiltroDeBusqueda> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<FiltroDeBusqueda> filtros) {
		this.filtros = filtros;
	}

	private List<FiltroDeBusqueda> filtros = new ArrayList<>();

	public CriterioBusqueda(List<FiltroDeBusqueda> filtros) {
		this.setFiltros(filtros);
	}

	public CriterioBusqueda agregarFiltro(FiltroDeBusqueda filtro) {
		this.getFiltros().add(filtro);
		return this;
	}

	public boolean cumple(Muestra muestra) {
		return filtros.stream().allMatch(f -> f.cumple(muestra));
	}
}
