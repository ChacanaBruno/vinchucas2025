package ar.edu.unq.integrador.busqueda;

import java.time.LocalDate;

import ar.edu.unq.integrador.muestra.Muestra;

public class FiltroFechaUltimaVotacionPosteriorA implements FiltroDeBusqueda {
    private LocalDate fecha;

    public FiltroFechaUltimaVotacionPosteriorA(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        return muestra.getFechaUltimaVotacion().isAfter(fecha);
    }
}
