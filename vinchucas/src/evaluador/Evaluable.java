package evaluador;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Opinion.Criterio;
import Opinion.Opinion;

public interface Evaluable {
	public Criterio resultadoActual(Set<Opinion> opiniones);

	public Criterio resultadoActualPorExpertos(Set<Opinion> opiniones);

	public Criterio resultadoActualPorBasico(Set<Opinion> opiniones);

	public Criterio opcionGanadora(Set<Opinion> opiniones);
	
	public boolean hayAcuerdoEntreExpertos(Set<Opinion> opiniones, Opinion unaOpinion);
}
