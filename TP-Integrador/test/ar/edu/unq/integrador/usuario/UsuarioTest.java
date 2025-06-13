package ar.edu.unq.integrador.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import ar.edu.unq.integrador.categoria.*;
import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.ubicacion.Ubicacion;


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
	
	@Test
	void test_UnUsuarioPuedeCrearUnFormulario() {
		// Setup
		String nombre = "Pepito";
		Categoria categoria = new Basico();
		List<Muestra> muestrasEnviadas = new ArrayList<>();
		List<Opinion> opinionesRealizadas = new ArrayList<>();
		Ubicacion ubicacion = mock(Ubicacion.class);
		
		Usuario usuario = new Usuario(nombre, categoria, muestrasEnviadas, opinionesRealizadas);
		
		// Exercise
		Formulario formulario = usuario.crearFormulario("foto_de_prueba", Concepto.VINCHUCA_GUASAYANA, ubicacion);
		
		// Verify
		assertEquals(usuario, formulario.getAutor());
		assertEquals("foto_de_prueba", formulario.getFoto());
		assertEquals(Concepto.VINCHUCA_GUASAYANA, formulario.getConcepto());
		assertEquals(ubicacion, formulario.getUbicacion());
	}
	
	@Test
	void test_UnUsuarioPuedeCrearUnaMuestraAPartirDeUnFormulario() {
		// Setup
		String nombre = "Pepito";
		Categoria categoria = new Basico();
		List<Muestra> muestrasEnviadas = new ArrayList<>();
		List<Opinion> opinionesRealizadas = new ArrayList<>();
		
		Ubicacion ubicacion = new Ubicacion(-34.70637371362339, -58.27837794516907); // unq
		
		Usuario usuario = new Usuario(nombre, categoria, muestrasEnviadas, opinionesRealizadas);
		
		// Exercise
		Formulario formulario = usuario.crearFormulario("foto_001", Concepto.VINCHUCA_GUASAYANA, ubicacion);
		
		Muestra muestraNueva = usuario.crearMuestra(formulario);
		
		// Verify
		assertEquals(muestraNueva.getAutor(), formulario.getAutor());
		assertEquals(muestraNueva.getFecha(), formulario.getFechaCreacion());
		assertEquals(muestraNueva.getFoto(), formulario.getFoto());
		assertEquals(muestraNueva.resultadoActual(), formulario.getConcepto());
	}
	
	@Test
	void test_UnUsuarioExpertoCreaUnaMuestraYUnBasicoYaNoPuedeOpinar() {
		// Setup
		Usuario usuarioExperto = new Usuario("Pepito Experto", new Experto(), new ArrayList<>(), new ArrayList<>());
		Formulario formularioUB = new Formulario ("Foto del experto", Concepto.VINCHUCA_GUASAYANA , new Ubicacion(1,1), usuarioExperto);
		Muestra muestraDelExperto = usuarioExperto.crearMuestra(formularioUB);
		
		// Exercise
		Usuario usuarioBasico = new Usuario("Pepito Basico", new Basico(), new ArrayList<>(), new ArrayList<>());
		
		Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioBasico.opinarSobreMuestra(Concepto.IMAGEN_POCO_CLARA, muestraDelExperto);});
		
		// Verify
		assertEquals("Solo pueden opinar Expertos!", excepcion.getMessage());
		assertEquals(muestraDelExperto.getOpiniones().size(),1);
	}
	
	@Test
	void test_UnUsuarioBasicoCreaUnaMuestraYElResultadoActualCambiaCuandoRecibeOpinionesDeOtrosUsuariosBasicos() {
		// Setup
		Usuario usuarioBasico1 = new Usuario("Pepito Basico 1", new Basico(), new ArrayList<>(), new ArrayList<>());
		Formulario formularioUB1 = new Formulario ("Foto del experto", Concepto.VINCHUCA_GUASAYANA , new Ubicacion(1,1), usuarioBasico1);
		Muestra muestraDelBasico1 = usuarioBasico1.crearMuestra(formularioUB1);
		
		Usuario usuarioBasico2 = new Usuario("Pepito Basico 2", new Basico(), new ArrayList<>(), new ArrayList<>());
		Usuario usuarioBasico3 = new Usuario("Pepito Basico 3", new Basico(), new ArrayList<>(), new ArrayList<>());
		
		assertEquals(muestraDelBasico1.getOpiniones().size(), 1);
		assertEquals(muestraDelBasico1.resultadoActual(), Concepto.VINCHUCA_GUASAYANA);
		
		// Exercise
		usuarioBasico2.opinarSobreMuestra(Concepto.VINCHUCA_INFESTANS, muestraDelBasico1);
		usuarioBasico3.opinarSobreMuestra(Concepto.VINCHUCA_INFESTANS, muestraDelBasico1);
		
		// Verify
		assertEquals(muestraDelBasico1.getOpiniones().size(), 3);
		assertEquals(muestraDelBasico1.resultadoActual(), Concepto.VINCHUCA_INFESTANS);	
	}
	
	@Test
	void test_UnUnicoExpertoOpinaLaMuestraDeUnBasicoNoHayConsensoEntreExpertosYElResultadoEsNoDefinido() {
		// Setup
		Usuario usuarioExperto = new Usuario("Pepito Experto", new Experto(), new ArrayList<>(), new ArrayList<>());
		Usuario usuarioBasico1 = new Usuario("Pepito Basico 1", new Basico(), new ArrayList<>(), new ArrayList<>());
		Formulario formularioUB1 = new Formulario ("Foto del experto", Concepto.VINCHUCA_GUASAYANA , new Ubicacion(1,1), usuarioBasico1);
		Muestra muestraDelBasico1 = usuarioBasico1.crearMuestra(formularioUB1);
		
		assertEquals(muestraDelBasico1.resultadoActual(), Concepto.VINCHUCA_GUASAYANA);
		
		// Exercise
		usuarioExperto.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestraDelBasico1);
		
		// Verify
		assertEquals(muestraDelBasico1.resultadoActual(), Concepto.NO_DEFINIDO); // cuando un experto opina necesita si o si de otro experto para validar la opinion, mientras tanto no se da un resultadoActual() especifico sino que se dice no deifinido		
	}
	
	 @Test
	 void test_DosExpertosOpinanIgualLaMuestraDeUnBasicoYHayResultadoActualDefinidoPorConsensoEntreExpertos() {
			// Setup
			Usuario usuarioExperto1 = new Usuario("Pepito Experto1", new Experto(), new ArrayList<>(), new ArrayList<>());
			Usuario usuarioExperto2 = new Usuario("Pepito Experto2", new Experto(), new ArrayList<>(), new ArrayList<>());
			Usuario usuarioBasico1 = new Usuario("Pepito Basico 1", new Basico(), new ArrayList<>(), new ArrayList<>());
			Formulario formularioUB1 = new Formulario ("Foto del experto", Concepto.VINCHUCA_GUASAYANA , new Ubicacion(1,1), usuarioBasico1);
			Muestra muestraDelBasico1 = usuarioBasico1.crearMuestra(formularioUB1);
			
			assertEquals(muestraDelBasico1.getOpiniones().size(), 1);
			assertEquals(muestraDelBasico1.resultadoActual(), Concepto.VINCHUCA_GUASAYANA);
			
			usuarioExperto1.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestraDelBasico1);
			
			assertEquals(muestraDelBasico1.getOpiniones().size(), 2);
			assertEquals(muestraDelBasico1.resultadoActual(), Concepto.NO_DEFINIDO);
			
			// Exercise
			usuarioExperto2.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestraDelBasico1);
			
			// Verify
			assertEquals(usuarioExperto2.getOpinionesRealizadas().size(), 1); // poner esto para todos los usuarios involucrados...
			assertEquals(muestraDelBasico1.getOpiniones().size(), 3);
			assertEquals(muestraDelBasico1.resultadoActual(), Concepto.CHINCHE_FOLIADA);
	 }
	 
	 @Test
	 void test_NoEsPosibleOpinarSobreUnaMuestraVerificada() {
			// Setup
			Usuario usuarioExperto = new Usuario("Pepito Experto", new Experto(), new ArrayList<>(), new ArrayList<>());
			Usuario usuarioExperto1 = new Usuario("Pepito Experto1", new Experto(), new ArrayList<>(), new ArrayList<>());
			Usuario usuarioExperto2 = new Usuario("Pepito Experto2", new Experto(), new ArrayList<>(), new ArrayList<>());
			Formulario formularioUE = new Formulario ("Foto del experto", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioExperto);
			Muestra muestraDelExperto = usuarioExperto.crearMuestra(formularioUE);
			
			assertEquals(usuarioExperto.getOpinionesRealizadas().size(), 1);
			assertEquals(muestraDelExperto.getOpiniones().size(), 1);
			assertEquals(muestraDelExperto.resultadoActual(), Concepto.NO_DEFINIDO); // hay una sola opinion, la del autor experto, por eso para retornar un resultado especifido necesita la validacion de otro experto al opinar igual
			
			usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestraDelExperto);
			
			assertEquals(usuarioExperto1.getOpinionesRealizadas().size(), 1);
			assertEquals(muestraDelExperto.getOpiniones().size(), 2);
			assertEquals(muestraDelExperto.resultadoActual(), Concepto.VINCHUCA_SORDIDA);
			
			// Exercise
			Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioExperto2.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestraDelExperto);});
			
			// Verify
			assertEquals("No se aceptan mas opiniones", excepcion.getMessage());
			assertEquals(usuarioExperto2.getOpinionesRealizadas().size(), 0);
			assertEquals(muestraDelExperto.getOpiniones().size(), 2);
			assertEquals(muestraDelExperto.resultadoActual(), Concepto.VINCHUCA_SORDIDA);		
	 }
	 
	 @Test
	 void test_UnUsuarioNoPuedeOpinarEnMuestrasCreadasPorElMismo() {
			// Setup
			Usuario usuarioExperto = new Usuario("Pepito Experto", new Experto(), new ArrayList<>(), new ArrayList<>());
			Formulario formularioUE = new Formulario ("Foto del experto", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioExperto);
			Muestra muestraDelExperto = usuarioExperto.crearMuestra(formularioUE);
			
			// Exercise
			Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioExperto.opinarSobreMuestra(Concepto.NINGUNA, muestraDelExperto);});
			
			// Verify
			assertEquals("El usuario ya registra una opinion en la muestra", excepcion.getMessage());
	 }
	 
	 @Test
	 void test_CuandoUnUsuarioCreaUnaMuestraEstaYaTieneLaOpinionDelUsuarioYElUsuarioRegistraLaOpinionEnSuHistorialDeOpinionesRealizadas() {
			// Setup
			Usuario usuarioBasico = new Usuario("Pepito Basico", new Basico(), new ArrayList<>(), new ArrayList<>());
			Formulario formularioUB = new Formulario ("Foto del basico", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioBasico);
			
			// Exercise
			Muestra muestraDelBasico = usuarioBasico.crearMuestra(formularioUB);
			
			//Verify
			assertEquals(muestraDelBasico.resultadoActual(), Concepto.VINCHUCA_SORDIDA);
			assertEquals(usuarioBasico.cantidadTotalDeOpinionesRealizadas(), 1);
	 }
	 
	 @Test
	 void test_CuandoUnUsuarioCreaUnaMuestraEstaYaTieneLaOpinionDelUsuarioYElUsuarioRegistraLaMuestraEnSuHistorialDeMuestrasEnviadas() {
			// Setup
			Usuario usuarioBasico = new Usuario("Pepito Basico", new Basico(), new ArrayList<>(), new ArrayList<>());
			Formulario formularioUB = new Formulario ("Foto del basico", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioBasico);
			
			// Exercise
			Muestra muestraDelBasico = usuarioBasico.crearMuestra(formularioUB);
			
			//Verify
			assertEquals(muestraDelBasico.resultadoActual(), Concepto.VINCHUCA_SORDIDA);
			assertEquals(usuarioBasico.cantidadTotalDeMuestrasEnviadas(), 1);
	 }
	
	 

}
