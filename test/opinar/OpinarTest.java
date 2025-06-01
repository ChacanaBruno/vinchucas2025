package opinar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import opinion.Criterio;
import opinion.Opinion;
import rango.Experto;
import ubicacion.Ubicacion;
import usuario.Participante;
import usuario.Usuario;

public class OpinarTest {
	private Usuario usuarioReal;
	private Usuario usuarioExperto;
	private Muestra muestraReal;
	private Participante usuarioMock;
	private Ubicacion ubicacionMock;
	private Opinion opinionMock;
	private Opinion opinionExpertaMock;
	private Muestra muestraRealQuilmes;

	@BeforeEach
	void setUp() {
		usuarioReal = new Usuario();

		usuarioExperto = new Usuario();

		usuarioMock = mock(Usuario.class);

		ubicacionMock = mock(Ubicacion.class);

		muestraReal = new Muestra(ubicacionMock, usuarioMock);

		opinionMock = mock(Opinion.class);

		opinionExpertaMock = mock(Opinion.class);

		muestraRealQuilmes = new Muestra(ubicacionMock, usuarioMock);
	}

	@Test
	void cuandoUsuarioNuevoOpinaEnUnaMuestraPublicaEstaRegistraLaOpinionEnBasicas() {

		when(opinionMock.getOpcionSeleccionada()).thenReturn(Criterio.Imagen_poco_clara);
		when(opinionMock.getOpinador()).thenReturn(usuarioReal);

		usuarioReal.opinarMuestra(muestraReal, opinionMock);

		assertEquals(0, muestraReal.getOpinionesExpertas().size());
		assertEquals(1, muestraReal.getOpinionesBasicas().size());
		assertTrue(muestraReal.getOpinionesExpertas().isEmpty());
	}

	@Test
	void cuandoUsuarioExpertoOpinaEnUnaMuestraPublicaEstaCambiaSuEstadoALimitada() {
		usuarioReal.cambiarRango(new Experto());
		when(opinionMock.getOpcionSeleccionada()).thenReturn(Criterio.Chinche_Foliada);
		when(opinionMock.getOpinador()).thenReturn(usuarioReal);

		usuarioReal.opinarMuestra(muestraReal, opinionMock);

		assertEquals(1, muestraReal.getOpinionesExpertas().size());
		assertEquals(0, muestraReal.getOpinionesBasicas().size());
		assertTrue(muestraReal.getOpinionesBasicas().isEmpty());
	}

	@Test
	void cuando2ExpertosCoincidenLaMuestraSeVerifica() {

		usuarioReal.cambiarRango(new Experto());

		when(opinionMock.getOpcionSeleccionada()).thenReturn(Criterio.Chinche_Foliada);
		when(opinionMock.getOpinador()).thenReturn(usuarioReal);

		usuarioExperto.cambiarRango(new Experto());
		when(opinionExpertaMock.getOpcionSeleccionada()).thenReturn(Criterio.Chinche_Foliada);
		when(opinionExpertaMock.getOpinador()).thenReturn(usuarioExperto);

		usuarioReal.opinarMuestra(muestraReal, opinionMock);

		usuarioExperto.opinarMuestra(muestraReal, opinionExpertaMock);

		assertTrue(muestraReal.hayAcuerdoEntreExpertosTest());
	}
}
