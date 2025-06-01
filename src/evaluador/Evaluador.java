package evaluador;

import java.util.Set;

import opinion.Criterio;
import opinion.Opinion;

public interface Evaluador {
	public Criterio resultadoActual(Set<Opinion> opiniones);

	public Criterio resultadoActualPorExpertos(Set<Opinion> opiniones);

	public Criterio resultadoActualPorBasico(Set<Opinion> opiniones);

	public Criterio opcionGanadora(Set<Opinion> opiniones);

	public boolean hayAcuerdoEntreExpertos(Set<Opinion> opiniones, Opinion unaOpinion);

	public boolean hayAcuerdoEntreExpertosTest(Set<Opinion> opinionesExpertas);
}