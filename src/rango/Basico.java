package rango;

import opinion.Opinion;
import muestra.Muestra;

public class Basico implements Rango {

	public Basico() {

	}

	@Override
	public void establecerOpinionParaMuestra(Opinion unaOpinion, Muestra unaMuestra) {
		unaMuestra.recibirOpinionBasica(unaOpinion);

	}

}