package ar.edu.unq.integrador.organizacion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import ar.edu.unq.integrador.organizacion.*;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.notificador.Evento;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.zonaDeCobertura.copy.ZonaDeCobertura;

class OrganizacionTest {

    @Test
    void testConstructorConPlugins() {
        Ubicacion ubicacion = mock(Ubicacion.class);
        TipoOrganizacion tipo = mock(TipoOrganizacion.class);
        FuncionalidadExterna pluginCarga = mock(FuncionalidadExterna.class);
        FuncionalidadExterna pluginValidacion = mock(FuncionalidadExterna.class);

        Organizacion org = new Organizacion(ubicacion, tipo, 5, pluginCarga, pluginValidacion);

        assertEquals(ubicacion, org.getUbicacion());
        assertEquals(tipo, org.getOrganizacion());
        assertEquals(5, org.getPersonas());
        assertEquals(pluginCarga, org.getPluginCarga());
        assertEquals(pluginValidacion, org.getPluginValidacion());
    }

    @Test
    void testConstructorSinPlugins() {
        Ubicacion ubicacion = mock(Ubicacion.class);
        TipoOrganizacion tipo = mock(TipoOrganizacion.class);

        Organizacion org = new Organizacion(ubicacion, tipo, 10);

        assertEquals(ubicacion, org.getUbicacion());
        assertEquals(tipo, org.getOrganizacion());
        assertEquals(10, org.getPersonas());
        assertNull(org.getPluginCarga());
        assertNull(org.getPluginValidacion());
    }

    @Test
    void testProcesarEvento_noEsVerificacion() {
        Organizacion org = new Organizacion(mock(Ubicacion.class), mock(TipoOrganizacion.class), 3,
            mock(FuncionalidadExterna.class), mock(FuncionalidadExterna.class));

        Evento evento = mock(Evento.class);
        ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
        Muestra muestra = mock(Muestra.class);

        when(evento.esVerificacion()).thenReturn(false);
        when(evento.getZona()).thenReturn(zona);
        when(evento.getMuestra()).thenReturn(muestra);

        // Ejecutar
        org.procesarEvento(evento);

        // Verificar que se llamó a pluginCarga.nuevoEvento
        verify(org.getPluginCarga(), times(1)).nuevoEvento(org, zona, muestra);

        // pluginValidacion no debería ser llamado
        verify(org.getPluginValidacion(), never()).nuevoEvento(any(), any(), any());
    }

    @Test
    void testProcesarEvento_esVerificacion() {
        Organizacion org = new Organizacion(mock(Ubicacion.class), mock(TipoOrganizacion.class), 3,
            mock(FuncionalidadExterna.class), mock(FuncionalidadExterna.class));

        Evento evento = mock(Evento.class);
        ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
        Muestra muestra = mock(Muestra.class);

        when(evento.esVerificacion()).thenReturn(true);
        when(evento.getZona()).thenReturn(zona);
        when(evento.getMuestra()).thenReturn(muestra);

        // Ejecutar
        org.procesarEvento(evento);

        // Verificar que se llamó a pluginValidacion.nuevoEvento
        verify(org.getPluginValidacion(), times(1)).nuevoEvento(org, zona, muestra);

        // pluginCarga no debería ser llamado
        verify(org.getPluginCarga(), never()).nuevoEvento(any(), any(), any());
    }
}

