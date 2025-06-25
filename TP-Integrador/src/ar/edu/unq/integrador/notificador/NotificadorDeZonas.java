package ar.edu.unq.integrador.notificador;

import java.util.ArrayList;
import java.util.List;


public class NotificadorDeZonas implements Notificador {
	// Atributos
	private List<Interesado> zonas;
	
	// Constructores
	public NotificadorDeZonas(List<Interesado> lz) {
		this.setZonas(lz);
	}
	
	public NotificadorDeZonas() {
		this.setZonas(new ArrayList<>());
	}
	
	// Accessing
	public List<Interesado> getZonas(){
		return this.zonas;
	}
	
	public void setZonas(List<Interesado> lz) {
		this.zonas = lz;
	}
	
	// Metodos
	@Override
	public void notificarVerificacion(Notificador notificador) {
		List<Interesado> zonasANotificar = this.getZonas();
		zonasANotificar.stream()
					   .forEach(z -> z.recibirNotificacionDeMuestra(notificador));
	}

	@Override
	public void suscribirInteresado(Interesado interesado) {
		this.getZonas().add(interesado);
	}

	@Override
	public void desuscribirInteresado(Interesado interesado) {
		this.getZonas().remove(interesado);

	}

}
