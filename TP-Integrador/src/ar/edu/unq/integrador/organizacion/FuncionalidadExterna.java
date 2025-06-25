package ar.edu.unq.integrador.organizacion;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.notificador.Notificador;
import ar.edu.unq.integrador.zonaDeCobertura.copy.ZonaDeCobertura;

public interface FuncionalidadExterna {
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zona, Notificador muestra);
}
