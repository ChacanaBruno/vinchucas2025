package ar.edu.unq.integrador.notificador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;


import ar.edu.unq.integrador.zonaDeCobertura.copy.ZonaDeCobertura;

public class EventoTest {

    @Test
    void testConstructorYGettersEventoRegistroEnZona() {
        Notificador muestraMock = mock(Notificador.class);
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);

        EventoRegistroEnZona evento = new EventoRegistroEnZona(muestraMock, zonaMock);

        assertEquals(muestraMock, evento.getMuestra());
        assertEquals(zonaMock, evento.getZona());
        assertFalse(evento.esVerificacion());
    }

    @Test
    void testConstructorYGettersEventoVerificacionEnZona() {
        Notificador muestraMock = mock(Notificador.class);
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);

        EventoVerificacionEnZona evento = new EventoVerificacionEnZona(muestraMock, zonaMock);

        assertEquals(muestraMock, evento.getMuestra());
        assertEquals(zonaMock, evento.getZona());
        assertTrue(evento.esVerificacion());
    }
}
