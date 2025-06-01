package evaluador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import opinion.Criterio;
import opinion.Opinion;
import usuario.Participante;
import usuario.Usuario;

public class OpinionEvaluadorTest {

	private final OpinionEvaluador evaluador = new OpinionEvaluador();

	@Test
	void retornaCriterioGanadorSinEmpate() {
		Set<Opinion> opiniones = new HashSet<>();
		opiniones.add(opinionMock(Criterio.Vinchuca_Infestans));
		opiniones.add(opinionMock(Criterio.Vinchuca_Infestans));
		opiniones.add(opinionMock(Criterio.Vinchuca_Sordida));

		Criterio resultado = evaluador.opcionGanadora(opiniones);
		assertEquals(Criterio.Vinchuca_Infestans, resultado);
	}

	@Test
	void retornaNoDefinidoEnEmpate() {
		Set<Opinion> opiniones = new HashSet<>();
		opiniones.add(opinionMock(Criterio.Chinche_Foliada));
		opiniones.add(opinionMock(Criterio.Phtia_Chinche));

		Criterio resultado = evaluador.opcionGanadora(opiniones);
		assertEquals(Criterio.NO_DEFINIDO, resultado);
	}

	@Test
	void retornaNoDefinidoCuandoSetEstaVacio() {
		Set<Opinion> opiniones = new HashSet<>();

		Criterio resultado = evaluador.opcionGanadora(opiniones);
		assertEquals(Criterio.NO_DEFINIDO, resultado);
	}

	@Test
	void retornaCriterioCuandoTodosVotanIgual() {
		Set<Opinion> opiniones = new HashSet<>();
		opiniones.add(opinionMock(Criterio.Ninguna));
		opiniones.add(opinionMock(Criterio.Ninguna));
		opiniones.add(opinionMock(Criterio.Ninguna));

		Criterio resultado = evaluador.opcionGanadora(opiniones);
		assertEquals(Criterio.Ninguna, resultado);
	}

	// Utilidad para crear opiniones con un criterio fijo
	private Opinion opinionMock(Criterio criterio) {
		Participante dummy = new Usuario(); // podés usar un mock si usás Mockito
		Opinion opinion = new Opinion(dummy, criterio);
		opinion.setFecha(LocalDate.of(2023, 1, 1)); // mockeo de fecha si tu clase lo permite
		return opinion;
	}
}