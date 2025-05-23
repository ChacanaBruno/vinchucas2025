package muestra;

import Opinion.Opinion;

public class Publica implements EstadoMuestra {

	@Override
	public void recibirOpinionPara(Opinion unaOpinion, Muestra unaMuestra) {
		unaMuestra.registrarOpinion(unaOpinion);

	}

}
