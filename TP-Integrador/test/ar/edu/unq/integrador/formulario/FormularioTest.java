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
	void test_FormulariosConMismoConceptoYUbicacionSonDistintos() {
	    // Setup
	    Concepto concepto = Concepto.VINCHUCA_SORDIDA;
	    Ubicacion ubicacion = new Ubicacion(-34.701, -58.276);

	    Usuario autor1 = new Usuario("Usuario1");
	    Usuario autor2 = new Usuario("Usuario2");
	    Usuario autor3 = new Usuario("Usuario3");
	    Usuario autor4 = new Usuario("Usuario4");
	    Usuario autor5 = new Usuario("Usuario5");
	    Usuario autor6 = new Usuario("Usuario6");
	    Usuario autor7 = new Usuario("Usuario7");
	    Usuario autor8 = new Usuario("Usuario8");
	    Usuario autor9 = new Usuario("Usuario9");
	    Usuario autor10 = new Usuario("Usuario10");

	    Formulario f1 = new Formulario("foto1", concepto, ubicacion, autor1);
	    Formulario f2 = new Formulario("foto2", concepto, ubicacion, autor2);
	    Formulario f3 = new Formulario("foto3", concepto, ubicacion, autor3);
	    Formulario f4 = new Formulario("foto4", concepto, ubicacion, autor4);
	    Formulario f5 = new Formulario("foto5", concepto, ubicacion, autor5);
	    Formulario f6 = new Formulario("foto6", concepto, ubicacion, autor6);
	    Formulario f7 = new Formulario("foto7", concepto, ubicacion, autor7);
	    Formulario f8 = new Formulario("foto8", concepto, ubicacion, autor8);
	    Formulario f9 = new Formulario("foto9", concepto, ubicacion, autor9);
	    Formulario f10 = new Formulario("foto10", concepto, ubicacion, autor10);

	    // Verify
	    assertNotSame(f1, f2);
	    assertNotSame(f2, f3);
	    assertNotSame(f3, f4);
	    assertNotSame(f4, f5);
	    assertNotSame(f5, f6);
	    assertNotSame(f6, f7);
	    assertNotSame(f7, f8);
	    assertNotSame(f8, f9);
	    assertNotSame(f9, f10);

	    assertEquals(concepto, f1.getConcepto());
	    assertEquals(ubicacion, f10.getUbicacion());
	    assertEquals("foto1", f1.getFoto());
	    assertEquals("foto10", f10.getFoto());
	    assertEquals(autor1, f1.getAutor());
	    assertEquals(autor10, f10.getAutor());
	}

}
