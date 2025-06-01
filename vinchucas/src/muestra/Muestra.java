package muestra;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Opinion.Criterio;
import Opinion.Opinion;
import evaluador.Evaluable;
import evaluador.OpinionEvaluador;
import usuario.Participante;
import usuario.Usuario;

public class Muestra {

	private Criterio especie; // primer voto?
	private Foto foto; // DEPENDER DE ABSTRACCION
	private Participante usuario; // DEPENDER DE ABSTRACCION

	private Set<Opinion> opinionesRecibidas;

	private EstadoMuestra estadoMuestra;

	private Ubicacion ubicacion;

	private Evaluable evaluador;

	private Set<Opinion> opinionesBasicas;

	private Set<Opinion> opinionesExpertas;

	public Muestra(Criterio especie, Foto foto, Ubicacion ubicacion, Participante usuario) {
		this.especie = especie;
		this.foto = foto;
		this.usuario = usuario;
		this.ubicacion = ubicacion;
		opinionesRecibidas = new HashSet<>();
		estadoMuestra = new Publica();
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

	public Criterio opinionDePublicador() {
		return especie;
	}

	public Criterio resultadoActual() {

		if (this.getOpinionesExpertas().isEmpty()) {

			return this.resultadoActualPorBasico();

		} else {

			return this.resultadoActualPorExpertos();
		}

	}

	public Criterio resultadoActualPorExpertos() {

		return evaluador.resultadoActualPorExpertos(this.getOpinionesExpertas());
	}

	public Criterio resultadoActualPorBasico() {

		return evaluador.opcionGanadora(this.getOpinionesBasicas()); // por aca
	}

	public boolean hayAcuerdoEntreExpertos(Opinion unaOpinion) {

		return evaluador.hayAcuerdoEntreExpertos(this.getOpinionesExpertas(), unaOpinion);
	}

	// GETTERS AND SETTERS
	public Foto getFoto() {
		return foto;
	}

	public Criterio getEspecie() {
		return especie;
	}

	public void setEspecie(Criterio especie) {
		this.especie = especie;
	}

	public Set<Opinion> getOpinionesRecibidas() {
		return opinionesRecibidas;
	}

	public void setOpinionesRecibidas(Set<Opinion> opinionesRecibidas) {
		this.opinionesRecibidas = opinionesRecibidas;
	}

	public void setUsuario(Participante usuario) {
		this.usuario = usuario;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
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

	public Evaluable getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Evaluable evaluador) {
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
