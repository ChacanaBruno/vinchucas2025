package ar.edu.unq.integrador.muestra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.estadoMuestra.EstadoMuestra;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.opinion.Opinion;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MuestraTest {
	LocalDate fecha;
	Formulario formulario;
	EstadoMuestra estadoMuestra;
	Opinion op1;
	Opinion op2;
	Opinion op3;
	List<Opinion> opiniones;

	@BeforeEach
	void setUp() throws Exception {
		fecha = mock(LocalDate.class);
		formulario = mock(Formulario.class);
		estadoMuestra = mock(EstadoMuestra.class);
		op1 = mock(Opinion.class);
		op2 = mock(Opinion.class);
		op3 = mock(Opinion.class);
		opiniones = Arrays.asList(op1,op2);
	}

	@Test
	void test_MuestraConstructor() {		
		// Exercise
		Muestra muestra = new Muestra(fecha, formulario, estadoMuestra, opiniones);
		
		// Verify
		assertEquals(fecha, muestra.getFecha());
		assertEquals(formulario, muestra.getFormulario());
		assertEquals(estadoMuestra, muestra.getEstadoMuestra());
		assertEquals(opiniones, muestra.getOpiniones());
		assertEquals(2, opiniones.size());
	}
	
	@Test
	void test_MuestraSabeSuAutor() {
		// Exercise
		Muestra muestra = new Muestra(fecha, formulario, estadoMuestra, opiniones);
		Formulario formulario = muestra.getFormulario();
		muestra.getAutor();
		
		// Verify
		verify(formulario, times(1)).getAutor();
	}
	
	@Test
	void test_MuestraSabeSuFoto() {
		// Exercise
		Muestra muestra = new Muestra(fecha, formulario, estadoMuestra, opiniones);
		Formulario formulario = muestra.getFormulario();
		muestra.getFoto();
		
		// Verify
		verify(formulario, times(1)).getFoto();
	}
	
	@Test
	void test_MuestraSabeSuUbicacion() {
		// Exercise
		Muestra muestra = new Muestra(fecha, formulario, estadoMuestra, opiniones);
		Formulario formulario = muestra.getFormulario();
		muestra.getUbicacion();
		
		// Verify
		verify(formulario, times(1)).getUbicacion();
	}
	
	@Test
	void test_MuestraAgregaUnaOpinion() {
		// Setup
		List<Opinion> ops = new ArrayList<>();
		
		// Exercise
		Muestra muestra = new Muestra(fecha, formulario, estadoMuestra, ops);
		muestra.agregarOpinion(op3);
		
		// Verify
		assertEquals(1, muestra.getOpiniones().size());
	}

}
