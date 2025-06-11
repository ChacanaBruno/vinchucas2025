package ar.edu.unq.integrador.muestra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.estadoMuestra.EstadoMuestra;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.opinion.Opinion;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class MuestraTest {
	Formulario formulario;
	EstadoMuestra estadoMuestra;
	Opinion op1;
	Opinion op2;
	List<Opinion> opiniones;

	@BeforeEach
	void setUp() throws Exception {
		formulario = mock(Formulario.class);
		estadoMuestra = mock(EstadoMuestra.class);
		op1 = mock(Opinion.class);
		op2 = mock(Opinion.class);
		opiniones = Arrays.asList(op1,op2);
	}

	@Test
	void test_MuestraConstructor() {		
		// Exercise
		Muestra muestra = new Muestra(formulario, estadoMuestra, opiniones);
		
		// Verify		
		assertEquals(formulario, muestra.getFormulario());
		assertEquals(estadoMuestra, muestra.getEstadoMuestra());
		assertEquals(opiniones, muestra.getOpiniones());
		assertEquals(2, opiniones.size());
	}
	
	@Test
	void test_MuestraSabeDecirQuienEsSuAutor() {
		// Exercise
		Muestra muestra = new Muestra(formulario, estadoMuestra, opiniones);
		Formulario formulario = muestra.getFormulario();
		muestra.getAutor();
		
		// Verify
		verify(formulario, times(1)).getAutor();
	}

}
