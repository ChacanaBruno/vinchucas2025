package estadoMuestra;

import muestra.Muestra;
import opinion.Criterio;
import opinion.Opinion;

public class Limitado implements EstadoMuestra {

	public void recibirOpinionExperta(Opinion unaOpinion, Muestra unaMuestra) {
		unaMuestra.agregarOpinionExperta(unaOpinion);
		if (unaMuestra.hayAcuerdoEntreExpertos(unaOpinion)) {
			unaMuestra.setEstadoMuestra(new Verificada());
		}

	}

	@Override
	public void recibirOpinionBasica(Opinion unaOpinion, Muestra unaMuestra) {
		// "Tu rango no te permite opinar"

	}

	@Override
	public Criterio resultadoActualPara(Muestra muestra) {
		return muestra.opcionGanadora(muestra.getOpinionesExpertas()); // rompe encapsulamiento?
		// se gana flexibilidad y no dependo de ifs
	}

}
