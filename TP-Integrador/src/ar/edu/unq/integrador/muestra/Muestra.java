package ar.edu.unq.integrador.muestra;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unq.integrador.estadoMuestra.EstadoMuestra;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.usuario.Usuario;

public class Muestra {
	// Atributos
	private LocalDate fecha;
	private Formulario formulario;
	private EstadoMuestra estadoMuestra;
	private List<Opinion> opiniones;
	
	// Constructor
	public Muestra(LocalDate fecha, Formulario formulario, EstadoMuestra estadoMuestra, List<Opinion> opiniones) {
		this.setFecha(fecha);
		this.setFormulario(formulario);
		this.setEstadoMuestra(estadoMuestra);
		this.setOpiniones(opiniones);
	}
	
	// Accessors
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public void setFecha(LocalDate f) {
		this.fecha = f;
	}
	
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

	// Metodos
	public Usuario getAutor() {
		return this.getFormulario().getAutor();
	}

	public String getFoto() {
		return this.getFormulario().getFoto();		
	}

	public Ubicacion getUbicacion() {
		return this.getFormulario().getUbicacion();
		
	}

	public void agregarOpinion(Opinion op) {
		Usuario autor = op.getAutor();
		List<Opinion> opiniones = this.getOpiniones();
		
        if (!elUsuarioYaOpino(autor)){
            opiniones.add(op);
        } else {
        	throw new RuntimeException("El usuario ya registra una opinion en la muestra");
        }
		
	}

	private boolean elUsuarioYaOpino(Usuario usuario) {
		List<Opinion> opiniones = this.getOpiniones();
		return opiniones.stream().anyMatch(o -> o.getAutor().equals(usuario));
	}	
}
