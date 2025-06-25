package ar.edu.unq.integrador.notificador;

import ar.edu.unq.integrador.zonaDeCobertura.copy.ZonaDeCobertura;

public class EventoVerificacionEnZona extends Evento {
	//Constructor
	public EventoVerificacionEnZona(Notificador m, ZonaDeCobertura z) {
		super(m,z);
	}
	
	@Override
	public boolean esVerificacion() {
		return true;
	}
}
