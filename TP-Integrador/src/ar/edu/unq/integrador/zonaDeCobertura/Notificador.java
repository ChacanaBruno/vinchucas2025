package ar.edu.unq.integrador.zonaDeCobertura;

import java.util.List;
import java.util.Set;

import ar.edu.unq.integrador.muestra.Muestra;

public class Notificador implements INotificador {

	private Set<Interesado> interesados;

	public void subscribir(Interesado interesado) {
		interesados.add(interesado);

	}

	public void desuscribir(Interesado interesado) {
		interesados.remove(interesado);

	}

	public void notificar(ZonaDeCobertura zona, List<Muestra> muestrasDentroDelRadio) {
		for (Muestra m : muestrasDentroDelRadio) {

			interesados.stream().forEach(i -> i.recibirNotifacionDeMuestra(zona, m));

			if (m.estaVerificada()) {
				interesados.stream().forEach(i -> i.recibirNotifacionDeMuestraValidada(zona, m));
			}
		}

	}

}
