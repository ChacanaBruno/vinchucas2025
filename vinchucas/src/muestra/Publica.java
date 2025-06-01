package muestra;

import Opinion.Opinion;

public class Publica implements EstadoMuestra {

	@Override
	public void recibirOpinionExperta(Opinion unaOpinion, Muestra unaMuestra) {

		unaMuestra.setEstadoMuestra(new Limitado());
		unaMuestra.recibirOpinionExperta(unaOpinion);
	}

	@Override
	public void recibirOpinionBasica(Opinion unaOpinion, Muestra muestra) {
		muestra.agregarOpinionBasica(unaOpinion);

	}

}
