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
		double longitud = -34.70;
		double latitud = -58.27;
		
		// Exercise
		Ubicacion ubicacion = new Ubicacion(longitud,latitud);
		
		// Verify
		assertEquals(longitud, ubicacion.getLongitud());
		assertEquals(latitud, ubicacion.getLatitud());
	}
	
	@Test
	void test_UnaUbicacionPuedeCalcularLaDistanciaEnKilometrosAOtraUbicacion() {
		// Setup
		Ubicacion ubicacion1 = new Ubicacion(25,30);
		Ubicacion ubicacion2 = new Ubicacion(27,40);
		
		// Exercise
		double distancia = ubicacion1.distanciaALaUbicacion(ubicacion2);
		
		// Verify
		assertEquals(distancia, 10.20); // 10.2 kms
	}
	
	@Test
	void test_UnaUbicacionPuedeDecirAPartirDeUnaListaDeUbicacionesCualesEstanAMenosDeXKilometros() {
		// Setup
		Ubicacion origen = new Ubicacion(15,15);
		Ubicacion ubicacion1 = new Ubicacion(10,20); // 7.07 kms
		Ubicacion ubicacion2 = new Ubicacion(5,15); // 10 kms
		Ubicacion ubicacion3 = new Ubicacion(1,8); // 15.65 kms
		List<Ubicacion> ubicaciones = Arrays.asList(ubicacion1, ubicacion2, ubicacion3);
		
		// Exercise
		List<Ubicacion> resultado = origen.ubicacionesAMenosDeXKilometros(10, ubicaciones);
		
		// Verify
		assertTrue(resultado.contains(ubicacion1));
		assertTrue(resultado.contains(ubicacion2));
		assertFalse(resultado.contains(ubicacion3)); 
	}

}
