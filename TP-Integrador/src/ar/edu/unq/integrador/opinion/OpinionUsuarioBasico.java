package ar.edu.unq.integrador.opinion;

import java.time.LocalDate;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.usuario.Usuario;

public class OpinionUsuarioBasico extends Opinion {
	// Atributos
	
	// Constructor
	public OpinionUsuarioBasico(LocalDate f, Usuario a, Concepto c) {
		super(f,a,c);
	}
	
	// Metodos
	@Override
	public boolean esOpinionExperta() {
		return false;
	}

}
