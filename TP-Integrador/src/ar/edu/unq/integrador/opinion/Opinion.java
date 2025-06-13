package ar.edu.unq.integrador.opinion;

import java.time.LocalDate;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.usuario.Usuario;

public abstract class Opinion {
	// Atributos
	private LocalDate fecha;
	private Usuario autor;
	private Concepto concepto;
	private Muestra muestraOpinada;
	
	// Constructor
	public Opinion(LocalDate f, Usuario a, Concepto c, Muestra m) {
		this.setFecha(f);
		this.setAutor(a);
		this.setConcepto(c);
		this.setMuestraOpinada(m);
	}
	
	// Accessors
	public Usuario getAutor() {
		return this.autor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	public Muestra getMuestraOpinada() {
		return muestraOpinada;
	}

	public void setMuestraOpinada(Muestra muestraOpinada) {
		this.muestraOpinada = muestraOpinada;
	}

	// Metodos
	public abstract boolean esOpinionExperta();
	
	
	
}
