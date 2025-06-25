package ar.edu.unq.integrador.zonaDeCobertura.copy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.notificador.Evento;
import ar.edu.unq.integrador.notificador.EventoVerificacionEnZona;
import ar.edu.unq.integrador.notificador.Notificador;
import ar.edu.unq.integrador.organizacion.Organizacion;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ZonaDeCoberturaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSeSolapaCon() {
	    Ubicacion epicentro1 = mock(Ubicacion.class);
	    Ubicacion epicentro2 = mock(Ubicacion.class);
	    
	    //distancia entre epicentros
	    when(epicentro1.distanciaALaUbicacion(epicentro2)).thenReturn(5.0);
	    
	    ZonaDeCobertura zona1 = new ZonaDeCobertura("Zona1", epicentro1, 3.0);
	    ZonaDeCobertura zona2 = new ZonaDeCobertura("Zona2", epicentro2, 3.0);
	    
	    assertTrue(zona1.seSolapaCon(zona2)); // 5.0 <= 3.0 + 3.0 => true
	}
	
	@Test
	void testPerteneceALaZonaDeCobertura() {
	    Ubicacion epicentro = mock(Ubicacion.class);
	    Ubicacion ubicacionMuestra = mock(Ubicacion.class);
	    
	    Muestra muestra = mock(Muestra.class);
	    when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);
	    
	    when(epicentro.distanciaALaUbicacion(ubicacionMuestra)).thenReturn(2.0);
	    
	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", epicentro, 3.0);
	    
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra)); // 2.0 <= 3.0
	}
	
	@Test
	void testEstaRegistradaEnLaZonaDeCobertura() {
	    Muestra muestra = mock(Muestra.class);
	    List<Muestra> muestras = new ArrayList<>();
	    muestras.add(muestra);
	    
	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", mock(Ubicacion.class), 3.0, muestras, new ArrayList<>());
	    
	    assertTrue(zona.estaRegistradaEnLaZonaDeCobertura(muestra));
	}
	
	@Test
	void testRegistrarMuestra() {
	    Ubicacion epicentro = mock(Ubicacion.class);
	    Muestra muestra = mock(Muestra.class);
	    Organizacion org = mock(Organizacion.class);
	    
	    when(muestra.getUbicacion()).thenReturn(mock(Ubicacion.class));
	    when(epicentro.distanciaALaUbicacion(any())).thenReturn(1.0);
	    
	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", epicentro, 3.0);
	    zona.suscribirOrganizacion(org);
	    
	    // No está registrada ni la muestra ni la organización aún
	    
	    zona.registrarMuestra(muestra);
	    
	    // Verificamos que la muestra se agregó
	    assertTrue(zona.getMuestrasEnLaZona().contains(muestra));
	    
	    // Verificamos que se notificó a la organización (se invoca procesarEvento)
	    verify(org, times(1)).procesarEvento(any());
	    
	    // Verificamos que la muestra suscribió a la zona (suscribirInteresado)
	    verify(muestra, times(1)).suscribirInteresado(zona);
	}

	@Test
	void testRegistrarMuestrasEnLaZonaDeCobertura() {
	    Ubicacion epicentro = mock(Ubicacion.class);
	    Muestra muestra1 = mock(Muestra.class);
	    Muestra muestra2 = mock(Muestra.class);
	    
	    when(muestra1.getUbicacion()).thenReturn(mock(Ubicacion.class));
	    when(muestra2.getUbicacion()).thenReturn(mock(Ubicacion.class));
	    when(epicentro.distanciaALaUbicacion(any())).thenReturn(1.0);
	    
	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", epicentro, 3.0);
	    
	    List<Muestra> muestras = Arrays.asList(muestra1, muestra2);
	    
	    zona.registrarMuestrasEnLaZonaDeCobertura(muestras);
	    
	    assertTrue(zona.getMuestrasEnLaZona().containsAll(muestras));
	}
	
	@Test
	void testSuscribirYDesuscribirOrganizacion() {
	    Organizacion org = mock(Organizacion.class);
	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", mock(Ubicacion.class), 3.0);
	    
	    zona.suscribirOrganizacion(org);
	    assertTrue(zona.getOrganizaciones().contains(org));
	    
	    zona.desuscribirOrganizacion(org);
	    assertFalse(zona.getOrganizaciones().contains(org));
	}
	
	@Test
	void testNotificarEventoAOrganizaciones() {
	    Organizacion org1 = mock(Organizacion.class);
	    Organizacion org2 = mock(Organizacion.class);
	    
	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", mock(Ubicacion.class), 3.0);
	    zona.suscribirOrganizacion(org1);
	    zona.suscribirOrganizacion(org2);
	    
	    Evento evento = mock(Evento.class);
	    
	    zona.notificarEventoAOrganizaciones(evento);
	    
	    verify(org1, times(1)).procesarEvento(evento);
	    verify(org2, times(1)).procesarEvento(evento);
	}

	@Test
	void testRecibirNotificacionDeMuestra() {
	    Organizacion org = mock(Organizacion.class);
	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", mock(Ubicacion.class), 3.0);
	    zona.suscribirOrganizacion(org);
	    
	    Notificador muestra = mock(Notificador.class);
	    
	    zona.recibirNotificacionDeMuestra(muestra);
	    
	    // Verificamos que se notificó a la organización con un evento
	    verify(org, times(1)).procesarEvento(any(EventoVerificacionEnZona.class));
	}

}
