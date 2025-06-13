package ar.edu.unq.integrador.muestra;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.estadoMuestra.EstadoMuestra;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
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
	public LocalDate getFecha() {
		return this.getFormulario().getFechaCreacion();
	}
	
	public void setFecha(LocalDate f) {
		this.getFormulario().setFechaCreacion(f);
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
		
        if (elUsuarioYaOpino(autor)){
        	throw new RuntimeException("El usuario ya registra una opinion en la muestra");
        } else {        	
        	opiniones.add(op);
        }	
	}

	public boolean elUsuarioYaOpino(Usuario usuario) {
		List<Opinion> opiniones = this.getOpiniones();
		return opiniones.stream().anyMatch(o -> o.getAutor().equals(usuario));
	}
	
	public void recibirOpinion(Opinion o) {
		this.getEstadoMuestra().recibirOpinion(o, this);
	}

	public boolean hayConsensoEntreExpertos() {
		List<Opinion> opiniones = this.getOpiniones();
		
	    return opiniones.stream()
	                    .filter(o -> o.esOpinionExperta())
	                    .collect(Collectors.groupingBy(o -> o.getConcepto(), Collectors.counting()))
	                    .values()
	                    .stream()
	                    .anyMatch(count -> count >= 2);
	}
	
	public Concepto resultadoActual() {
		return this.getEstadoMuestra().resultadoActual(this);	
	}
}
