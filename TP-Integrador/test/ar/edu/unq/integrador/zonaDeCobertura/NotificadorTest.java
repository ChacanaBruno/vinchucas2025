package ar.edu.unq.integrador.zonaDeCobertura;

import ar.edu.unq.integrador.muestra.Muestra;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class NotificadorTest {

	@Test
	void testSuscribirAgregaInteresado() {
		Notificador notificador = new Notificador();
		Interesado interesado = mock(Interesado.class);

		notificador.subscribir(interesado);

		ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);
		when(muestra.estaVerificada()).thenReturn(false);

		notificador.notificar(zona, List.of(muestra));

		verify(interesado).recibirNotifacionDeMuestra(zona, muestra);
		verify(interesado, never()).recibirNotifacionDeMuestraValidada(any(), any());
	}

	@Test
	void testDesuscribirEliminaInteresado() {
		Notificador notificador = new Notificador();
		Interesado interesado = mock(Interesado.class);

		notificador.subscribir(interesado);
		notificador.desuscribir(interesado);

		ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);
		when(muestra.estaVerificada()).thenReturn(true);

		notificador.notificar(zona, List.of(muestra));

		verify(interesado, never()).recibirNotifacionDeMuestra(any(), any());
		verify(interesado, never()).recibirNotifacionDeMuestraValidada(any(), any());
	}

	@Test
	void testNotificarMuestraVerificada() {
		Notificador notificador = new Notificador();
		Interesado interesado = mock(Interesado.class);

		notificador.subscribir(interesado);

		ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);
		when(muestra.estaVerificada()).thenReturn(true);

		notificador.notificar(zona, List.of(muestra));

		verify(interesado).recibirNotifacionDeMuestra(zona, muestra);
		verify(interesado).recibirNotifacionDeMuestraValidada(zona, muestra);
	}
}
