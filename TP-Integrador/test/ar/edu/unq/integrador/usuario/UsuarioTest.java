package ar.edu.unq.integrador.usuario;

import static org.junit.jupiter.api.Assertions.*;

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
	 
	 @Test
	 void test_UsuarioConstructorQueCreaUsuariosConCategoriaBasicoPorDefaultSinMuestrasEnviadasNiOpinionesRealizadas() {
		 // Setup
		 Usuario usuarioDefault = new Usuario("Pepito deafult"); // Constructor con categoria basica default, para usuario nuevos!
		 
		 assertEquals(usuarioDefault.cantidadTotalDeMuestrasEnviadas(), 0);
		 assertEquals(usuarioDefault.cantidadTotalDeOpinionesRealizadas(), 0);
		 
		 // Exercise
		 Usuario usuarioExperto = new Usuario("Pepito Experto", new Experto(), new ArrayList<>(), new ArrayList<>());
		 Formulario formularioUE = new Formulario ("Foto del experto", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioExperto);
		 Muestra muestraDelExperto = usuarioExperto.crearMuestra(formularioUE);
		
		 Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioDefault.opinarSobreMuestra(Concepto.NINGUNA, muestraDelExperto);});
		
		 // Verify
		 assertEquals("Solo pueden opinar Expertos!", excepcion.getMessage()); // excepcion que tira cuando un basico quiere opinar una muestra con un estado distinto a NoVerificado
		 assertEquals(usuarioDefault.cantidadTotalDeMuestrasEnviadas(), 0);
		 assertEquals(usuarioDefault.cantidadTotalDeOpinionesRealizadas(), 0); 
	 }
	 
	 @Test
	 void test_UnUsuarioBasicoSeConvierteEnExpertoSiCumpleLosRequisitos() {
		 // Setup
		 Usuario usuarioBasico1 = new Usuario("Usuario Basico 1"); // este envia las muestras, ese el que va a subir de rango
		 Usuario usuarioBasico2 = new Usuario("Usuario Basico 2"); // a este le comenta
		 Usuario usuarioExperto1 = new Usuario("Usuario Experto 1", new Experto(), new ArrayList<>(), new ArrayList<>()); // este verifica las muestras
		 Usuario usuarioExperto2 = new Usuario("Usuario Experto 2", new Experto(), new ArrayList<>(), new ArrayList<>()); // este verifica las muestras
		 
		 Formulario formularioUB1 = new Formulario ("Foto del Usuario Basico 1", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioBasico1); // el formulario se puede repetir, no hay nada que lo impida y no creo que sea inconveniente, ya que se trabaja a nivel muestra no a nivel formulario
		 Formulario formularioUB2 = new Formulario ("Foto del Usuario Basico 2", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioBasico2);
		 
		 Muestra muestra1UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra2UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra3UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra4UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra5UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra6UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra7UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra8UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra9UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra10UB1 = usuarioBasico1.crearMuestra(formularioUB1); // los 10 envios de muestras del usuarioBasico1, 10 muestras distintas. Estas tambien cuentan como 10 opiniones!
		 
		 Muestra muestra1UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra2UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra3UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra4UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra5UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra6UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra7UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra8UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra9UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra10UB2 = usuarioBasico2.crearMuestra(formularioUB2); // las 10 muestras que tiene que opinar el usuarioBasico1
		 
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra1UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra2UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra3UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra4UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra5UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra6UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra7UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra8UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra9UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra10UB2); // sus 10 opiniones
		 
		 assertEquals(usuarioBasico1.cantidadTotalDeMuestrasEnviadas(), 10);
		 assertEquals(usuarioBasico1.cantidadTotalDeOpinionesRealizadas(), 20);
		 
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra1UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra2UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra3UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra4UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra5UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra6UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra7UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra8UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra9UB1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra10UB1); // expertos verificando las muestras del user que va a subir de rango
		 
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra1UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra2UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra3UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra4UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra5UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra6UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra7UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra8UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra9UB1);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra10UB1); // expertos verificando las muestras del user que va a subir de rango
		 
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra1UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra2UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra3UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra4UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra5UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra6UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra7UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra8UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra9UB2);
		 usuarioExperto1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra10UB2); // experto verifica muestra en la que opino el que va a subir de rango
		 
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra1UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra2UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra3UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra4UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra5UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra6UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra7UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra8UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra9UB2);
		 usuarioExperto2.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra10UB2); // experto verifica muestra en la que opino el que va a subir de rango, entonces la opinion del basico ahora coincide con el resultadoActual de la muestra verificada!
		 
		 assertEquals(usuarioBasico1.cantidadTotalDeMuestrasEnviadas(), 10);
		 assertEquals(usuarioBasico1.cantidadTotalDeOpinionesRealizadas(), 20);
		 
		 // Exercise
		 usuarioBasico1.promoverAExperto();
		 Muestra muestraDelExperto = usuarioExperto1.crearMuestra(formularioUB1); // da igual el formulario, solo necesito la muestra
		 usuarioBasico1.opinarSobreMuestra(Concepto.IMAGEN_POCO_CLARA, muestraDelExperto); // puede opinar porque ahora el basico es experto
		 
		 // Verify
		 assertEquals(usuarioBasico1.cantidadTotalDeOpinionesRealizadas(), 21); 
	 }
	
	 @Test
	 void test_UnUsuarioExpertoNoPuedeSerPromovidoAExperto() {
		 // Setup
		 Usuario usuarioExperto1 = new Usuario("Usuario Experto 1", new Experto(), new ArrayList<>(), new ArrayList<>());
		 
		 // Exercise
		 Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioExperto1.promoverAExperto();});
		 
		 // Verify
		 assertEquals("El usuario ya es Experto", excepcion.getMessage());
	 }
	 
	 @Test
	 void test_UnUsuarioBasicoNoSeConvierteEnExpertoSiNoCumpleLosRequisitos() {
		 // Setup
		 Usuario usuarioBasico1 = new Usuario("Usuario Basico 1"); // este envia las muestras, ese el que va a intentar subir de rango
		 Usuario usuarioBasico2 = new Usuario("Usuario Basico 2"); // a este le comenta
		 
		 Formulario formularioUB1 = new Formulario ("Foto del Usuario Basico 1", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioBasico1); // el formulario se puede repetir, no hay nada que lo impida y no creo que sea inconveniente, ya que se trabaja a nivel muestra no a nivel formulario
		 Formulario formularioUB2 = new Formulario ("Foto del Usuario Basico 2", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioBasico2);
		 
		 Muestra muestra1UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra2UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra3UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra4UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra5UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra6UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra7UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra8UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra9UB1 = usuarioBasico1.crearMuestra(formularioUB1);
		 Muestra muestra10UB1 = usuarioBasico1.crearMuestra(formularioUB1); // los 10 envios de muestras del usuarioBasico1, 10 muestras distintas. Estas tambien cuentan como 10 opiniones!
		 
		 Muestra muestra1UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra2UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra3UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra4UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra5UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra6UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra7UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra8UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra9UB2 = usuarioBasico2.crearMuestra(formularioUB2);
		 Muestra muestra10UB2 = usuarioBasico2.crearMuestra(formularioUB2); // las 10 muestras que tiene que opinar el usuarioBasico1
		 
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra1UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra2UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra3UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra4UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra5UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra6UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra7UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra8UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra9UB2);
		 usuarioBasico1.opinarSobreMuestra(Concepto.VINCHUCA_SORDIDA, muestra10UB2); // sus 10 opiniones
		 
		 assertEquals(usuarioBasico1.cantidadTotalDeMuestrasEnviadas(), 10);
		 assertEquals(usuarioBasico1.cantidadTotalDeOpinionesRealizadas(), 20); // cumple con los 10 envios y las 20 opiniones, pero su opinion no valió para verificar la muestra.
		 
		 // Exercise
		 Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioBasico1.promoverAExperto();});
		 
		 // Verify
		 assertEquals("El usuario no cumple con los requisitos necesarios para ser Experto", excepcion.getMessage());
	 }
	 
	 @Test
	 void test_UnUsuarioBasicoNoPuedeDegradarseABasico() {	
		 // Setup
		 Usuario usuarioBasico = new Usuario("Basic");
		
		 // Exercise
		 Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioBasico.degradarABasico();});
		 
		 // Verify
		 assertEquals("El usuario ya es Basico", excepcion.getMessage());
	 }
	 
	 @Test
	 void test_UnUsuarioExpertoPuedeDegradarseABasico() {		 
		 // Setup
		 Usuario usuarioExperto1 = new Usuario("Usuario Experto 1", new Experto(), new ArrayList<>(), new ArrayList<>());
		 
		 Usuario usuarioExperto2 = new Usuario("Usuario Experto 2", new Experto(), new ArrayList<>(), new ArrayList<>());
		 
		 Formulario formulario1 = new Formulario ("Foto", Concepto.VINCHUCA_SORDIDA , new Ubicacion(1,1), usuarioExperto2);
		 
		 Formulario formulario2 = new Formulario ("Foto", Concepto.VINCHUCA_INFESTANS, new Ubicacion(1,1), usuarioExperto2);
		 
		 Muestra muestra1DeExperto2 = usuarioExperto2.crearMuestra(formulario1);
		 usuarioExperto1.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestra1DeExperto2);
		 
		 assertEquals(muestra1DeExperto2.resultadoActual(), Concepto.NO_DEFINIDO); // experto1 puede opinar porque es experto y deja el resultado en empate
		 
		 // Exercise
		 usuarioExperto1.degradarABasico();
		 
		 Muestra muestra2DeExperto2 = usuarioExperto2.crearMuestra(formulario2); 
		 
		 Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioExperto1.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestra1DeExperto2);}); // experto1 no va a poder opinar porque ahora es basico
		 
		 // Verify
		 assertEquals("Solo pueden opinar Expertos!", excepcion.getMessage());
	 }

}
