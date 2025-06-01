package usuario;

import Opinion.Opinion;
import muestra.Muestra;

public class Basico implements Rango {

	public Basico() {

	}

	@Override
	public void establecerOpinionParaMuestra(Opinion unaOpinion, Muestra muestra) {
		muestra.recibirOpinionBasica(unaOpinion);

	}

}
