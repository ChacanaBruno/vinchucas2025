package ar.edu.unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;

public class CriterioBusquedaTest {

    private Muestra muestraMock;

    @BeforeEach
    public void setUp() {
        muestraMock = mock(Muestra.class);
    }

    @Test
    public void testCriterioCumpleConUnFiltroMock() {
        FiltroDeBusqueda filtro = mock(FiltroDeBusqueda.class);
        when(filtro.cumple(muestraMock)).thenReturn(true);

        CriterioBusqueda criterio = new CriterioBusqueda(List.of(filtro));

        assertTrue(criterio.cumple(muestraMock));
        verify(filtro).cumple(muestraMock);
    }

    @Test
    public void testCriterioNoCumpleConAlgunFiltroMock() {
        FiltroDeBusqueda filtro1 = mock(FiltroDeBusqueda.class);
        FiltroDeBusqueda filtro2 = mock(FiltroDeBusqueda.class);

        when(filtro1.cumple(muestraMock)).thenReturn(true);
        when(filtro2.cumple(muestraMock)).thenReturn(false);

        CriterioBusqueda criterio = new CriterioBusqueda(List.of(filtro1, filtro2));

        assertFalse(criterio.cumple(muestraMock));
    }

    @Test
    public void testFiltroTipoInsectoCumple() {
        when(muestraMock.resultadoActual()).thenReturn(Concepto.CHINCHE_FOLIADA);

        FiltroDeBusqueda filtro = new FiltroTipoInsecto(Concepto.CHINCHE_FOLIADA);

        assertTrue(filtro.cumple(muestraMock));
    }

    @Test
    public void testFiltroNivelVerificacionNoCumple() {
        when(muestraMock.estaVerificada()).thenReturn(false);

        FiltroDeBusqueda filtro = new FiltroNivelVerificacion();

        assertFalse(filtro.cumple(muestraMock));
    }

    @Test
    public void testFiltroAndCumple() {
        FiltroDeBusqueda f1 = mock(FiltroDeBusqueda.class);
        FiltroDeBusqueda f2 = mock(FiltroDeBusqueda.class);

        when(f1.cumple(muestraMock)).thenReturn(true);
        when(f2.cumple(muestraMock)).thenReturn(true);

		List<FiltroDeBusqueda> filtros = new ArrayList<>();
		filtros.add(f1);
		filtros.add(f2);

        FiltroAnd filtroAnd = new FiltroAnd(filtros);

        assertTrue(filtroAnd.cumple(muestraMock));
    }

    @Test
    public void testFiltroOrCumple() {
        FiltroDeBusqueda f1 = mock(FiltroDeBusqueda.class);
        FiltroDeBusqueda f2 = mock(FiltroDeBusqueda.class);

        when(f1.cumple(muestraMock)).thenReturn(false);
        when(f2.cumple(muestraMock)).thenReturn(true);

        
		List<FiltroDeBusqueda> filtros = new ArrayList<>();
		filtros.add(f1);
		filtros.add(f2);
        
        FiltroOr filtroOr = new FiltroOr(filtros);

        assertTrue(filtroOr.cumple(muestraMock));
    }

    @Test
    public void testFiltroFechaCreacionPosteriorCumple() {
        LocalDate fecha = LocalDate.of(2023, 1, 1);
        when(muestraMock.getFecha()).thenReturn(LocalDate.of(2024, 1, 1));

        FiltroFechaCreacionPosteriorA filtro = new FiltroFechaCreacionPosteriorA(fecha);

        assertTrue(filtro.cumple(muestraMock));
    }

    @Test
    public void testFiltroFechaUltimaVotacionAnteriorNoCumple() {
        LocalDate fecha = LocalDate.of(2023, 1, 1);
        when(muestraMock.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2024, 1, 1));

        FiltroFechaUltimaVotacionAnteriorA filtro = new FiltroFechaUltimaVotacionAnteriorA(fecha);

        assertFalse(filtro.cumple(muestraMock));
    }
    
	@Test
	public void testFiltroFechaCreacionAnteriorADevuelveTrue() {
		Muestra muestra = mock(Muestra.class);
		LocalDate fechaFiltro = LocalDate.of(2024, 1, 1);
		LocalDate fechaMuestra = LocalDate.of(2023, 12, 31);

		when(muestra.getFecha()).thenReturn(fechaMuestra);

		FiltroDeBusqueda filtro = new FiltroFechaCreacionAnteriorA(fechaFiltro);

		assertTrue(filtro.cumple(muestra));
	}

	@Test
	public void testFiltroFechaCreacionAnteriorADevuelveFalse() {
		Muestra muestra = mock(Muestra.class);
		LocalDate fechaFiltro = LocalDate.of(2024, 1, 1);
		LocalDate fechaMuestra = LocalDate.of(2024, 1, 2);

		when(muestra.getFecha()).thenReturn(fechaMuestra);

		FiltroDeBusqueda filtro = new FiltroFechaCreacionAnteriorA(fechaFiltro);

		assertFalse(filtro.cumple(muestra));
	}

	@Test
	public void testFiltroFechaCreacionIgualDevuelveTrue() {
		Muestra muestra = mock(Muestra.class);
		LocalDate fecha = LocalDate.of(2024, 5, 20);

		when(muestra.getFecha()).thenReturn(fecha);

		FiltroDeBusqueda filtro = new FiltroFechaCreacionIgual(fecha);

		assertTrue(filtro.cumple(muestra));
	}

	@Test
	public void testFiltroFechaCreacionIgualDevuelveFalse() {
		Muestra muestra = mock(Muestra.class);
		LocalDate fechaFiltro = LocalDate.of(2024, 5, 20);
		LocalDate fechaMuestra = LocalDate.of(2024, 5, 21);

		when(muestra.getFecha()).thenReturn(fechaMuestra);

		FiltroDeBusqueda filtro = new FiltroFechaCreacionIgual(fechaFiltro);

		assertFalse(filtro.cumple(muestra));
	}

	@Test
	public void testFiltroFechaUltimaVotacionIgualDevuelveTrue() {
		Muestra muestra = mock(Muestra.class);
		LocalDate fecha = LocalDate.of(2024, 6, 15);

		when(muestra.getFechaUltimaVotacion()).thenReturn(fecha);

		FiltroDeBusqueda filtro = new FiltroFechaUltimaVotacionIgual(fecha);

		assertTrue(filtro.cumple(muestra));
	}

	@Test
	public void testFiltroFechaUltimaVotacionIgualDevuelveFalse() {
		Muestra muestra = mock(Muestra.class);
		LocalDate fechaFiltro = LocalDate.of(2024, 6, 15);
		LocalDate fechaMuestra = LocalDate.of(2024, 6, 14);

		when(muestra.getFechaUltimaVotacion()).thenReturn(fechaMuestra);

		FiltroDeBusqueda filtro = new FiltroFechaUltimaVotacionIgual(fechaFiltro);

		assertFalse(filtro.cumple(muestra));
	}

	@Test
	public void testFiltroFechaUltimaVotacionPosteriorADevuelveTrue() {
		Muestra muestra = mock(Muestra.class);
		LocalDate fechaFiltro = LocalDate.of(2024, 1, 1);
		LocalDate fechaMuestra = LocalDate.of(2024, 2, 1);

		when(muestra.getFechaUltimaVotacion()).thenReturn(fechaMuestra);

		FiltroDeBusqueda filtro = new FiltroFechaUltimaVotacionPosteriorA(fechaFiltro);

		assertTrue(filtro.cumple(muestra));
	}

	@Test
	public void testFiltroFechaUltimaVotacionPosteriorADevuelveFalse() {
		Muestra muestra = mock(Muestra.class);
		LocalDate fechaFiltro = LocalDate.of(2024, 2, 1);
		LocalDate fechaMuestra = LocalDate.of(2024, 1, 1);

		when(muestra.getFechaUltimaVotacion()).thenReturn(fechaMuestra);

		FiltroDeBusqueda filtro = new FiltroFechaUltimaVotacionPosteriorA(fechaFiltro);

		assertFalse(filtro.cumple(muestra));
	}

}
