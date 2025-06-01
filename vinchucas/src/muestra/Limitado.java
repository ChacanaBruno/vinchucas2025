package muestra;

import Opinion.Opinion;

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



}
