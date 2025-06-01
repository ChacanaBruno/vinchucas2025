package estadoMuestra;

import muestra.Muestra;
import opinion.Criterio;
import opinion.Opinion;

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

	@Override
	public Criterio resultadoActualPara(Muestra muestra) {
		// TODO Auto-generated method stub
		return muestra.opcionGanadora(muestra.getOpinionesBasicas()); // rompe encapsulamiento?
		// se gana flexibilidad y no dependo de ifs
	}

}
