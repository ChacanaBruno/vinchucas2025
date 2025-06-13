package ar.edu.unq.integrador.opinion;

import java.time.LocalDate;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.usuario.Usuario;

public class OpinionUsuarioExperto extends Opinion {
	// Atributos
	
	// Constructor
	public OpinionUsuarioExperto(LocalDate f, Usuario a, Concepto c, Muestra m) {
		super(f,a,c,m);
	}
	
	// Metodos
	@Override
	public boolean esOpinionExperta() {

		return true;
	}

}
