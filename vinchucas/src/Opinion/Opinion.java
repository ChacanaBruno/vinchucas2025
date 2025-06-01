package Opinion;

import java.time.LocalDate;

import usuario.Participante;
import usuario.Usuario;

public class Opinion {
	private Participante opinador;
	private Criterio opcionSeleccionada;
	private LocalDate fecha; // esto debe mockearse con un set

	public Opinion(Participante opinador, Criterio opciones) {
		this.opinador = opinador;
		this.opcionSeleccionada = opciones;
		this.fecha = LocalDate.now();
	}

	public Participante getOpinador() {
		return opinador;
	}

	public void setOpinador(Participante opinador) {
		this.opinador = opinador;
	}

	public Criterio getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	public void setOpciones(Criterio opciones) {
		this.opcionSeleccionada = opciones;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setOpcionSeleccionada(Criterio opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}

}
