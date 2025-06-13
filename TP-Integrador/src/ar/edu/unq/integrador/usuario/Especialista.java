package ar.edu.unq.integrador.usuario;

import java.util.ArrayList;

import ar.edu.unq.integrador.categoria.Experto;

public class Especialista extends Usuario {
	// Atributos
	
	// Constructor
	public Especialista(String nombre) {
		super(nombre, new Experto(), new ArrayList<>(), new ArrayList<>()); // El especialista siempre es experto!
	}
	
	// Metodos
	@Override
	public void degradarABasico() {
		throw new RuntimeException("Un Especialista no puede ser degradado a Basico porque tiene validacion externa");
	}
}
