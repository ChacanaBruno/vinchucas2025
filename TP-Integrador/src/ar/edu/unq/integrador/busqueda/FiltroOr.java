package ar.edu.unq.integrador.busqueda;

import java.util.List;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroOr extends FiltroBoolean {

	public FiltroOr(List<FiltroDeBusqueda> filtros) {
		super(filtros);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return filtros.stream().anyMatch(f -> f.cumple(muestra));
	}
}
