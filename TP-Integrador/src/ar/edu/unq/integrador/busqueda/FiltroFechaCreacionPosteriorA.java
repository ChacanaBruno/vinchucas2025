package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroFechaCreacionPosteriorA implements FiltroDeBusqueda {
    private LocalDate fecha;

    public FiltroFechaCreacionPosteriorA(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        return muestra.getFecha().isAfter(fecha);
    }
}