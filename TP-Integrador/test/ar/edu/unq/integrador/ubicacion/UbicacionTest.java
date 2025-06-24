package ar.edu.unq.integrador.ubicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.usuario.Usuario;

class UbicacionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test_UbicacionConstructor() {
		// Setup
		double latitud = -34.70;
		double longitud = -58.27;
		
		// Exercise
		Ubicacion ubicacion = new Ubicacion(latitud,longitud);
		
		// Verify
		assertEquals(latitud, ubicacion.getLatitud());
		assertEquals(longitud, ubicacion.getLongitud());		
	}
	
	@Test
	void test_UnaUbicacionPuedeCalcularLaDistanciaEnKilometrosAOtraUbicacion() {
		// Setup
		Ubicacion ubicacion1 = new Ubicacion(-34.70937193975776, -58.2804081705572); // estacion de bernal 
		Ubicacion ubicacion2 = new Ubicacion(-34.70637371362339, -58.27837794516907); // unq
		
		// Exercise
		double distancia = ubicacion1.distanciaALaUbicacion(ubicacion2);
		
		// Verify
		assertEquals(distancia, 0.38155985286411503); // 0.38155985286411503 kms -> 400 mts
	}
	
	@Test
	void test_UnaUbicacionPuedeDecirAPartirDeUnaListaDeUbicacionesCualesEstanAMenosDeXKilometros() {
		// Setup
		Ubicacion origen = new Ubicacion(-34.70637371362339, -58.27837794516907); // unq
		Ubicacion ubicacion1 = new Ubicacion(-34.70937193975776, -58.2804081705572); // estacion de bernal -> 0.38 kms de distancia
		Ubicacion ubicacion2 = new Ubicacion(-34.72430929327392, -58.26083800020545); // estacion de quilmes -> 2.55 kms de distancia
		Ubicacion ubicacion3 = new Ubicacion(-34.75166004604107, -58.234313391223694); // estacion de ezpeleta -> 6.44 kms de distancia
		
		List<Ubicacion> ubicaciones = Arrays.asList(ubicacion1, ubicacion2, ubicacion3);
		
		// Exercise
		List<Ubicacion> resultado = origen.ubicacionesAMenosDeXKilometros(3, ubicaciones);
		
		// Verify
		assertTrue(resultado.contains(ubicacion1));
		assertTrue(resultado.contains(ubicacion2));
		assertFalse(resultado.contains(ubicacion3)); // ezpeleta esta a mas de 3 kms
	}
	
	@Test
	void test_UnaUbicacionPuedeDecirAPartirDeUnaListaDeMuestrasCualesEstanAMenosDeXKilometros() {
		// Setup
		Ubicacion origen = new Ubicacion(-34.70637371362339, -58.27837794516907); // unq
		Ubicacion ubicacion1 = new Ubicacion(-34.70937193975776, -58.2804081705572); // estacion de bernal -> 0.38 kms de distancia
		Ubicacion ubicacion2 = new Ubicacion(-34.72430929327392, -58.26083800020545); // estacion de quilmes -> 2.55 kms de distancia
		Ubicacion ubicacion3 = new Ubicacion(-34.75166004604107, -58.234313391223694); // estacion de ezpeleta -> 6.44 kms de distancia
		
		Usuario usuarioBasico1 = new Usuario("Usuario Basico 1");
		
		Formulario formularioUB1 = new Formulario ("Foto", Concepto.VINCHUCA_SORDIDA , ubicacion1, usuarioBasico1);
		Muestra muestra1 = usuarioBasico1.crearMuestra(formularioUB1);
		
		Formulario formularioUB2 = new Formulario ("Foto", Concepto.VINCHUCA_INFESTANS , ubicacion2, usuarioBasico1);
		Muestra muestra2 = usuarioBasico1.crearMuestra(formularioUB2);
		
		Formulario formularioUB3 = new Formulario ("Foto", Concepto.VINCHUCA_SORDIDA, ubicacion3, usuarioBasico1);
		Muestra muestra3 = usuarioBasico1.crearMuestra(formularioUB3);
		
		List<Muestra> muestras = Arrays.asList(muestra1, muestra2, muestra3);
		
		// Exercise
		List<Muestra> resultado = origen.muestrasAMenosDeXKilometros(3, muestras);
		
		// Verify
		assertTrue(resultado.contains(muestra1));
		assertTrue(resultado.contains(muestra2));
		assertFalse(resultado.contains(muestra3)); // ezpeleta esta a mas de 3 kms
	}
	
	
	
	

}
