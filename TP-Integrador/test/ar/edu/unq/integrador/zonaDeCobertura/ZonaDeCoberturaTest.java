package ar.edu.unq.integrador.zonaDeCobertura;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.ubicacion.Ubicacion;

public class ZonaDeCoberturaTest {
	
	@Test
	void test_ZonaDeCoberturaConstructor() {
		// Setup
		Ubicacion epicentro = mock(Ubicacion.class);
		double radioKm = 10;
		String nombreIdentificador = "Pepe";
		INotificador notificador = mock(INotificador.class);
		
		// Exercise
		ZonaDeCobertura zona = new ZonaDeCobertura(nombreIdentificador, epicentro, radioKm, notificador);
		
		// Verify
		assertEquals(nombreIdentificador, zona.getNombre());
		assertEquals(epicentro, zona.getEpicentro());
		assertEquals(radioKm, zona.getRadioKm());
		assertEquals(notificador, zona.getNotificador());
	}
	
	@Test
	public void testSeSolapaConOtraZona() {
		Ubicacion ubicacion1 = mock(Ubicacion.class);
		Ubicacion ubicacion2 = mock(Ubicacion.class);

		when(ubicacion1.distanciaALaUbicacion(ubicacion2)).thenReturn(5.0);

		ZonaDeCobertura zona1 = new ZonaDeCobertura("Zona 1", ubicacion1, 3.0, mock(INotificador.class));
		ZonaDeCobertura zona2 = new ZonaDeCobertura("Zona 2", ubicacion2, 3.0, mock(INotificador.class));

		assertTrue(zona1.seSolapaCon(zona2));
	}

	@Test
	public void testMuestrasDentroDelRadio() {
		Ubicacion epicentro = mock(Ubicacion.class);
		Ubicacion ubicacionMuestra = mock(Ubicacion.class);

		when(epicentro.distanciaALaUbicacion(ubicacionMuestra)).thenReturn(2.0);

		Muestra muestra = mock(Muestra.class);
		when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);

		ZonaDeCobertura zona = new ZonaDeCobertura("Zona", epicentro, 3.0, mock(INotificador.class));
		List<Muestra> resultado = zona.muestrasDentroDelRadio(List.of(muestra));

		assertEquals(1, resultado.size());
		assertTrue(resultado.contains(muestra));
	}

	@Test
	public void testRecibirMuestrasConMuestrasDentroDelRadio() {
		Ubicacion epicentro = mock(Ubicacion.class);
		Ubicacion ubicacionMuestra = mock(Ubicacion.class);

		Muestra muestra = mock(Muestra.class);
		when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);
		when(epicentro.distanciaALaUbicacion(ubicacionMuestra)).thenReturn(1.0);

		INotificador notificador = mock(INotificador.class);

		ZonaDeCobertura zona = new ZonaDeCobertura("Z1", epicentro, 5.0, notificador);

		zona.recibirMuestras(List.of(muestra));

		verify(notificador).notificar(eq(zona), eq(List.of(muestra)));
	}

	@Test
	public void testRecibirMuestrasSinMuestrasDentroDelRadio() {
		Ubicacion epicentro = mock(Ubicacion.class);
		Ubicacion ubicacionMuestra = mock(Ubicacion.class);

		Muestra muestra = mock(Muestra.class);
		when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);
		when(epicentro.distanciaALaUbicacion(ubicacionMuestra)).thenReturn(10.0);

		INotificador notificador = mock(INotificador.class);

		ZonaDeCobertura zona = new ZonaDeCobertura("Z1", epicentro, 5.0, notificador);

		zona.recibirMuestras(List.of(muestra));

		verify(notificador, never()).notificar(any(), any());
	}
}
