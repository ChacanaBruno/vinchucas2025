package evaluador;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Opinion.Opciones;
import Opinion.Opinion;

public class OpinionEvaluador implements Evaluable {

	public OpinionEvaluador() {}

	public Opciones resultadoActual(Set<Opinion> opiniones) {
		boolean hayExpertos = opiniones.stream().anyMatch(Opinion::isEsDeExperto);
		return hayExpertos ? resultadoActualPorExpertos(opiniones) : resultadoActualPorBasico(opiniones);
	}

	public Opciones resultadoActualPorExpertos(Set<Opinion> opiniones) {
		List<Opinion> opinionesExpertos = opiniones.stream()
			.filter(Opinion::isEsDeExperto)
			.collect(Collectors.toList());

		return opcionGanadora(opinionesExpertos);
	}

	public Opciones resultadoActualPorBasico(Set<Opinion> opiniones) {
		List<Opinion> opinionesBasicos = opiniones.stream()
			.filter(op -> !op.isEsDeExperto())
			.collect(Collectors.toList());

		return opcionGanadora(opinionesBasicos);
	}

	public Opciones opcionGanadora(List<Opinion> opiniones) {
	    Map<Opciones, Long> votosPorOpcion = opiniones.stream()
	        .collect(Collectors.groupingBy(
	            Opinion::getOpcionSeleccionada,
	            Collectors.counting()
	        ));

	    if (votosPorOpcion.isEmpty()) {
	        return Opciones.NO_DEFINIDO;
	    }

	    long maxVotos = votosPorOpcion.values().stream()
	        .max(Long::compare)
	        .orElse(0L);

	    List<Opciones> opcionesConMaxVotos = votosPorOpcion.entrySet().stream()
	        .filter(entry -> entry.getValue() == maxVotos)
	        .map(Map.Entry::getKey)
	        .collect(Collectors.toList());

	    return (opcionesConMaxVotos.size() == 1)
	        ? opcionesConMaxVotos.get(0)
	        : Opciones.NO_DEFINIDO;
	}

	public boolean hayAcuerdoEntreExpertos(Set<Opinion> opiniones, Opinion unaOpinion) {
		return opiniones.stream()
			.filter(Opinion::isEsDeExperto)
			.collect(Collectors.groupingBy(Opinion::getOpcionSeleccionada, Collectors.counting()))
			.values().stream()
			.anyMatch(count -> count >= 2);
	}
}
