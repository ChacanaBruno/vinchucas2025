package ar.edu.unq.integrador.usuario;

import ar.edu.unq.integrador.opinion.Opinion;

import java.util.List;

import ar.edu.unq.integrador.categoria.Categoria;
import ar.edu.unq.integrador.muestra.Muestra;

public class Usuario {
	// Atributos
	private String nombre;
	private Categoria categoria;
	private List<Muestra> muestrasEnviadas;
	private List<Opinion> opinionesRealizadas; // esto puede ser un (Map Muestra Opinion)?? facilitaria la subida de rango asi...

	// Constructor
	public Usuario (String n, Categoria c, List<Muestra> ms, List<Opinion> os) {
		this.setNombre(n);
		this.setCategoria(c);
		this.setMuestrasEnviadas(ms);
		this.setOpinionesRealizadas(os);
	}
	
	// Accessors
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Muestra> getMuestrasEnviadas() {
		return muestrasEnviadas;
	}
	public void setMuestrasEnviadas(List<Muestra> muestrasEnviadas) {
		this.muestrasEnviadas = muestrasEnviadas;
	}
	public List<Opinion> getOpinionesRealizadas() {
		return opinionesRealizadas;
	}
	public void setOpinionesRealizadas(List<Opinion> opinionesRealizadas) {
		this.opinionesRealizadas = opinionesRealizadas;
	}

	
	
	
	
}
