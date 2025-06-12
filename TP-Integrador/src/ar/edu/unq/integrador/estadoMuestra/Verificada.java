package ar.edu.unq.integrador.estadoMuestra;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;

public class Verificada implements EstadoMuestra {
	// Atributos
	
	// Constructor
	public Verificada() {};
	
	// Metodos
	@Override
	public void recibirOpinion(Opinion o, Muestra m) {
		throw new RuntimeException("No se aceptan mas opiniones");
	}

	@Override
	public Concepto resultadoActual(Muestra m) {
		List<Opinion> opiniones = m.getOpiniones();
		return opiniones.stream()
		        .filter(o -> o.esOpinionExperta())
		        .collect(Collectors.groupingBy(o -> o.getConcepto(), Collectors.counting())) // armo un Map Concepto Votos
                .entrySet()
                .stream()
                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .get()
                .getKey();
	}
	
	
	
	
}
