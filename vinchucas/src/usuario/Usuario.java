package usuario;

import java.util.HashSet;
import java.util.Set;

import Opinion.Criterio;
import Opinion.Opinion;
import muestra.Muestra;

public class Usuario implements Participante {
	private Set<Muestra> muestrasRealizadas;
	private Set<Muestra> muestrasOpinadas;
	private Rango rangoActual;

	public Usuario() { // usuario nuevo
		super();
		this.muestrasRealizadas = new HashSet<>();
		;
		this.muestrasOpinadas = new HashSet<>();
		;
		this.rangoActual = new Basico();
	}

	public Usuario(Especialista unEspecialista) {
		this.muestrasRealizadas = new HashSet<>();
		;
		this.muestrasOpinadas = new HashSet<>();
		;
		this.rangoActual = unEspecialista;
	}

	@Override
	public void enviarMuestra(Muestra unaMuestra) {
		muestrasRealizadas.add(unaMuestra); // enviar a zona que pertence la muestra. GestorDeZona

	}

	@Override
	public void opinarMuestra(Muestra muestra, Criterio opcion) {
		// si cambia el constructor de opinion solo cambio esta linea
		Opinion opinion = new Opinion(this, opcion);

		rangoActual.establecerOpinionParaMuestra(opinion, muestra);

	}

	protected void cambiarRango(Rango unRango) {
		this.setRangoActual(unRango);
	}

	public Rango getRangoActual() {
		return rangoActual;
	}

	protected void setRangoActual(Rango rangoActual) {
		this.rangoActual = rangoActual;
	}

}
