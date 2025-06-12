package ar.edu.unq.integrador.muestra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.estadoMuestra.*;

import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.opinion.*;

import ar.edu.unq.integrador.usuario.Usuario;

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
	
	@Test
	void test_MuestraNoVerificadaRecibeUnaOpinionExpertaYCambiaEstado() {
		// Setup
		EstadoMuestra estadoOg = new NoVerificada();
		List<Opinion> opinionesOg = new ArrayList<>();
		
		Usuario usuarioBasico1 = mock(Usuario.class);
		Usuario usuarioBasico2 = mock(Usuario.class);
		Usuario usuarioExperto = mock(Usuario.class);
		
		Opinion opinionBasica1 = mock(OpinionUsuarioBasico.class);
		when(opinionBasica1.esOpinionExperta()).thenReturn(false);
		when(opinionBasica1.getAutor()).thenReturn(usuarioBasico1);
		
		Opinion opinionBasica2 = mock(OpinionUsuarioBasico.class);
		when(opinionBasica2.esOpinionExperta()).thenReturn(false);
		when(opinionBasica2.getAutor()).thenReturn(usuarioBasico2);
		
		Opinion opinionExperta = mock(OpinionUsuarioExperto.class);
		when(opinionExperta.esOpinionExperta()).thenReturn(true);
		when(opinionExperta.getAutor()).thenReturn(usuarioExperto);
		
		// Exercise
		Muestra muestra = new Muestra(fecha, formulario, estadoOg, opinionesOg);
		
		muestra.recibirOpinion(opinionBasica1);
		muestra.recibirOpinion(opinionExperta);
		
		Exception excepcion = assertThrows(RuntimeException.class, () -> {muestra.recibirOpinion(opinionBasica2);});

		// Verify
		assertEquals(muestra.getOpiniones().size(), 2);
		assertEquals("Solo pueden opinar Expertos!", excepcion.getMessage()); // msj que tira cuando esta EnEvaluacion		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
