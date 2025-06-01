package usuario;

import Opinion.Opinion;
import muestra.Muestra;

public class Experto implements Rango {

	@Override
	public void establecerOpinionParaMuestra(Opinion unaOpinion, Muestra muestra) {
		muestra.recibirOpinionExperta(unaOpinion);
	}

}
