package ar.edu.unq.integrador.muestra;

import java.util.List;

import ar.edu.unq.integrador.estadoMuestra.EstadoMuestra;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.usuario.Usuario;

public class Muestra {
	// Atributos
	private Formulario formulario;
	private EstadoMuestra estadoMuestra;
	private List<Opinion> opiniones;
	
	// Constructor
	public Muestra(Formulario formulario, EstadoMuestra estadoMuestra, List<Opinion> opiniones) {
		this.setFormulario(formulario);
		this.setEstadoMuestra(estadoMuestra);
		this.setOpiniones(opiniones);
	}
	
	// Accessors
	public Formulario getFormulario() {
		return formulario;
	}
	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
	public EstadoMuestra getEstadoMuestra() {
		return estadoMuestra;
	}
	public void setEstadoMuestra(EstadoMuestra estadoMuestra) {
		this.estadoMuestra = estadoMuestra;
	}
	public List<Opinion> getOpiniones() {
		return opiniones;
	}
	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}

	public Usuario getAutor() {
		return this.getFormulario().getAutor();
	}
	
	
}
