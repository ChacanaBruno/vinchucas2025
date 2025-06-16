package ar.edu.unq.integrador.zonaDeCobertura;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.ubicacion.Ubicacion;

public class ZonaDeCobertura {

	private Ubicacion epicentro;
	private double radioKm;
	private String nombreIdentificador;
	private INotificador notificador;

	public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radioKm, INotificador notificador) {
		this.nombreIdentificador = nombre;
		this.epicentro = epicentro;
		this.radioKm = radioKm;
		this.notificador = notificador;
	}

	public String getNombre() {
		return nombreIdentificador;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public double getRadioKm() {
		return radioKm;
	}

	public INotificador getNotificador() {
		return notificador;
	}

	public void setNotificador(Notificador notificador) {
		this.notificador = notificador;
	}

	/**
	 * Indica si esta zona se solapa con otra zona, es decir, si la distancia entre
	 * epicentros es menor que la suma de los radios.
	 */
	public boolean seSolapaCon(ZonaDeCobertura otraZona) {
		double distanciaEpicentros = epicentro.distanciaALaUbicacion(otraZona.getEpicentro());
		return distanciaEpicentros <= (this.radioKm + otraZona.getRadioKm());
	}

	public List<Muestra> muestrasDentroDelRadio(List<Muestra> muestras) {
		return muestras.stream().filter(muestra -> {
			Ubicacion ubicacionMuestra = muestra.getUbicacion();
			double distancia = epicentro.distanciaALaUbicacion(ubicacionMuestra);
			return distancia <= radioKm;
		}).collect(Collectors.toList());
	}

	public void recibirMuestras(List<Muestra> muestras) {

		if (!this.muestrasDentroDelRadio(muestras).isEmpty()) {

			notificador.notificar(this, muestrasDentroDelRadio(muestras));

		}
	}
}