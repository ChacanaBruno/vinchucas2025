package ar.edu.unq.integrador.organizacion;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.notificador.Notificador;
import ar.edu.unq.integrador.notificador.Evento;
import ar.edu.unq.integrador.notificador.Interesado;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.zonaDeCobertura.copy.*;

public class Organizacion {
	private Ubicacion ubicacion;
	private TipoOrganizacion organizacion;
	private int personas;
	private FuncionalidadExterna pluginCarga;
	private FuncionalidadExterna pluginValidacion;
	
	// Constructores
	public Organizacion(Ubicacion ubi, TipoOrganizacion tipo, int per, FuncionalidadExterna pluginCarga, FuncionalidadExterna pluginValidacion) {
		this.setUbicacion(ubi);
		this.setOrganizacion(tipo);
		this.setPersonas(per);
		this.setPluginCarga(pluginCarga);
		this.setPluginValidacion(pluginValidacion);	
	}
	
	public Organizacion(Ubicacion ubi, TipoOrganizacion tipo, int per) { // por defecto no tiene plugins!
		this.setUbicacion(ubi);
		this.setOrganizacion(tipo);
		this.setPersonas(per);
	}
		
	
	// Accessing
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public TipoOrganizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(TipoOrganizacion organizacion) {
		this.organizacion = organizacion;
	}
	public int getPersonas() {
		return personas;
	}
	public void setPersonas(int personas) {
		this.personas = personas;
	}
	public FuncionalidadExterna getPluginCarga() {
		return pluginCarga;
	}
	public void setPluginCarga(FuncionalidadExterna pluginCarga) {
		this.pluginCarga = pluginCarga;
	}
	public FuncionalidadExterna getPluginValidacion() {
		return pluginValidacion;
	}
	public void setPluginValidacion(FuncionalidadExterna pluginValidacion) {
		this.pluginValidacion = pluginValidacion;
	}
	
	public void procesarEvento(Evento e) {
		if(!e.esVerificacion()) {
			this.getPluginCarga().nuevoEvento(this, e.getZona(), e.getMuestra());
		} else {
			this.getPluginValidacion().nuevoEvento(this, e.getZona(), e.getMuestra());
		}
	}
	
}
