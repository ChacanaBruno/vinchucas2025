package evaluador;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Opinion.Opciones;
import Opinion.Opinion;

public interface Evaluable {
	public Opciones resultadoActual(Set<Opinion> opiniones);

	public Opciones resultadoActualPorExpertos(Set<Opinion> opiniones);

	public Opciones resultadoActualPorBasico(Set<Opinion> opiniones);

	public Opciones opcionGanadora(List<Opinion> opiniones);
	
	public boolean hayAcuerdoEntreExpertos(Set<Opinion> opiniones, Opinion unaOpinion);
}
