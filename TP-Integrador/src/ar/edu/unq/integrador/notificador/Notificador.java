package ar.edu.unq.integrador.notificador;

public interface Notificador {
	void notificarVerificacion(Notificador notificador);
	void suscribirInteresado(Interesado interesado);
	void desuscribirInteresado(Interesado interesado);
}
