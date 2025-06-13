package ar.edu.unq.integrador.usuario;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.categoria.Experto;
import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.ubicacion.Ubicacion;

class EspecialistaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test_UnEspecialistaCreaUnaMuestraYSoloPuedenOpinarExpertos() {
		// Setup
		Usuario especialista = new Especialista("Especialista");
		Formulario formularioE = new Formulario ("Foto del especialista", Concepto.VINCHUCA_GUASAYANA , new Ubicacion(1,1), especialista);
		Muestra muestraDelEespecialista = especialista.crearMuestra(formularioE);
		
		// Exercise
		Usuario usuarioBasico = new Usuario("Pepito Basico");
		
		Exception excepcion = assertThrows(RuntimeException.class, () -> {usuarioBasico.opinarSobreMuestra(Concepto.IMAGEN_POCO_CLARA, muestraDelEespecialista);});
		
		// Verify
		assertEquals("Solo pueden opinar Expertos!", excepcion.getMessage()); // El especialista es un experto! por ende el basico no puede opinar su muestra
		assertEquals(muestraDelEespecialista.getOpiniones().size(),1);
	}
	
	@Test
	void test_UnUnicoEspecialistaOpinaLaMuestraDeUnBasicoNoHayConsensoEntreExpertosYElResultadoEsNoDefinido() {
		// Setup
		Usuario especialista = new Especialista("Especialista");
		Usuario usuarioBasico = new Usuario("Pepito Basico");
		Formulario formularioUB = new Formulario ("Foto del experto", Concepto.VINCHUCA_GUASAYANA , new Ubicacion(1,1), usuarioBasico);
		Muestra muestraDelBasico = usuarioBasico.crearMuestra(formularioUB);
		
		assertEquals(muestraDelBasico.resultadoActual(), Concepto.VINCHUCA_GUASAYANA);
		
		// Exercise
		especialista.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestraDelBasico);
		
		// Verify
		assertEquals(muestraDelBasico.resultadoActual(), Concepto.NO_DEFINIDO); // cuando un especialista opina necesita si o si de otro especialista o experto para validar la opinion, mientras tanto no se da un resultadoActual() especifico sino que se dice no deifinido		
	}
	
	 @Test
	 void test_UnEspecialistaYUnExpertoOpinanIgualLaMuestraDeUnBasicoYHayResultadoActualDefinidoPorConsensoEntreExpertos() {
			// Setup
		    Usuario especialista = new Especialista("Especialista");
			Usuario usuarioExperto = new Usuario("Pepito Experto", new Experto(), new ArrayList<>(), new ArrayList<>());
			Usuario usuarioBasico = new Usuario("Pepito Basico");
			Formulario formularioUB = new Formulario ("Foto del experto", Concepto.VINCHUCA_GUASAYANA , new Ubicacion(1,1), usuarioBasico);
			Muestra muestraDelBasico = usuarioBasico.crearMuestra(formularioUB);
			
			assertEquals(muestraDelBasico.getOpiniones().size(), 1);
			assertEquals(muestraDelBasico.resultadoActual(), Concepto.VINCHUCA_GUASAYANA);
			
			especialista.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestraDelBasico);
			
			assertEquals(muestraDelBasico.getOpiniones().size(), 2);
			assertEquals(muestraDelBasico.resultadoActual(), Concepto.NO_DEFINIDO);
			
			// Exercise
			usuarioExperto.opinarSobreMuestra(Concepto.CHINCHE_FOLIADA, muestraDelBasico);
			
			// Verify
			assertEquals(especialista.cantidadTotalDeOpinionesRealizadas(), 1);
			assertEquals(usuarioExperto.cantidadTotalDeOpinionesRealizadas(), 1);
			assertEquals(muestraDelBasico.getOpiniones().size(), 3);
			assertEquals(muestraDelBasico.resultadoActual(), Concepto.CHINCHE_FOLIADA); // se validó la opinion del especialista
	 }
	 
	 @Test
	 void test_UnEspecialistaNoPuedeSerPromovidoAExpertoPorqueSiempreEsExperto() {
		 // Setup
		 Usuario especialista = new Especialista("Juancito");
		 
		 // Exercise
		 Exception excepcion = assertThrows(RuntimeException.class, () -> {especialista.promoverAExperto();});
		 
		 // Verify
		 assertEquals("El usuario ya es Experto", excepcion.getMessage());
	 }
	 
	 @Test
	 void test_UnEspecialistaNoPuedeSerDegradadoABasico() {
		 // Setup
		 Usuario especialista = new Especialista("Juancito");
		 
		 // Exercise
		 Exception excepcion = assertThrows(RuntimeException.class, () -> {especialista.degradarABasico();});
		 
		 // Verify
		 assertEquals("Un Especialista no puede ser degradado a Basico porque tiene validacion externa", excepcion.getMessage());
	 }

}
