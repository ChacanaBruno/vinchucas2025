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

}
