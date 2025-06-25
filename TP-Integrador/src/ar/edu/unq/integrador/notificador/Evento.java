package ar.edu.unq.integrador.notificador;

import ar.edu.unq.integrador.zonaDeCobertura.copy.ZonaDeCobertura;

public abstract class Evento {
	// Atributos
	private Notificador muestra;
	private ZonaDeCobertura zona;
	
	// Constructor
	public Evento(Notificador m, ZonaDeCobertura z) {
		this.setMuestra(m);
		this.setZona(z);
	}
	
	// Accessing
	public Notificador getMuestra() {
		return muestra;
	}
	public void setMuestra(Notificador muestra) {
		this.muestra = muestra;
	}
	public ZonaDeCobertura getZona() {
		return zona;
	}
	public void setZona(ZonaDeCobertura zona) {
		this.zona = zona;
	}
	
	// Hook
	public boolean esVerificacion() {
		return false;
	}
}
