package ar.edu.unq.integrador.organizacion;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.zonaDeCobertura.ZonaDeCobertura;
import ar.edu.unq.integrador.zonaDeCobertura.INotificador;

public class OrganizacionTest {

	@Test
	public void testSubscribirseAZona() {
		ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
		INotificador notificador = mock(INotificador.class);
		when(zona.getNotificador()).thenReturn(notificador);

		Organizacion org = new Organizacion(mock(Ubicacion.class), TipoOrganizacion.EDUCATIVA, 10,
				mock(FuncionalidadExterna.class));

		org.subscribirseAZona(zona);

		verify(notificador).subscribir(org);
	}

	@Test
	public void testDesubscribirseAZona() {
		ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
		INotificador notificador = mock(INotificador.class);
		when(zona.getNotificador()).thenReturn(notificador);

		Organizacion org = new Organizacion(mock(Ubicacion.class), TipoOrganizacion.EDUCATIVA, 5,
				mock(FuncionalidadExterna.class));

		org.desubscribirseAZona(zona);

		verify(notificador).desuscribir(org);
	}

	@Test
	public void testRecibirNotificacionDeMuestra() {
		FuncionalidadExterna plugin = mock(FuncionalidadExterna.class);
		ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);

		Organizacion org = new Organizacion(mock(Ubicacion.class), TipoOrganizacion.EDUCATIVA, 20, plugin);

		org.recibirNotifacionDeMuestra(zona, muestra);

		verify(plugin).nuevoEvento(org, zona, muestra);
	}

	@Test
	public void testRecibirNotificacionDeMuestraValidada() {
		FuncionalidadExterna plugin = mock(FuncionalidadExterna.class);
		ZonaDeCobertura zona = mock(ZonaDeCobertura.class);
		Muestra muestra = mock(Muestra.class);

		Organizacion org = new Organizacion(mock(Ubicacion.class), TipoOrganizacion.EDUCATIVA, 20, plugin);

		org.recibirNotifacionDeMuestraValidada(zona, muestra);

		verify(plugin).nuevoEvento(org, zona, muestra);
	}
	
    @Test
    public void testSetYGetPlugin() {
        FuncionalidadExterna pluginMock = mock(FuncionalidadExterna.class);
        Organizacion org = new Organizacion(mock(Ubicacion.class), mock(TipoOrganizacion.class), 100, pluginMock);

        assertSame(pluginMock, org.getPlugin());

        // Ahora seteamos otro plugin
        FuncionalidadExterna otroPlugin = mock(FuncionalidadExterna.class);
        org.setPlugin(otroPlugin);

        assertSame(otroPlugin, org.getPlugin());
    }


}
