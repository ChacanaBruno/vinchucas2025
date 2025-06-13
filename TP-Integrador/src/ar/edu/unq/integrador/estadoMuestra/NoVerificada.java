package ar.edu.unq.integrador.estadoMuestra;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;

public class NoVerificada implements EstadoMuestra{
	// Atributos
	
	// Constructor
	public NoVerificada() {}

	// Metodos
	@Override
	public void recibirOpinion(Opinion o, Muestra m) {
		if (o.esOpinionExperta()) {
			m.agregarOpinion(o);
			m.setEstadoMuestra(new EnEvaluacion());
		} else {
			m.agregarOpinion(o);
		}	
	}

	@Override
	public Concepto resultadoActual(Muestra m) {
		List<Opinion> opiniones = m.getOpiniones();
		
		return opiniones.stream()
				        .collect(Collectors.groupingBy(o -> o.getConcepto(), Collectors.counting())) // armo un Map Concepto Votos
		                .entrySet()
		                .stream()
		                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
		                .get()
		                .getKey();
	}

	@Override
	public boolean esVerificada() {
		return false;
	}
	
	

}
