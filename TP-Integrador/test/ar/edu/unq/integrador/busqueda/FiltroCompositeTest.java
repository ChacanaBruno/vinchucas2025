package ar.edu.unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroCompositeTest {

	@Test
	public void testFiltroAndConDosFiltrosCumpleAmbos() {
		Muestra muestra = mock(Muestra.class);

		FiltroDeBusqueda filtro1 = mock(FiltroDeBusqueda.class);
		FiltroDeBusqueda filtro2 = mock(FiltroDeBusqueda.class);

		when(filtro1.cumple(muestra)).thenReturn(true);
		when(filtro2.cumple(muestra)).thenReturn(true);

		List<FiltroDeBusqueda> filtros = new ArrayList<>();
		filtros.add(filtro1);
		filtros.add(filtro2);

		FiltroDeBusqueda filtroAnd = new FiltroAnd(filtros);

		assertTrue(filtroAnd.cumple(muestra));
		verify(filtro1).cumple(muestra);
		verify(filtro2).cumple(muestra);
	}

	@Test
	public void testFiltroOrConUnFiltroQueCumple() {
		Muestra muestra = mock(Muestra.class);

		FiltroDeBusqueda filtro1 = mock(FiltroDeBusqueda.class);
		FiltroDeBusqueda filtro2 = mock(FiltroDeBusqueda.class);

		when(filtro1.cumple(muestra)).thenReturn(false);
		when(filtro2.cumple(muestra)).thenReturn(true);

		List<FiltroDeBusqueda> filtros = new ArrayList<>();
		filtros.add(filtro1);
		filtros.add(filtro2);

		FiltroDeBusqueda filtroOr = new FiltroOr(filtros);

		assertTrue(filtroOr.cumple(muestra));
		verify(filtro1).cumple(muestra);
		verify(filtro2).cumple(muestra);
	}

	@Test
	public void testFiltroAndConUnFiltroQueNoCumple() {
		Muestra muestra = mock(Muestra.class);

		FiltroDeBusqueda filtro1 = mock(FiltroDeBusqueda.class);
		FiltroDeBusqueda filtro2 = mock(FiltroDeBusqueda.class);

		when(filtro1.cumple(muestra)).thenReturn(true);
		when(filtro2.cumple(muestra)).thenReturn(false);

		List<FiltroDeBusqueda> filtros = new ArrayList<>();
		filtros.add(filtro1);
		filtros.add(filtro2);

		FiltroDeBusqueda filtroAnd = new FiltroAnd(filtros);

		assertFalse(filtroAnd.cumple(muestra));
		verify(filtro1).cumple(muestra);
		verify(filtro2).cumple(muestra);
	}
}