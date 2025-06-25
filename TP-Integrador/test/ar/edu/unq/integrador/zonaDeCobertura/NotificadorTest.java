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
	
	@Test
	void testMultiplesInteresadosRecibenLaNotificacion() {
	    Notificador notificador = new Notificador();
	    Interesado interesado1 = mock(Interesado.class);
	    Interesado interesado2 = mock(Interesado.class);

	    notificador.subscribir(interesado1);
	    notificador.subscribir(interesado2);

	    ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
	    Muestra muestra = mock(Muestra.class);
	    when(muestra.estaVerificada()).thenReturn(true);

	    notificador.notificar(zona, List.of(muestra));

	    verify(interesado1).recibirNotifacionDeMuestra(zona, muestra);
	    verify(interesado2).recibirNotifacionDeMuestra(zona, muestra);
	    verify(interesado1).recibirNotifacionDeMuestraValidada(zona, muestra);
	    verify(interesado2).recibirNotifacionDeMuestraValidada(zona, muestra);
	}
	
	@Test
	void testDesuscribirUnoDejaAlOtroRecibiendo() {
	    Notificador notificador = new Notificador();
	    Interesado interesado1 = mock(Interesado.class);
	    Interesado interesado2 = mock(Interesado.class);

	    notificador.subscribir(interesado1);
	    notificador.subscribir(interesado2);
	    notificador.desuscribir(interesado1);

	    ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
	    Muestra muestra = mock(Muestra.class);
	    when(muestra.estaVerificada()).thenReturn(true);

	    notificador.notificar(zona, List.of(muestra));

	    verify(interesado1, never()).recibirNotifacionDeMuestra(any(), any());
	    verify(interesado2).recibirNotifacionDeMuestra(zona, muestra);
	}
	
	@Test
	void testNotificarMultiplesMuestras() {
	    Notificador notificador = new Notificador();
	    Interesado interesado = mock(Interesado.class);
	    notificador.subscribir(interesado);

	    ZonaDeCobertura zona = mock(ZonaDeCobertura.class);

	    Muestra muestra1 = mock(Muestra.class);
	    when(muestra1.estaVerificada()).thenReturn(true);

	    Muestra muestra2 = mock(Muestra.class);
	    when(muestra2.estaVerificada()).thenReturn(false);

	    Muestra muestra3 = mock(Muestra.class);
	    when(muestra3.estaVerificada()).thenReturn(true);

	    List<Muestra> muestras = List.of(muestra1, muestra2, muestra3);

	    notificador.notificar(zona, muestras);

	    verify(interesado).recibirNotifacionDeMuestra(zona, muestra1);
	    verify(interesado).recibirNotifacionDeMuestra(zona, muestra2);
	    verify(interesado).recibirNotifacionDeMuestra(zona, muestra3);

	    verify(interesado).recibirNotifacionDeMuestraValidada(zona, muestra1);
	    verify(interesado, never()).recibirNotifacionDeMuestraValidada(zona, muestra2);
	    verify(interesado).recibirNotifacionDeMuestraValidada(zona, muestra3);
	}
}
