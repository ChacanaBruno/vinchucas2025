package ar.edu.unq.integrador.zonaDeCobertura.copy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.notificador.*;
import ar.edu.unq.integrador.organizacion.Organizacion;

public class ZonaDeCobertura implements Interesado {
	// Atributos
	private String nombreIdentificador;
	private Ubicacion epicentro;
	private double radioKm;
	private List<Muestra> muestrasEnLaZona;
	private List<Organizacion> organizaciones;
	
	// Constructores
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radioKm, List<Muestra> muestrasEnLaZona, List<Organizacion> organizaciones) {
		this.setNombreIdentificador(nombre);
		this.setEpicentro(epicentro);
		this.setRadioKm(radioKm);
		this.setMuestrasEnLaZona(muestrasEnLaZona);
		this.setOrganizaciones(organizaciones);
	}
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radioKm) { // Default
		this.setNombreIdentificador(nombre);
		this.setEpicentro(epicentro);
		this.setRadioKm(radioKm);
		this.setMuestrasEnLaZona(new ArrayList<>());
		this.setOrganizaciones(new ArrayList<>());
	}

	// Accessing
	public String getNombreIdentificador() {
		return nombreIdentificador;
	}

	public void setNombreIdentificador(String nombreIdentificador) {
		this.nombreIdentificador = nombreIdentificador;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public void setEpicentro(Ubicacion epicentro) {
		this.epicentro = epicentro;
	}

	public double getRadioKm() {
		return radioKm;
	}

	public void setRadioKm(double radioKm) {
		this.radioKm = radioKm;
	}

	public List<Muestra> getMuestrasEnLaZona() {
		return muestrasEnLaZona;
	}

	public void setMuestrasEnLaZona(List<Muestra> muestrasEnLaZona) {
		this.muestrasEnLaZona = muestrasEnLaZona;
	}

	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	/**
	 * Indica si esta zona se solapa con otra zona, es decir, si la distancia entre
	 * epicentros es menor que la suma de los radios.
	 */
	public boolean seSolapaCon(ZonaDeCobertura otraZona) { // Conservar
		double distanciaEpicentros = this.getEpicentro().distanciaALaUbicacion(otraZona.getEpicentro());
		return distanciaEpicentros <= (this.getRadioKm() + otraZona.getRadioKm());
	}
	
	public boolean perteneceALaZonaDeCobertura(Muestra m) { // mira si la muestra se encuentra dentro del area de la zona de cobertura
	    Ubicacion ubicacionMuestra = m.getUbicacion();
	    
	    double distancia = epicentro.distanciaALaUbicacion(ubicacionMuestra);
	    
	    return distancia <= radioKm;
	}
	
	public boolean estaRegistradaEnLaZonaDeCobertura(Muestra m) { // mira si la muestra ya se encuentra en la coleccion de muestras de la zona
		return this.getMuestrasEnLaZona().contains(m);
	}
	
	public void registrarMuestra(Muestra m) { // registra la muestra si cumple los requisitos
		if(!estaRegistradaEnLaZonaDeCobertura(m) && perteneceALaZonaDeCobertura(m)) {
			this.getMuestrasEnLaZona().add(m);
			this.notificarEventoAOrganizaciones(new EventoRegistroEnZona(m, this));
			m.suscribirInteresado(this); // agrega a la zona a lista de observers que miran la muestra para una futura verificacion
		}
	}
	
	// deberia haber un eliminar muestra que saque la muestra de la coleccion y tambien desuscriba a la zona de la muestra?
	
	public void registrarMuestrasEnLaZonaDeCobertura(List<Muestra> ms) { // registra las muestras de la coleccion ms que cumplan requisitos
		ms.stream()
		  .forEach(m -> this.registrarMuestra(m));
	}
	
	public void suscribirOrganizacion(Organizacion o) {
		this.getOrganizaciones().add(o);
	}
	
	public void desuscribirOrganizacion(Organizacion o) {
		this.getOrganizaciones().remove(o);
	}
	
	public void notificarEventoAOrganizaciones(Evento e) { // avisa a todos los observers (las orgas)
		List<Organizacion> organizaciones = this.getOrganizaciones();
		organizaciones.stream()
					  .forEach(o -> o.procesarEvento(e)); // las orgas procesan un evento que tiene muestra y zona
	}

	@Override // este es el de interfaz Interesado
	public void recibirNotificacionDeMuestra(Notificador muestra) { // esto solo se ejecuta cuando la muestra que esta mirando la zona avisa que se verifico!
		Evento evento = new EventoVerificacionEnZona(muestra, this);
		this.notificarEventoAOrganizaciones(evento);
		
	}
}