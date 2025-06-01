package rango;

import muestra.Muestra;
import opinion.Opinion;

public class Experto implements Rango {

	@Override
	public void establecerOpinionParaMuestra(Opinion unaOpinion, Muestra muestra) {
		muestra.recibirOpinionExperta(unaOpinion);
	}

}
