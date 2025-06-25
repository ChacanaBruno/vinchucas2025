package ar.edu.unq.integrador.formulario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.usuario.Usuario;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

class FormularioTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test_FormularioConstructor() {
		// Setup
		String foto = "foto001";
		Concepto concepto = Concepto.VINCHUCA_GUASAYANA;
		Ubicacion ubicacion = mock(Ubicacion.class);
		Usuario autor = mock(Usuario.class);
		
		// Excercise
		Formulario formulario = new Formulario(foto, concepto, ubicacion, autor);
		
		// Verify
		assertEquals(foto, formulario.getFoto());
		assertEquals(concepto, formulario.getConcepto());
		assertEquals(ubicacion, formulario.getUbicacion());
		assertEquals(autor, formulario.getAutor());
		assertEquals(LocalDate.now(), formulario.getFechaCreacion());
	}
	
	@Test
	void test_FormulariosConMismaEspecieYUbicacionPeroSonInstanciasDistintas() {
	    Ubicacion ubicacion = new Ubicacion(-34.778, -58.296);
	    Usuario autor = new Usuario("Autor");
	    Concepto concepto = Concepto.VINCHUCA_GUASAYANA;

	    Formulario f1 = new Formulario("foto1", concepto, ubicacion, autor);
	    Formulario f2 = new Formulario("foto2", concepto, ubicacion, autor);
	    Formulario f3 = new Formulario("foto3", concepto, ubicacion, autor);
	    Formulario f4 = new Formulario("foto4", concepto, ubicacion, autor);
	    Formulario f5 = new Formulario("foto5", concepto, ubicacion, autor);
	    Formulario f6 = new Formulario("foto6", concepto, ubicacion, autor);
	    Formulario f7 = new Formulario("foto7", concepto, ubicacion, autor);
	    Formulario f8 = new Formulario("foto8", concepto, ubicacion, autor);
	    Formulario f9 = new Formulario("foto9", concepto, ubicacion, autor);
	    Formulario f10 = new Formulario("foto10", concepto, ubicacion, autor);
	    Formulario f11 = new Formulario("foto11", concepto, ubicacion, autor);
	    Formulario f12 = new Formulario("foto12", concepto, ubicacion, autor);
	    Formulario f13 = new Formulario("foto13", concepto, ubicacion, autor);
	    Formulario f14 = new Formulario("foto14", concepto, ubicacion, autor);
	    Formulario f15 = new Formulario("foto15", concepto, ubicacion, autor);
	    Formulario f16 = new Formulario("foto16", concepto, ubicacion, autor);
	    Formulario f17 = new Formulario("foto17", concepto, ubicacion, autor);
	    Formulario f18 = new Formulario("foto18", concepto, ubicacion, autor);
	    Formulario f19 = new Formulario("foto19", concepto, ubicacion, autor);
	    Formulario f20 = new Formulario("foto20", concepto, ubicacion, autor);
	    Formulario f21 = new Formulario("foto21", concepto, ubicacion, autor);
	    Formulario f22 = new Formulario("foto22", concepto, ubicacion, autor);
	    Formulario f23 = new Formulario("foto23", concepto, ubicacion, autor);
	    Formulario f24 = new Formulario("foto24", concepto, ubicacion, autor);
	    Formulario f25 = new Formulario("foto25", concepto, ubicacion, autor);
	    Formulario f26 = new Formulario("foto26", concepto, ubicacion, autor);
	    Formulario f27 = new Formulario("foto27", concepto, ubicacion, autor);
	    Formulario f28 = new Formulario("foto28", concepto, ubicacion, autor);
	    Formulario f29 = new Formulario("foto29", concepto, ubicacion, autor);
	    Formulario f30 = new Formulario("foto30", concepto, ubicacion, autor);
	    Formulario f31 = new Formulario("foto31", concepto, ubicacion, autor);
	    Formulario f32 = new Formulario("foto32", concepto, ubicacion, autor);
	    Formulario f33 = new Formulario("foto33", concepto, ubicacion, autor);
	    Formulario f34 = new Formulario("foto34", concepto, ubicacion, autor);
	    Formulario f35 = new Formulario("foto35", concepto, ubicacion, autor);
	    Formulario f36 = new Formulario("foto36", concepto, ubicacion, autor);
	    Formulario f37 = new Formulario("foto37", concepto, ubicacion, autor);
	    Formulario f38 = new Formulario("foto38", concepto, ubicacion, autor);
	    Formulario f39 = new Formulario("foto39", concepto, ubicacion, autor);
	    Formulario f40 = new Formulario("foto40", concepto, ubicacion, autor);
	    Formulario f41 = new Formulario("foto41", concepto, ubicacion, autor);
	    Formulario f42 = new Formulario("foto42", concepto, ubicacion, autor);
	    Formulario f43 = new Formulario("foto43", concepto, ubicacion, autor);
	    Formulario f44 = new Formulario("foto44", concepto, ubicacion, autor);
	    Formulario f45 = new Formulario("foto45", concepto, ubicacion, autor);
	    Formulario f46 = new Formulario("foto46", concepto, ubicacion, autor);
	    Formulario f47 = new Formulario("foto47", concepto, ubicacion, autor);
	    Formulario f48 = new Formulario("foto48", concepto, ubicacion, autor);
	    Formulario f49 = new Formulario("foto49", concepto, ubicacion, autor);
	    Formulario f50 = new Formulario("foto50", concepto, ubicacion, autor);

	    // Verify
	    assertNotSame(f1, f2);
	    assertNotSame(f25, f30);
	    assertNotSame(f10, f50);
	    assertNotSame(f3, f48);
	    assertNotSame(f5, f45);
	}

}
