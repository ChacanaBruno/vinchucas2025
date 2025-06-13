package ar.edu.unq.integrador.categoria;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.usuario.Usuario;

public interface Categoria {
	void opinarSobreMuestra(Usuario u, Concepto c, Muestra m);
	void promoverAExperto(Usuario u);
	void degradarABasico(Usuario u);
}
