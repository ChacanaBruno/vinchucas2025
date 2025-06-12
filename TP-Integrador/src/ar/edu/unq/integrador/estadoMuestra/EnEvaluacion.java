package ar.edu.unq.integrador.estadoMuestra;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.opinion.Opinion;

public class EnEvaluacion implements EstadoMuestra {
	// Atributos
	
	// Constructor
	public EnEvaluacion() {};
	
	// Metodos
	@Override
	public void recibirOpinion(Opinion o, Muestra m) {
		
		if (!o.esOpinionExperta()) {
			throw new RuntimeException("Solo pueden opinar Expertos!");
		} else {
			m.agregarOpinion(o);
			this.intentarVerificar(m);
		}
	}

	private void intentarVerificar(Muestra m) {
		
		if (m.hayConsensoEntreExpertos()) {
			m.setEstadoMuestra(new Verificada());
		}	
	}

	@Override
	public Concepto resultadoActual(Muestra m) {
		return Concepto.NO_DEFINIDO;
//		List<Opinion> opiniones = m.getOpiniones();		// NO HACE FALTA ESTO PORQUE SI ESTA COMO ENEVALUACION ES PORQUE NO HAY CONSENSO!!
//		if (!m.hayConsensoEntreExpertos()){
//			return Concepto.NO_DEFINIDO;
//		} else {
//			return opiniones.stream()
//					        .filter(o -> o.esOpinionExperta())
//					        .collect(Collectors.groupingBy(o -> o.getConcepto(), Collectors.counting())) // armo un Map Concepto Votos
//			                .entrySet()
//			                .stream()
//			                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
//			                .get()
//			                .getKey();
//		}
	}
	
	
	

}
