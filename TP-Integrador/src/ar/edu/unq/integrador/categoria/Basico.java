package ar.edu.unq.integrador.categoria;

import java.time.LocalDate;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.usuario.Usuario;
import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.opinion.OpinionUsuarioBasico;

public class Basico implements Categoria{
	// Atributos
	
	// Constructor
	public Basico() {}

	// Metodos
	@Override
	public void opinarSobreMuestra(Usuario u, Concepto c, Muestra m) {
		Opinion opinionUsuarioBasico = new OpinionUsuarioBasico(LocalDate.now(), u, c);
		
		m.recibirOpinion(opinionUsuarioBasico);
		u.registrarOpinionEnHistorial(opinionUsuarioBasico);
	};
	
}
