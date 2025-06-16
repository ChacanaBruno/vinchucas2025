package ar.edu.unq.integrador.zonaDeCobertura;

import java.util.List;

import ar.edu.unq.integrador.muestra.Muestra;

public interface INotificador {
	public void notificar(ZonaDeCobertura zona, List<Muestra> muestrasDentroDelRadio);

	public void subscribir(Interesado interesado);

	public void desuscribir(Interesado interesado);
}
