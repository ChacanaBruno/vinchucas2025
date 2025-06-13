package ar.edu.unq.integrador.ubicacion;

import java.util.List;
import java.util.stream.Collectors;

public class Ubicacion {
	// Atributos
	private double latitud; // grados en decimales, representa norte-sur
	private double longitud; // grados en decimales, representa este-oeste
	
	// Constructor
	public Ubicacion(double latitud, double longitud) {
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}
	
	// Accessors
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double distanciaALaUbicacion(Ubicacion u) {
//		// esta implementacion no contempla curvatura, la comento para tenerla presente pero no es adecuada
//		double x1 = this.getLongitud();
//		double y1 = this.getLatitud();
//		double x2 = u.getLongitud();
//		double y2 = u.getLatitud();
//		
//		double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
//		double redondeado = Math.round(distancia * 100.0) / 100.0; // si no redondeo el decimal es enorme...
//		
//		return redondeado;
		
		// esta, en cambio, si contempla la curvatura y eso es importante porque se esta trabajando con latitud y longitud
		// OBSERVACION: si se elige redondear, asegurarse de que las coordenadas que se le pasan por parametro sean exactas, sino se aleja demasiado...
		//              aca dejo el redondeo comentado para trabajar con el resultado original
		final int RADIO_TIERRA_KM = 6371;
		
		double latitud1 = this.getLatitud();
		double longitud1 = this.getLongitud();
		double latitud2 = u.getLatitud();
		double longitud2 = u.getLongitud();
		
	    double dLat = Math.toRadians(latitud2 - latitud1);
	    double dLon = Math.toRadians(longitud2 - longitud1);
		
	    double a = Math.pow(Math.sin(dLat / 2), 2)
	             + Math.cos(Math.toRadians(latitud1)) * Math.cos(Math.toRadians(latitud2))
	             * Math.pow(Math.sin(dLon / 2), 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return RADIO_TIERRA_KM * c;
//	    double distancia = RADIO_TIERRA_KM * c;
//
//	    return Math.round(distancia * 100.0) / 100.0; // redondeo a 2 decimales para que el numero no sea enorme
	}

	public List<Ubicacion> ubicacionesAMenosDeXKilometros(double d, List<Ubicacion> us) {
		return us.stream()
				 .filter(u -> (this.distanciaALaUbicacion(u)) <= d)
				 .collect(Collectors.toList()); 
	}
	
	
}
