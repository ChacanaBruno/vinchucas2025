package usuario;

import muestra.Muestra;
import opinion.Opinion;

public interface Participante {

	public void enviarMuestra(Muestra unaMuestra);

	public void opinarMuestra(Muestra unaMuestra, Opinion unaOpinion);
}