package ar.edu.unq.integrador.estadoMuestra;

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

}
