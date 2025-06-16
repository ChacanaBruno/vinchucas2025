package ar.edu.unq.integrador.busqueda;

import java.util.List;

import ar.edu.unq.integrador.muestra.Muestra;

public abstract class FiltroBoolean implements FiltroDeBusqueda {

	protected List<FiltroDeBusqueda> filtros;

	public FiltroBoolean(List<FiltroDeBusqueda> filtros) {
		super();
		this.filtros = filtros;
	}

	@Override
	public abstract boolean cumple(Muestra muestra);

}
