package ar.edu.unq.integrador.ubicacion;

import java.util.List;
import java.util.stream.Collectors;

public class Ubicacion {
	// Atributos
	private double longitud; // eje x
	private double latitud; // eje y
	
	// Constructor
	public Ubicacion(double longitud, double latitud) {
		this.setLongitud(longitud);
		this.setLatitud(latitud);
	}
	
	// Accessors
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double distanciaALaUbicacion(Ubicacion u) {
		double x1 = this.getLongitud();
		double y1 = this.getLatitud();
		double x2 = u.getLongitud();
		double y2 = u.getLatitud();
		
		double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		double redondeado = Math.round(distancia * 100.0) / 100.0; // si no redondeo el decimal es enorme...
		
		return redondeado;
	}

	public List<Ubicacion> ubicacionesAMenosDeXKilometros(double d, List<Ubicacion> us) {
		return us.stream()
				 .filter(u -> (this.distanciaALaUbicacion(u)) <= d)
				 .collect(Collectors.toList()); 
	}
	
	
}
