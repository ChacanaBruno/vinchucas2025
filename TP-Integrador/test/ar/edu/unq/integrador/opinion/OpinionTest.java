package ar.edu.unq.integrador.opinion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.usuario.Usuario;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

class OpinionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test_OpinionBasicoConstructor() {
		// Setup
		LocalDate fecha = mock(LocalDate.class);
		Usuario autor = mock(Usuario.class);
		Concepto concepto = mock(Concepto.class);
		Muestra muestra = mock(Muestra.class);
		
		// Exercise
		Opinion opinion = new OpinionUsuarioBasico(fecha, autor, concepto, muestra);
		
		// Verify
		assertEquals(fecha, opinion.getFecha());
		assertEquals(autor, opinion.getAutor());
		assertEquals(concepto, opinion.getConcepto());
		assertEquals(muestra, opinion.getMuestraOpinada());
	}
	
	@Test
	void test_OpinionExpertoConstructor() {
		// Setup
		LocalDate fecha = mock(LocalDate.class);
		Usuario autor = mock(Usuario.class);
		Concepto concepto = mock(Concepto.class);
		Muestra muestra = mock(Muestra.class);
		
		// Exercise
		Opinion opinion = new OpinionUsuarioExperto(fecha, autor, concepto,muestra);
		
		// Verify
		assertEquals(fecha, opinion.getFecha());
		assertEquals(autor, opinion.getAutor());
		assertEquals(concepto, opinion.getConcepto());
		assertEquals(muestra, opinion.getMuestraOpinada());
	}
}
