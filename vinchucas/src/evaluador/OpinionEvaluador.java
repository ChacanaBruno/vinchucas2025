package evaluador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Opinion.Criterio;
import Opinion.Opinion;

public class OpinionEvaluador implements Evaluable {

	public OpinionEvaluador() {
	}

	@Override
	public Criterio resultadoActual(Set<Opinion> opiniones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criterio resultadoActualPorExpertos(Set<Opinion> opiniones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criterio resultadoActualPorBasico(Set<Opinion> opiniones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criterio opcionGanadora(Set<Opinion> opiniones) {
	    if (opiniones.isEmpty()) {
	        return Criterio.NO_DEFINIDO;
	    }

	    Map<Criterio, Integer> conteo = new HashMap<>();

	    // Contar votos
	    for (Opinion opinion : opiniones) {
	        Criterio criterio = opinion.getOpcionSeleccionada();
	        conteo.put(criterio, conteo.getOrDefault(criterio, 0) + 1);
	    }

	    // Buscar el máximo
	    int maxVotos = 0;
	    for (int cantidad : conteo.values()) {
	        if (cantidad > maxVotos) {
	            maxVotos = cantidad;
	        }
	    }

	    // Verificar cuántos criterios tienen el máximo
	    Criterio candidato = null;
	    int cantidadMaximos = 0;

	    for (Map.Entry<Criterio, Integer> entry : conteo.entrySet()) {
	        if (entry.getValue() == maxVotos) {
	            cantidadMaximos++;
	            candidato = entry.getKey();
	        }
	    }

	    return (cantidadMaximos == 1) ? candidato : Criterio.NO_DEFINIDO;
	}

	@Override
	public boolean hayAcuerdoEntreExpertos(Set<Opinion> opiniones, Opinion unaOpinion) {
		// TODO Auto-generated method stub
		return false;
	}

}
