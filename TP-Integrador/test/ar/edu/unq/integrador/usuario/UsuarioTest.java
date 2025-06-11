package ar.edu.unq.integrador.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import ar.edu.unq.integrador.categoria.Categoria;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;


class UsuarioTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test_UsuarioContructor() {
		// Setup
		String nombre = "VinchucaKiller";
		Categoria categoria = mock(Categoria.class);
		List<Muestra> muestrasEnviadas = new ArrayList<>();
		List<Opinion> opinionesRealizadas = new ArrayList<>();
		
		// Exercise
		Usuario usuario = new Usuario(nombre, categoria, muestrasEnviadas, opinionesRealizadas);
		
		// Verify
		assertEquals(nombre, usuario.getNombre());
		assertEquals(categoria, usuario.getCategoria());
		assertEquals(muestrasEnviadas, usuario.getMuestrasEnviadas());
		assertEquals(opinionesRealizadas, usuario.getOpinionesRealizadas());	
	}

}
