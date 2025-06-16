package ar.edu.unq.integrador.busqueda;

import java.util.List;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroAnd extends FiltroBoolean {

	public FiltroAnd(List<FiltroDeBusqueda> filtros) {
		super(filtros);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return filtros.stream().allMatch(f -> f.cumple(muestra));
	}
}
