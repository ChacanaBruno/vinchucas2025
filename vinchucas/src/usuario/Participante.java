package usuario;

import Opinion.Opciones;

import muestra.Muestra;

public interface Participante {
	
	public void enviarMuestra(Muestra unaMuestra);

	public void opinarMuestra(Muestra muestra, Opciones unaOpcion);
}
