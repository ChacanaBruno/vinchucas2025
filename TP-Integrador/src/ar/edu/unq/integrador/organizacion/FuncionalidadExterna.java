package ar.edu.unq.integrador.organizacion;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.zonaDeCobertura.ZonaDeCobertura;

public interface FuncionalidadExterna {
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zona, Muestra muestra);
}
