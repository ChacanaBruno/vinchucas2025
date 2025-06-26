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

	@Test
	void testNoSeSolapaCon() {
	    Ubicacion epicentro1 = mock(Ubicacion.class);
	    Ubicacion epicentro2 = mock(Ubicacion.class);

	    when(epicentro1.distanciaALaUbicacion(epicentro2)).thenReturn(10.0);

	    ZonaDeCobertura zona1 = new ZonaDeCobertura("Zona1", epicentro1, 3.0);
	    ZonaDeCobertura zona2 = new ZonaDeCobertura("Zona2", epicentro2, 3.0);

	    assertFalse(zona1.seSolapaCon(zona2));
	}
	
	@Test
	void testNoPerteneceALaZonaDeCobertura() {
	    Ubicacion epicentro = mock(Ubicacion.class);
	    Ubicacion ubicacion = mock(Ubicacion.class);

	    Muestra muestra = mock(Muestra.class);
	    when(muestra.getUbicacion()).thenReturn(ubicacion);
	    when(epicentro.distanciaALaUbicacion(ubicacion)).thenReturn(5.0);

	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", epicentro, 3.0);

	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra));
	}
	@Test
	void testUbicacionesNoPertenecenALaZonaDeCobertura() {
	    Ubicacion epicentro = mock(Ubicacion.class);
	    ZonaDeCobertura zona = new ZonaDeCobertura("Oeste", epicentro, 3.0);

	    Ubicacion ubicacion1 = mock(Ubicacion.class);
	    Muestra muestra1 = mock(Muestra.class);
	    when(muestra1.getUbicacion()).thenReturn(ubicacion1);
	    when(epicentro.distanciaALaUbicacion(ubicacion1)).thenReturn(5.0);

	    Ubicacion ubicacion2 = mock(Ubicacion.class);
	    Muestra muestra2 = mock(Muestra.class);
	    when(muestra2.getUbicacion()).thenReturn(ubicacion2);
	    when(epicentro.distanciaALaUbicacion(ubicacion2)).thenReturn(6.0);

	    Ubicacion ubicacion3 = mock(Ubicacion.class);
	    Muestra muestra3 = mock(Muestra.class);
	    when(muestra3.getUbicacion()).thenReturn(ubicacion3);
	    when(epicentro.distanciaALaUbicacion(ubicacion3)).thenReturn(7.0);

	    Ubicacion ubicacion4 = mock(Ubicacion.class);
	    Muestra muestra4 = mock(Muestra.class);
	    when(muestra4.getUbicacion()).thenReturn(ubicacion4);
	    when(epicentro.distanciaALaUbicacion(ubicacion4)).thenReturn(8.0);

	    Ubicacion ubicacion5 = mock(Ubicacion.class);
	    Muestra muestra5 = mock(Muestra.class);
	    when(muestra5.getUbicacion()).thenReturn(ubicacion5);
	    when(epicentro.distanciaALaUbicacion(ubicacion5)).thenReturn(9.0);

	    Ubicacion ubicacion6 = mock(Ubicacion.class);
	    Muestra muestra6 = mock(Muestra.class);
	    when(muestra6.getUbicacion()).thenReturn(ubicacion6);
	    when(epicentro.distanciaALaUbicacion(ubicacion6)).thenReturn(10.0);

	    Ubicacion ubicacion7 = mock(Ubicacion.class);
	    Muestra muestra7 = mock(Muestra.class);
	    when(muestra7.getUbicacion()).thenReturn(ubicacion7);
	    when(epicentro.distanciaALaUbicacion(ubicacion7)).thenReturn(11.0);

	    Ubicacion ubicacion8 = mock(Ubicacion.class);
	    Muestra muestra8 = mock(Muestra.class);
	    when(muestra8.getUbicacion()).thenReturn(ubicacion8);
	    when(epicentro.distanciaALaUbicacion(ubicacion8)).thenReturn(12.0);

	    Ubicacion ubicacion9 = mock(Ubicacion.class);
	    Muestra muestra9 = mock(Muestra.class);
	    when(muestra9.getUbicacion()).thenReturn(ubicacion9);
	    when(epicentro.distanciaALaUbicacion(ubicacion9)).thenReturn(13.0);

	    Ubicacion ubicacion10 = mock(Ubicacion.class);
	    Muestra muestra10 = mock(Muestra.class);
	    when(muestra10.getUbicacion()).thenReturn(ubicacion10);
	    when(epicentro.distanciaALaUbicacion(ubicacion10)).thenReturn(14.0);

	    Ubicacion ubicacion11 = mock(Ubicacion.class);
	    Muestra muestra11 = mock(Muestra.class);
	    when(muestra11.getUbicacion()).thenReturn(ubicacion11);
	    when(epicentro.distanciaALaUbicacion(ubicacion11)).thenReturn(15.0);

	    Ubicacion ubicacion12 = mock(Ubicacion.class);
	    Muestra muestra12 = mock(Muestra.class);
	    when(muestra12.getUbicacion()).thenReturn(ubicacion12);
	    when(epicentro.distanciaALaUbicacion(ubicacion12)).thenReturn(16.0);

	    Ubicacion ubicacion13 = mock(Ubicacion.class);
	    Muestra muestra13 = mock(Muestra.class);
	    when(muestra13.getUbicacion()).thenReturn(ubicacion13);
	    when(epicentro.distanciaALaUbicacion(ubicacion13)).thenReturn(17.0);

	    Ubicacion ubicacion14 = mock(Ubicacion.class);
	    Muestra muestra14 = mock(Muestra.class);
	    when(muestra14.getUbicacion()).thenReturn(ubicacion14);
	    when(epicentro.distanciaALaUbicacion(ubicacion14)).thenReturn(18.0);

	    Ubicacion ubicacion15 = mock(Ubicacion.class);
	    Muestra muestra15 = mock(Muestra.class);
	    when(muestra15.getUbicacion()).thenReturn(ubicacion15);
	    when(epicentro.distanciaALaUbicacion(ubicacion15)).thenReturn(19.0);

	    Ubicacion ubicacion16 = mock(Ubicacion.class);
	    Muestra muestra16 = mock(Muestra.class);
	    when(muestra16.getUbicacion()).thenReturn(ubicacion16);
	    when(epicentro.distanciaALaUbicacion(ubicacion16)).thenReturn(20.0);

	    Ubicacion ubicacion17 = mock(Ubicacion.class);
	    Muestra muestra17 = mock(Muestra.class);
	    when(muestra17.getUbicacion()).thenReturn(ubicacion17);
	    when(epicentro.distanciaALaUbicacion(ubicacion17)).thenReturn(21.0);

	    Ubicacion ubicacion18 = mock(Ubicacion.class);
	    Muestra muestra18 = mock(Muestra.class);
	    when(muestra18.getUbicacion()).thenReturn(ubicacion18);
	    when(epicentro.distanciaALaUbicacion(ubicacion18)).thenReturn(22.0);

	    Ubicacion ubicacion19 = mock(Ubicacion.class);
	    Muestra muestra19 = mock(Muestra.class);
	    when(muestra19.getUbicacion()).thenReturn(ubicacion19);
	    when(epicentro.distanciaALaUbicacion(ubicacion19)).thenReturn(23.0);

	    Ubicacion ubicacion20 = mock(Ubicacion.class);
	    Muestra muestra20 = mock(Muestra.class);
	    when(muestra20.getUbicacion()).thenReturn(ubicacion20);
	    when(epicentro.distanciaALaUbicacion(ubicacion20)).thenReturn(24.0);

	    Ubicacion ubicacion21 = mock(Ubicacion.class);
	    Muestra muestra21 = mock(Muestra.class);
	    when(muestra21.getUbicacion()).thenReturn(ubicacion21);
	    when(epicentro.distanciaALaUbicacion(ubicacion21)).thenReturn(25.0);

	    Ubicacion ubicacion22 = mock(Ubicacion.class);
	    Muestra muestra22 = mock(Muestra.class);
	    when(muestra22.getUbicacion()).thenReturn(ubicacion22);
	    when(epicentro.distanciaALaUbicacion(ubicacion22)).thenReturn(26.0);

	    Ubicacion ubicacion23 = mock(Ubicacion.class);
	    Muestra muestra23 = mock(Muestra.class);
	    when(muestra23.getUbicacion()).thenReturn(ubicacion23);
	    when(epicentro.distanciaALaUbicacion(ubicacion23)).thenReturn(27.0);

	    Ubicacion ubicacion24 = mock(Ubicacion.class);
	    Muestra muestra24 = mock(Muestra.class);
	    when(muestra24.getUbicacion()).thenReturn(ubicacion24);
	    when(epicentro.distanciaALaUbicacion(ubicacion24)).thenReturn(28.0);

	    Ubicacion ubicacion25 = mock(Ubicacion.class);
	    Muestra muestra25 = mock(Muestra.class);
	    when(muestra25.getUbicacion()).thenReturn(ubicacion25);
	    when(epicentro.distanciaALaUbicacion(ubicacion25)).thenReturn(29.0);

	    Ubicacion ubicacion26 = mock(Ubicacion.class);
	    Muestra muestra26 = mock(Muestra.class);
	    when(muestra26.getUbicacion()).thenReturn(ubicacion26);
	    when(epicentro.distanciaALaUbicacion(ubicacion26)).thenReturn(30.0);

	    Ubicacion ubicacion27 = mock(Ubicacion.class);
	    Muestra muestra27 = mock(Muestra.class);
	    when(muestra27.getUbicacion()).thenReturn(ubicacion27);
	    when(epicentro.distanciaALaUbicacion(ubicacion27)).thenReturn(31.0);

	    Ubicacion ubicacion28 = mock(Ubicacion.class);
	    Muestra muestra28 = mock(Muestra.class);
	    when(muestra28.getUbicacion()).thenReturn(ubicacion28);
	    when(epicentro.distanciaALaUbicacion(ubicacion28)).thenReturn(32.0);

	    Ubicacion ubicacion29 = mock(Ubicacion.class);
	    Muestra muestra29 = mock(Muestra.class);
	    when(muestra29.getUbicacion()).thenReturn(ubicacion29);
	    when(epicentro.distanciaALaUbicacion(ubicacion29)).thenReturn(33.0);

	    Ubicacion ubicacion30 = mock(Ubicacion.class);
	    Muestra muestra30 = mock(Muestra.class);
	    when(muestra30.getUbicacion()).thenReturn(ubicacion30);
	    when(epicentro.distanciaALaUbicacion(ubicacion30)).thenReturn(34.0);

	    Ubicacion ubicacion31 = mock(Ubicacion.class);
	    Muestra muestra31 = mock(Muestra.class);
	    when(muestra31.getUbicacion()).thenReturn(ubicacion31);
	    when(epicentro.distanciaALaUbicacion(ubicacion31)).thenReturn(35.0);

	    Ubicacion ubicacion32 = mock(Ubicacion.class);
	    Muestra muestra32 = mock(Muestra.class);
	    when(muestra32.getUbicacion()).thenReturn(ubicacion32);
	    when(epicentro.distanciaALaUbicacion(ubicacion32)).thenReturn(36.0);

	    Ubicacion ubicacion33 = mock(Ubicacion.class);
	    Muestra muestra33 = mock(Muestra.class);
	    when(muestra33.getUbicacion()).thenReturn(ubicacion33);
	    when(epicentro.distanciaALaUbicacion(ubicacion33)).thenReturn(37.0);

	    Ubicacion ubicacion34 = mock(Ubicacion.class);
	    Muestra muestra34 = mock(Muestra.class);
	    when(muestra34.getUbicacion()).thenReturn(ubicacion34);
	    when(epicentro.distanciaALaUbicacion(ubicacion34)).thenReturn(38.0);

	    Ubicacion ubicacion35 = mock(Ubicacion.class);
	    Muestra muestra35 = mock(Muestra.class);
	    when(muestra35.getUbicacion()).thenReturn(ubicacion35);
	    when(epicentro.distanciaALaUbicacion(ubicacion35)).thenReturn(39.0);

	    Ubicacion ubicacion36 = mock(Ubicacion.class);
	    Muestra muestra36 = mock(Muestra.class);
	    when(muestra36.getUbicacion()).thenReturn(ubicacion36);
	    when(epicentro.distanciaALaUbicacion(ubicacion36)).thenReturn(40.0);

	    Ubicacion ubicacion37 = mock(Ubicacion.class);
	    Muestra muestra37 = mock(Muestra.class);
	    when(muestra37.getUbicacion()).thenReturn(ubicacion37);
	    when(epicentro.distanciaALaUbicacion(ubicacion37)).thenReturn(41.0);

	    Ubicacion ubicacion38 = mock(Ubicacion.class);
	    Muestra muestra38 = mock(Muestra.class);
	    when(muestra38.getUbicacion()).thenReturn(ubicacion38);
	    when(epicentro.distanciaALaUbicacion(ubicacion38)).thenReturn(42.0);

	    Ubicacion ubicacion39 = mock(Ubicacion.class);
	    Muestra muestra39 = mock(Muestra.class);
	    when(muestra39.getUbicacion()).thenReturn(ubicacion39);
	    when(epicentro.distanciaALaUbicacion(ubicacion39)).thenReturn(43.0);

	    Ubicacion ubicacion40 = mock(Ubicacion.class);
	    Muestra muestra40 = mock(Muestra.class);
	    when(muestra40.getUbicacion()).thenReturn(ubicacion40);
	    when(epicentro.distanciaALaUbicacion(ubicacion40)).thenReturn(44.0);

	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra1));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra2));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra3));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra4));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra5));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra6));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra7));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra8));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra9));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra10));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra11));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra12));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra13));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra14));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra15));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra16));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra17));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra18));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra19));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra20));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra21));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra22));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra23));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra24));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra25));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra26));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra27));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra28));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra29));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra30));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra31));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra32));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra33));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra34));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra35));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra36));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra37));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra38));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra39));
	    assertFalse(zona.perteneceALaZonaDeCobertura(muestra40));
	}
	
	@Test
	void testUbicacionesPertenecenALaZonaDeCobertura() {
	    Ubicacion epicentro = mock(Ubicacion.class);
	    ZonaDeCobertura zona = new ZonaDeCobertura("Zona", epicentro, 5.0);

	    Ubicacion ubicacion1 = mock(Ubicacion.class);
	    Muestra muestra1 = mock(Muestra.class);
	    when(muestra1.getUbicacion()).thenReturn(ubicacion1);
	    when(epicentro.distanciaALaUbicacion(ubicacion1)).thenReturn(1.0);

	    Ubicacion ubicacion2 = mock(Ubicacion.class);
	    Muestra muestra2 = mock(Muestra.class);
	    when(muestra2.getUbicacion()).thenReturn(ubicacion2);
	    when(epicentro.distanciaALaUbicacion(ubicacion2)).thenReturn(2.0);

	    Ubicacion ubicacion3 = mock(Ubicacion.class);
	    Muestra muestra3 = mock(Muestra.class);
	    when(muestra3.getUbicacion()).thenReturn(ubicacion3);
	    when(epicentro.distanciaALaUbicacion(ubicacion3)).thenReturn(3.0);

	    Ubicacion ubicacion4 = mock(Ubicacion.class);
	    Muestra muestra4 = mock(Muestra.class);
	    when(muestra4.getUbicacion()).thenReturn(ubicacion4);
	    when(epicentro.distanciaALaUbicacion(ubicacion4)).thenReturn(4.0);

	    Ubicacion ubicacion5 = mock(Ubicacion.class);
	    Muestra muestra5 = mock(Muestra.class);
	    when(muestra5.getUbicacion()).thenReturn(ubicacion5);
	    when(epicentro.distanciaALaUbicacion(ubicacion5)).thenReturn(5.0);

	    Ubicacion ubicacion6 = mock(Ubicacion.class);
	    Muestra muestra6 = mock(Muestra.class);
	    when(muestra6.getUbicacion()).thenReturn(ubicacion6);
	    when(epicentro.distanciaALaUbicacion(ubicacion6)).thenReturn(2.5);

	    Ubicacion ubicacion7 = mock(Ubicacion.class);
	    Muestra muestra7 = mock(Muestra.class);
	    when(muestra7.getUbicacion()).thenReturn(ubicacion7);
	    when(epicentro.distanciaALaUbicacion(ubicacion7)).thenReturn(4.9);

	    Ubicacion ubicacion8 = mock(Ubicacion.class);
	    Muestra muestra8 = mock(Muestra.class);
	    when(muestra8.getUbicacion()).thenReturn(ubicacion8);
	    when(epicentro.distanciaALaUbicacion(ubicacion8)).thenReturn(0.1);

	    Ubicacion ubicacion9 = mock(Ubicacion.class);
	    Muestra muestra9 = mock(Muestra.class);
	    when(muestra9.getUbicacion()).thenReturn(ubicacion9);
	    when(epicentro.distanciaALaUbicacion(ubicacion9)).thenReturn(3.3);

	    Ubicacion ubicacion10 = mock(Ubicacion.class);
	    Muestra muestra10 = mock(Muestra.class);
	    when(muestra10.getUbicacion()).thenReturn(ubicacion10);
	    when(epicentro.distanciaALaUbicacion(ubicacion10)).thenReturn(1.7);

	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra1));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra2));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra3));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra4));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra5));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra6));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra7));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra8));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra9));
	    assertTrue(zona.perteneceALaZonaDeCobertura(muestra10));
	}
}
