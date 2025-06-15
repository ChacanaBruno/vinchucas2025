package ar.edu.unq.integrador.busqueda;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroTipoInsecto implements FiltroDeBusqueda {
    private Concepto tipoInsecto;

    public FiltroTipoInsecto(Concepto tipoInsecto) {
        this.tipoInsecto = tipoInsecto;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        return muestra.resultadoActual().equals(tipoInsecto); // deberia preguntar por el la primera opnion?
    }
}