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

}
