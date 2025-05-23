package Opinion;

import java.time.LocalDate;

import usuario.Participante;
import usuario.Usuario;

public class Opinion {
	private Participante opinador;
	private Opciones opcionSeleccionada;
	private boolean esDeExperto;
	private LocalDate fecha; // esto debe mockearse con un set

	public Opinion(Participante opinador, Opciones opciones) {
		this.opinador = opinador;
		this.opcionSeleccionada = opciones;
		this.esDeExperto = false; // default
		this.fecha = LocalDate.now();
	}

	public Participante getOpinador() {
		return opinador;
	}

	public void setOpinador(Participante opinador) {
		this.opinador = opinador;
	}

	public Opciones getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	public void setOpciones(Opciones opciones) {
		this.opcionSeleccionada = opciones;
	}

	public boolean isEsDeExperto() {
		return esDeExperto;
	}

	public void setEsDeExperto(boolean esDeExperto) {
		this.esDeExperto = esDeExperto;
	}

}
