package ar.edu.unq.integrador.formulario;

import java.time.LocalDate;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.usuario.Usuario;

public class Formulario {
	// Atributos
	String foto; // el nombre de un archivo .jpg
	Concepto concepto;
	Ubicacion ubicacion;
	Usuario autor;
	LocalDate fechaCreacion;
	
	// Constructor
	public Formulario (String f, Concepto c, Ubicacion u, Usuario a) {
		this.setFoto(f);
		this.setConcepto(c);
		this.setUbicacion(u);
		this.setAutor(a);
		this.setFechaCreacion(LocalDate.now());
	}
	
	// Accessors
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getAutor() {
		return this.autor;
	}

	public String getFoto() {
		return this.foto;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}
	
	public Concepto getConcepto() {
		return this.concepto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	
	
	
}
