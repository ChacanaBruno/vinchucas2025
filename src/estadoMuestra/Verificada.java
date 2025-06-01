package estadoMuestra;

import muestra.Muestra;
import opinion.Criterio;
import opinion.Opinion;

public class Verificada implements EstadoMuestra {

	// posibles exepciones, tal vez estado muestra podria ser clase abstracta para
	// reutilizar codigo ahi

	@Override
	public void recibirOpinionExperta(Opinion unaOpinion, Muestra unaMuestra) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recibirOpinionBasica(Opinion unaOpinion, Muestra unaMuestra) {
		// TODO Auto-generated method stub

	}

	@Override
	public Criterio resultadoActualPara(Muestra muestra) {
		// TODO Auto-generated method stub
		return null;
	}

}