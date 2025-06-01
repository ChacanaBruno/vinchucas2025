package muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Opinion.Criterio;
import usuario.Participante;
import usuario.Usuario;

class MuestraTest {

	private Muestra muestraSUT;
	private Foto fotoMock;
	private Ubicacion ubicacionMuestraMock;
	private Criterio especieMock;
	private Participante usuarioMock;

	@BeforeEach
	void setUp() {

		fotoMock = mock(Foto.class);

		especieMock = mock(Criterio.class);

		usuarioMock = mock(Participante.class);

		ubicacionMuestraMock = mock(Ubicacion.class);

		muestraSUT = new Muestra(especieMock, fotoMock, ubicacionMuestraMock, usuarioMock);

	}

	@Test
	void unaMuestraIndicaSuEspecie() {
		muestraSUT.setEspecie(especieMock);
		assertTrue(muestraSUT.getEspecie().equals(especieMock));
	}

	@Test
	void unaMuestraIndicaSuFoto() {
		muestraSUT.setFoto(fotoMock);
		assertTrue(muestraSUT.getFoto().equals(fotoMock));
	}

	@Test
	void unaMuestraIndicaSuUsuario() {
		muestraSUT.setUsuario(usuarioMock);
		assertTrue(muestraSUT.getUsuario().equals(usuarioMock));
	}
}
