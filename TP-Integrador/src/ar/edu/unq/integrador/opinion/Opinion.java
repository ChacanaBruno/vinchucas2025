package ar.edu.unq.integrador.opinion;

import java.time.LocalDate;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.usuario.Usuario;

public abstract class Opinion {
	// Atributos
	private LocalDate fecha;
	private Usuario autor;
	private Concepto concepto;
	
	// Constructor
	public Opinion(LocalDate f, Usuario a, Concepto c) {
		this.setFecha(f);
		this.setAutor(a);
		this.setConcepto(c);
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

	// Metodos
	public abstract boolean esOpinionExperta();
	
	
	
}
