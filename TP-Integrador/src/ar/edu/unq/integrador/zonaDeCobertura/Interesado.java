package ar.edu.unq.integrador.zonaDeCobertura;

import ar.edu.unq.integrador.muestra.Muestra;

public interface Interesado {
	public void subscribirseAZona(ZonaDeCobertura zona);

	public void desubscribirseAZona(ZonaDeCobertura zona);

	public void recibirNotifacionDeMuestra(ZonaDeCobertura zona, Muestra muestra);

	public void recibirNotifacionDeMuestraValidada(ZonaDeCobertura zona, Muestra muestra);
}
