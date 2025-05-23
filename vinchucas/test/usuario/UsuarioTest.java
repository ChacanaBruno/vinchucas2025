package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Opinion.Opinion;
import muestra.Muestra;

class UsuarioTest {

	private Usuario usuarioBasico;
	private Muestra muestraMock;
	private Opinion opinionMock;

	@BeforeEach
	void setUp() {
		usuarioBasico = new Usuario();
		muestraMock = mock(Muestra.class);
		opinionMock = mock(Opinion.class);
	}

	@Test
	void cuandoUnUsuarioConRangoBasicoOpinaSobreUnaMuestraAumentaEnUnoSusMuestrasOpinadas() {

	}

}
