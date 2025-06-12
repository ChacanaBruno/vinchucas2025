package ar.edu.unq.integrador.categoria;

import java.time.LocalDate;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.opinion.OpinionUsuarioExperto;
import ar.edu.unq.integrador.usuario.Usuario;

public class Experto implements Categoria {
	// Atributos
	
	// Constructor
	public Experto() {}

	// Metodos
	@Override
	public void opinarSobreMuestra(Usuario u, Concepto c, Muestra m) {
		Opinion opinionUsuarioExperto = new OpinionUsuarioExperto(LocalDate.now(), u, c);
		
		m.recibirOpinion(opinionUsuarioExperto);
	};
	
	
	
}
