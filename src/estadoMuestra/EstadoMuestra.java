package estadoMuestra;

import muestra.Muestra;
import opinion.Criterio;
import opinion.Opinion;

public interface EstadoMuestra {
	// public void recibirOpinionPara(Opinion unaOpinion, Muestra unaMuestra);

	public void recibirOpinionExperta(Opinion unaOpinion, Muestra unaMuestra);

	public void recibirOpinionBasica(Opinion unaOpinion, Muestra unaMuestra);

	public Criterio resultadoActualPara(Muestra muestra);

}