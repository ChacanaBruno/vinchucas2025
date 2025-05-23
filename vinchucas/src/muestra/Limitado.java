package muestra;

import Opinion.Opinion;

public class Limitado implements EstadoMuestra {

	@Override
	public void recibirOpinionPara(Opinion unaOpinion, Muestra unaMuestra) {

		unaMuestra.registrarOpinion(unaOpinion);

		if (unaMuestra.hayAcuerdoEntreExpertos(unaOpinion)) {
			unaMuestra.setEstadoMuestra(new Verificada());
		}

	}

}
