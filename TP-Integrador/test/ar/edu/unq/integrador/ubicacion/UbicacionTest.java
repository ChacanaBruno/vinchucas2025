package ar.edu.unq.integrador.ubicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	
	
	

}
