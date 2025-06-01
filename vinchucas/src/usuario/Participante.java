package usuario;

import Opinion.Criterio;

import muestra.Muestra;

public interface Participante {
	
	public void enviarMuestra(Muestra unaMuestra);

	public void opinarMuestra(Muestra muestra, Criterio unaOpcion);
}
