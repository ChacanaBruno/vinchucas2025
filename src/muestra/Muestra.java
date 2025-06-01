package muestra;

import java.util.HashSet;

import java.util.Set;

import estadoMuestra.EstadoMuestra;
import estadoMuestra.Publica;
import opinion.Criterio;
import opinion.Opinion;
import ubicacion.Ubicacion;
import evaluador.Evaluador;
import evaluador.OpinionEvaluador;
import usuario.Participante;
import usuario.Usuario;

public class Muestra {

	private Participante usuario; // DEPENDER DE ABSTRACCION

	private Set<Opinion> opinionesRecibidas;

	private EstadoMuestra estadoMuestra;

	private Ubicacion ubicacion;

	private Evaluador evaluador;

	private Set<Opinion> opinionesBasicas;

	private Set<Opinion> opinionesExpertas;

	public Muestra(Ubicacion ubicacion, Participante usuario) {

		this.usuario = usuario;
		this.ubicacion = ubicacion;
		opinionesBasicas = new HashSet<>();
		opinionesExpertas = new HashSet<>();
		estadoMuestra = new Publica(); // si el usuario envia la muestra entonces este estado depende del rango del
										// usuario
		evaluador = new OpinionEvaluador();

	}

	public void recibirOpinionBasica(Opinion unaOpinion) {
		if (validacionIdentidad(unaOpinion)) {
			estadoMuestra.recibirOpinionBasica(unaOpinion, this);
		}
	}

	public void recibirOpinionExperta(Opinion unaOpinion) {
		if (validacionIdentidad(unaOpinion)) {
			estadoMuestra.recibirOpinionExperta(unaOpinion, this);
		}
	}

	public boolean validacionIdentidad(Opinion unaOpinion) {
		return !unaOpinion.getOpinador().equals(usuario);
	}

	public void registrarOpinion(Opinion unaOpinion) {
		opinionesRecibidas.add(unaOpinion);

	}

	/*
	 * NO BORRAR POR AHORA public Criterio resultadoActual() {
	 * 
	 * if (this.getOpinionesExpertas().isEmpty()) {
	 * 
	 * return this.opcionGanadora(this.getOpinionesBasicas());
	 * 
	 * } else {
	 * 
	 * return this.opcionGanadora(this.getOpinionesExpertas()); }
	 * 
	 * }
	 */

	public Criterio resultadoActual() {

		return estadoMuestra.resultadoActualPara(this);

	}

	public Criterio opcionGanadora(Set<Opinion> opiniones) {
		return evaluador.opcionGanadora(opiniones);
	}

	public boolean hayAcuerdoEntreExpertos(Opinion unaOpinion) {

		return evaluador.hayAcuerdoEntreExpertos(this.getOpinionesExpertas(), unaOpinion);
	}
	
	
	public boolean hayAcuerdoEntreExpertosTest() { // despues lo arreglo bach

		return evaluador.hayAcuerdoEntreExpertosTest(this.getOpinionesExpertas());
	}


	// GETTERS AND SETTERS

	public Set<Opinion> getOpinionesRecibidas() {
		return opinionesRecibidas;
	}

	public void setOpinionesRecibidas(Set<Opinion> opinionesRecibidas) {
		this.opinionesRecibidas = opinionesRecibidas;
	}

	public void setUsuario(Participante usuario) {
		this.usuario = usuario;
	}

	public Participante getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EstadoMuestra getEstadoMuestra() {
		return estadoMuestra;
	}

	public void setEstadoMuestra(EstadoMuestra estadoMuestra) {
		this.estadoMuestra = estadoMuestra;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Evaluador getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Evaluador evaluador) {
		this.evaluador = evaluador;
	}

	public void agregarOpinionBasica(Opinion unaOpinion) {
		opinionesBasicas.add(unaOpinion);
	}

	public void agregarOpinionExperta(Opinion unaOpinion) {
		opinionesExpertas.add(unaOpinion);

	}

	public Set<Opinion> getOpinionesBasicas() {
		return opinionesBasicas;
	}

	public void setOpinionesBasicas(Set<Opinion> opinionesBasicas) {
		this.opinionesBasicas = opinionesBasicas;
	}

	public Set<Opinion> getOpinionesExpertas() {
		return opinionesExpertas;
	}

	public void setOpinionesExpertas(Set<Opinion> opinionesExpertas) {
		this.opinionesExpertas = opinionesExpertas;
	}

}