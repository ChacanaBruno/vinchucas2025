package ar.edu.unq.integrador.opinion;

import java.time.LocalDate;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.usuario.Usuario;

public class OpinionUsuarioExperto extends Opinion {
	// Atributos
	
	// Constructor
	public OpinionUsuarioExperto(LocalDate f, Usuario a, Concepto c) {
		super(f,a,c);
	}
	
	// Metodos
	@Override
	public boolean esOpinionExperta() {

		return true;
	}

}
