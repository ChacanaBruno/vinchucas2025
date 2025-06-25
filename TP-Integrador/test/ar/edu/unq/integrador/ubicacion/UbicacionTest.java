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
	
	
	@Test
	void test_UbicacionFiltraListaVaciaYDevuelveListaVacia() {
		//Setup
	    Ubicacion origen = new Ubicacion(-34.70637371362339, -58.27837794516907);
	    List<Ubicacion> ubicaciones = List.of();
        
	    //Exercise
	    List<Ubicacion> resultado = origen.ubicacionesAMenosDeXKilometros(5, ubicaciones);

	    //Verify
	    assertTrue(resultado.isEmpty());
	}
	
	@Test
	void test_UbicacionFiltraMuestrasConListaVacia() {
	    Ubicacion origen = new Ubicacion(-34.70637371362339, -58.27837794516907);
	    List<Muestra> muestras = List.of();

	    List<Muestra> resultado = origen.muestrasAMenosDeXKilometros(5, muestras);

	    assertTrue(resultado.isEmpty());
	}

	@Test
	void test_TodasLasUbicacionesEstanDentroDelRango() {
	    Ubicacion origen = new Ubicacion(-34.706, -58.278);

	    Ubicacion ubicacion1 = new Ubicacion(-34.7061, -58.2781);
	    Ubicacion ubicacion2 = new Ubicacion(-34.7062, -58.2782);
	    Ubicacion ubicacion3 = new Ubicacion(-34.7063, -58.2783);
	    Ubicacion ubicacion4 = new Ubicacion(-34.7064, -58.2784);
	    Ubicacion ubicacion5 = new Ubicacion(-34.7065, -58.2785);
	    Ubicacion ubicacion6 = new Ubicacion(-34.7059, -58.2779);
	    Ubicacion ubicacion7 = new Ubicacion(-34.7058, -58.2778);
	    Ubicacion ubicacion8 = new Ubicacion(-34.7057, -58.2777);
	    Ubicacion ubicacion9 = new Ubicacion(-34.7056, -58.2776);
	    Ubicacion ubicacion10 = new Ubicacion(-34.7055, -58.2775);
	    Ubicacion ubicacion11 = new Ubicacion(-34.7066, -58.2786);
	    Ubicacion ubicacion12 = new Ubicacion(-34.7067, -58.2787);
	    Ubicacion ubicacion13 = new Ubicacion(-34.7054, -58.2774);
	    Ubicacion ubicacion14 = new Ubicacion(-34.7053, -58.2773);
	    Ubicacion ubicacion15 = new Ubicacion(-34.7052, -58.2772);

	    List<Ubicacion> ubicaciones = List.of(
	        ubicacion1, ubicacion2, ubicacion3, ubicacion4, ubicacion5,
	        ubicacion6, ubicacion7, ubicacion8, ubicacion9, ubicacion10,
	        ubicacion11, ubicacion12, ubicacion13, ubicacion14, ubicacion15
	    );

	    List<Ubicacion> resultado = origen.ubicacionesAMenosDeXKilometros(1.0, ubicaciones);

	    assertTrue(resultado.containsAll(ubicaciones));
	}
	
	@Test
	void test_TodasLasMuestrasEstanDentroDelRango() {
	    // Setup
	    Ubicacion origen = new Ubicacion(-34.706, -58.278);
	    Usuario usuario = new Usuario("Arnold");

	    Formulario f1 = new Formulario("Foto1", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7061, -58.2781), usuario);
	    Formulario f2 = new Formulario("Foto2", Concepto.VINCHUCA_INFESTANS, new Ubicacion(-34.7062, -58.2782), usuario);
	    Formulario f3 = new Formulario("Foto3", Concepto.CHINCHE_FOLIADA, new Ubicacion(-34.7063, -58.2783), usuario);
	    Formulario f4 = new Formulario("Foto4", Concepto.IMAGEN_POCO_CLARA, new Ubicacion(-34.7064, -58.2784), usuario);
	    Formulario f5 = new Formulario("Foto5", Concepto.NO_DEFINIDO, new Ubicacion(-34.7065, -58.2785), usuario);
	    Formulario f6 = new Formulario("Foto6", Concepto.VINCHUCA_GUASAYANA, new Ubicacion(-34.7059, -58.2779), usuario);
	    Formulario f7 = new Formulario("Foto7", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7058, -58.2778), usuario);
	    Formulario f8 = new Formulario("Foto8", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7057, -58.2777), usuario);
	    Formulario f9 = new Formulario("Foto9", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7056, -58.2776), usuario);
	    Formulario f10 = new Formulario("Foto10", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7055, -58.2775), usuario);
	    Formulario f11 = new Formulario("Foto11", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7066, -58.2786), usuario);
	    Formulario f12 = new Formulario("Foto12", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7067, -58.2787), usuario);
	    Formulario f13 = new Formulario("Foto13", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7054, -58.2774), usuario);
	    Formulario f14 = new Formulario("Foto14", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7053, -58.2773), usuario);
	    Formulario f15 = new Formulario("Foto15", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7052, -58.2772), usuario);

	    Muestra m1 = usuario.crearMuestra(f1);
	    Muestra m2 = usuario.crearMuestra(f2);
	    Muestra m3 = usuario.crearMuestra(f3);
	    Muestra m4 = usuario.crearMuestra(f4);
	    Muestra m5 = usuario.crearMuestra(f5);
	    Muestra m6 = usuario.crearMuestra(f6);
	    Muestra m7 = usuario.crearMuestra(f7);
	    Muestra m8 = usuario.crearMuestra(f8);
	    Muestra m9 = usuario.crearMuestra(f9);
	    Muestra m10 = usuario.crearMuestra(f10);
	    Muestra m11 = usuario.crearMuestra(f11);
	    Muestra m12 = usuario.crearMuestra(f12);
	    Muestra m13 = usuario.crearMuestra(f13);
	    Muestra m14 = usuario.crearMuestra(f14);
	    Muestra m15 = usuario.crearMuestra(f15);

	    List<Muestra> muestras = List.of(
	        m1, m2, m3, m4, m5,
	        m6, m7, m8, m9, m10,
	        m11, m12, m13, m14, m15
	    );

	    // Exercise
	    List<Muestra> resultado = origen.muestrasAMenosDeXKilometros(1.0, muestras);

	    // Verify
	    assertTrue(resultado.containsAll(muestras));
	}
}
