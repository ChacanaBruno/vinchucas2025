package ar.edu.unq.integrador.notificador;

import ar.edu.unq.integrador.zonaDeCobertura.copy.ZonaDeCobertura;

public class EventoRegistroEnZona extends Evento {
	// Constructor
	public EventoRegistroEnZona(Notificador m, ZonaDeCobertura z) {
		super(m,z);
	}
}
