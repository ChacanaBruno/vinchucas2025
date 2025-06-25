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
	
	@Test
	void test_TodasLasMuestrasEstanFueraDelRango() {
	    // Setup
	    Ubicacion origen = new Ubicacion(-34.706, -58.278);
	    Usuario usuario = new Usuario("Dorean");

	    Formulario f1 = new Formulario("Foto1", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7200, -58.2900), usuario);
	    Muestra m1 = usuario.crearMuestra(f1);

	    Formulario f2 = new Formulario("Foto2", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7210, -58.2910), usuario);
	    Muestra m2 = usuario.crearMuestra(f2);

	    Formulario f3 = new Formulario("Foto3", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7220, -58.2920), usuario);
	    Muestra m3 = usuario.crearMuestra(f3);

	    Formulario f4 = new Formulario("Foto4", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7230, -58.2930), usuario);
	    Muestra m4 = usuario.crearMuestra(f4);

	    Formulario f5 = new Formulario("Foto5", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7240, -58.2940), usuario);
	    Muestra m5 = usuario.crearMuestra(f5);

	    Formulario f6 = new Formulario("Foto6", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7250, -58.2950), usuario);
	    Muestra m6 = usuario.crearMuestra(f6);

	    Formulario f7 = new Formulario("Foto7", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7260, -58.2960), usuario);
	    Muestra m7 = usuario.crearMuestra(f7);

	    Formulario f8 = new Formulario("Foto8", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7270, -58.2970), usuario);
	    Muestra m8 = usuario.crearMuestra(f8);

	    Formulario f9 = new Formulario("Foto9", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7280, -58.2980), usuario);
	    Muestra m9 = usuario.crearMuestra(f9);

	    Formulario f10 = new Formulario("Foto10", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7290, -58.2990), usuario);
	    Muestra m10 = usuario.crearMuestra(f10);

	    Formulario f11 = new Formulario("Foto11", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7300, -58.3000), usuario);
	    Muestra m11 = usuario.crearMuestra(f11);

	    Formulario f12 = new Formulario("Foto12", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7310, -58.3010), usuario);
	    Muestra m12 = usuario.crearMuestra(f12);

	    Formulario f13 = new Formulario("Foto13", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7320, -58.3020), usuario);
	    Muestra m13 = usuario.crearMuestra(f13);

	    Formulario f14 = new Formulario("Foto14", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7330, -58.3030), usuario);
	    Muestra m14 = usuario.crearMuestra(f14);

	    Formulario f15 = new Formulario("Foto15", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7340, -58.3040), usuario);
	    Muestra m15 = usuario.crearMuestra(f15);

	    Formulario f16 = new Formulario("Foto16", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7350, -58.3050), usuario);
	    Muestra m16 = usuario.crearMuestra(f16);

	    Formulario f17 = new Formulario("Foto17", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7360, -58.3060), usuario);
	    Muestra m17 = usuario.crearMuestra(f17);

	    Formulario f18 = new Formulario("Foto18", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7370, -58.3070), usuario);
	    Muestra m18 = usuario.crearMuestra(f18);

	    Formulario f19 = new Formulario("Foto19", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7380, -58.3080), usuario);
	    Muestra m19 = usuario.crearMuestra(f19);

	    Formulario f20 = new Formulario("Foto20", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7390, -58.3090), usuario);
	    Muestra m20 = usuario.crearMuestra(f20);

	    Formulario f21 = new Formulario("Foto21", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7400, -58.3100), usuario);
	    Muestra m21 = usuario.crearMuestra(f21);

	    Formulario f22 = new Formulario("Foto22", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7410, -58.3110), usuario);
	    Muestra m22 = usuario.crearMuestra(f22);

	    Formulario f23 = new Formulario("Foto23", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7420, -58.3120), usuario);
	    Muestra m23 = usuario.crearMuestra(f23);

	    Formulario f24 = new Formulario("Foto24", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7430, -58.3130), usuario);
	    Muestra m24 = usuario.crearMuestra(f24);

	    Formulario f25 = new Formulario("Foto25", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7440, -58.3140), usuario);
	    Muestra m25 = usuario.crearMuestra(f25);

	    Formulario f26 = new Formulario("Foto26", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7450, -58.3150), usuario);
	    Muestra m26 = usuario.crearMuestra(f26);

	    Formulario f27 = new Formulario("Foto27", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7460, -58.3160), usuario);
	    Muestra m27 = usuario.crearMuestra(f27);

	    Formulario f28 = new Formulario("Foto28", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7470, -58.3170), usuario);
	    Muestra m28 = usuario.crearMuestra(f28);

	    Formulario f29 = new Formulario("Foto29", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7480, -58.3180), usuario);
	    Muestra m29 = usuario.crearMuestra(f29);

	    Formulario f30 = new Formulario("Foto30", Concepto.VINCHUCA_SORDIDA, new Ubicacion(-34.7490, -58.3190), usuario);
	    Muestra m30 = usuario.crearMuestra(f30);

	    List<Muestra> muestras = Arrays.asList(
	        m1, m2, m3, m4, m5, m6, m7, m8, m9, m10,
	        m11, m12, m13, m14, m15, m16, m17, m18, m19, m20,
	        m21, m22, m23, m24, m25, m26, m27, m28, m29, m30
	    );

	    // Exercise
	    List<Muestra> resultado = origen.muestrasAMenosDeXKilometros(1.0, muestras);

	    // Verify
	    assertTrue(resultado.isEmpty());
	}
	
	@Test
	void test_TodasLas50UbicacionesEstanFueraDelRango() {
	    Ubicacion origen = new Ubicacion(-34.706, -58.278);

	    Ubicacion ubicacion1 = new Ubicacion(-34.716, -58.288);
	    Ubicacion ubicacion2 = new Ubicacion(-34.717, -58.289);
	    Ubicacion ubicacion3 = new Ubicacion(-34.718, -58.290);
	    Ubicacion ubicacion4 = new Ubicacion(-34.719, -58.291);
	    Ubicacion ubicacion5 = new Ubicacion(-34.720, -58.292);
	    Ubicacion ubicacion6 = new Ubicacion(-34.721, -58.293);
	    Ubicacion ubicacion7 = new Ubicacion(-34.722, -58.294);
	    Ubicacion ubicacion8 = new Ubicacion(-34.723, -58.295);
	    Ubicacion ubicacion9 = new Ubicacion(-34.724, -58.296);
	    Ubicacion ubicacion10 = new Ubicacion(-34.725, -58.297);
	    Ubicacion ubicacion11 = new Ubicacion(-34.726, -58.298);
	    Ubicacion ubicacion12 = new Ubicacion(-34.727, -58.299);
	    Ubicacion ubicacion13 = new Ubicacion(-34.728, -58.300);
	    Ubicacion ubicacion14 = new Ubicacion(-34.729, -58.301);
	    Ubicacion ubicacion15 = new Ubicacion(-34.730, -58.302);
	    Ubicacion ubicacion16 = new Ubicacion(-34.731, -58.303);
	    Ubicacion ubicacion17 = new Ubicacion(-34.732, -58.304);
	    Ubicacion ubicacion18 = new Ubicacion(-34.733, -58.305);
	    Ubicacion ubicacion19 = new Ubicacion(-34.734, -58.306);
	    Ubicacion ubicacion20 = new Ubicacion(-34.735, -58.307);
	    Ubicacion ubicacion21 = new Ubicacion(-34.736, -58.308);
	    Ubicacion ubicacion22 = new Ubicacion(-34.737, -58.309);
	    Ubicacion ubicacion23 = new Ubicacion(-34.738, -58.310);
	    Ubicacion ubicacion24 = new Ubicacion(-34.739, -58.311);
	    Ubicacion ubicacion25 = new Ubicacion(-34.740, -58.312);
	    Ubicacion ubicacion26 = new Ubicacion(-34.741, -58.313);
	    Ubicacion ubicacion27 = new Ubicacion(-34.742, -58.314);
	    Ubicacion ubicacion28 = new Ubicacion(-34.743, -58.315);
	    Ubicacion ubicacion29 = new Ubicacion(-34.744, -58.316);
	    Ubicacion ubicacion30 = new Ubicacion(-34.745, -58.317);
	    Ubicacion ubicacion31 = new Ubicacion(-34.746, -58.318);
	    Ubicacion ubicacion32 = new Ubicacion(-34.747, -58.319);
	    Ubicacion ubicacion33 = new Ubicacion(-34.748, -58.320);
	    Ubicacion ubicacion34 = new Ubicacion(-34.749, -58.321);
	    Ubicacion ubicacion35 = new Ubicacion(-34.750, -58.322);
	    Ubicacion ubicacion36 = new Ubicacion(-34.751, -58.323);
	    Ubicacion ubicacion37 = new Ubicacion(-34.752, -58.324);
	    Ubicacion ubicacion38 = new Ubicacion(-34.753, -58.325);
	    Ubicacion ubicacion39 = new Ubicacion(-34.754, -58.326);
	    Ubicacion ubicacion40 = new Ubicacion(-34.755, -58.327);
	    Ubicacion ubicacion41 = new Ubicacion(-34.756, -58.328);
	    Ubicacion ubicacion42 = new Ubicacion(-34.757, -58.329);
	    Ubicacion ubicacion43 = new Ubicacion(-34.758, -58.330);
	    Ubicacion ubicacion44 = new Ubicacion(-34.759, -58.331);
	    Ubicacion ubicacion45 = new Ubicacion(-34.760, -58.332);
	    Ubicacion ubicacion46 = new Ubicacion(-34.761, -58.333);
	    Ubicacion ubicacion47 = new Ubicacion(-34.762, -58.334);
	    Ubicacion ubicacion48 = new Ubicacion(-34.763, -58.335);
	    Ubicacion ubicacion49 = new Ubicacion(-34.764, -58.336);
	    Ubicacion ubicacion50 = new Ubicacion(-34.765, -58.337);

	    List<Ubicacion> ubicaciones = List.of(
	        ubicacion1,
	        ubicacion2,
	        ubicacion3,
	        ubicacion4,
	        ubicacion5,
	        ubicacion6,
	        ubicacion7,
	        ubicacion8,
	        ubicacion9,
	        ubicacion10,
	        ubicacion11,
	        ubicacion12,
	        ubicacion13,
	        ubicacion14,
	        ubicacion15,
	        ubicacion16,
	        ubicacion17,
	        ubicacion18,
	        ubicacion19,
	        ubicacion20,
	        ubicacion21,
	        ubicacion22,
	        ubicacion23,
	        ubicacion24,
	        ubicacion25,
	        ubicacion26,
	        ubicacion27,
	        ubicacion28,
	        ubicacion29,
	        ubicacion30,
	        ubicacion31,
	        ubicacion32,
	        ubicacion33,
	        ubicacion34,
	        ubicacion35,
	        ubicacion36,
	        ubicacion37,
	        ubicacion38,
	        ubicacion39,
	        ubicacion40,
	        ubicacion41,
	        ubicacion42,
	        ubicacion43,
	        ubicacion44,
	        ubicacion45,
	        ubicacion46,
	        ubicacion47,
	        ubicacion48,
	        ubicacion49,
	        ubicacion50
	    );

	    List<Ubicacion> resultado = origen.ubicacionesAMenosDeXKilometros(1.0, ubicaciones);

	    assertTrue(resultado.isEmpty());
	}
}
