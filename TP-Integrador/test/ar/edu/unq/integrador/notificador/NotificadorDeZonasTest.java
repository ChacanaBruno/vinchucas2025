package ar.edu.unq.integrador.notificador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class NotificadorDeZonasTest {

    @Test
    void testConstructorConLista() {
        List<Interesado> listaMock = new ArrayList<>();
        Interesado interesadoMock = mock(Interesado.class);
        listaMock.add(interesadoMock);

        NotificadorDeZonas notificador = new NotificadorDeZonas(listaMock);

        assertEquals(1, notificador.getZonas().size());
        assertTrue(notificador.getZonas().contains(interesadoMock));
    }

    @Test
    void testConstructorPorDefecto() {
        NotificadorDeZonas notificador = new NotificadorDeZonas();

        assertNotNull(notificador.getZonas());
        assertTrue(notificador.getZonas().isEmpty());
    }

    @Test
    void testSetYGetZonas() {
        NotificadorDeZonas notificador = new NotificadorDeZonas();

        List<Interesado> nuevaLista = new ArrayList<>();
        Interesado interesadoMock = mock(Interesado.class);
        nuevaLista.add(interesadoMock);

        notificador.setZonas(nuevaLista);

        assertEquals(nuevaLista, notificador.getZonas());
        assertEquals(1, notificador.getZonas().size());
    }

    @Test
    void testNotificarVerificacion() {
        NotificadorDeZonas notificador = new NotificadorDeZonas();

        Interesado interesado1 = mock(Interesado.class);
        Interesado interesado2 = mock(Interesado.class);

        notificador.suscribirInteresado(interesado1);
        notificador.suscribirInteresado(interesado2);

        Notificador notificadorMuestra = mock(Notificador.class);

        notificador.notificarVerificacion(notificadorMuestra);

        verify(interesado1, times(1)).recibirNotificacionDeMuestra(notificadorMuestra);
        verify(interesado2, times(1)).recibirNotificacionDeMuestra(notificadorMuestra);
    }

    @Test
    void testSuscribirInteresado() {
        NotificadorDeZonas notificador = new NotificadorDeZonas();

        Interesado interesadoMock = mock(Interesado.class);

        notificador.suscribirInteresado(interesadoMock);

        assertTrue(notificador.getZonas().contains(interesadoMock));
    }

    @Test
    void testDesuscribirInteresado() {
        NotificadorDeZonas notificador = new NotificadorDeZonas();

        Interesado interesadoMock = mock(Interesado.class);

        notificador.suscribirInteresado(interesadoMock);
        assertTrue(notificador.getZonas().contains(interesadoMock));

        notificador.desuscribirInteresado(interesadoMock);
        assertFalse(notificador.getZonas().contains(interesadoMock));
    }
}
