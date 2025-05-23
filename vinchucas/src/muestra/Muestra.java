package muestra;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Opinion.Opciones;
import Opinion.Opinion;
import evaluador.Evaluable;
import evaluador.OpinionEvaluador;
import usuario.Participante;
import usuario.Usuario;

public class Muestra {

	private Opciones especie; // primer voto?
	private Foto foto; // DEPENDER DE ABSTRACCION
	private Participante usuario; // DEPENDER DE ABSTRACCION

	private Set<Opinion> opinionesRecibidas;

	private EstadoMuestra estadoMuestra;

	private Ubicacion ubicacion;

	private Evaluable evaluador;

	public Muestra(Opciones especie, Foto foto, Ubicacion ubicacion, Participante usuario) {
		this.especie = especie;
		this.foto = foto;
		this.usuario = usuario;
		this.ubicacion = ubicacion;
		opinionesRecibidas = new HashSet<>();
		estadoMuestra = new Publica();
		evaluador = new OpinionEvaluador();

	}

	public void recibirOpinion(Opinion unaOpinion) {
		if (!unaOpinion.getOpinador().equals(usuario)) {
			estadoMuestra.recibirOpinionPara(unaOpinion, this);
		}
	}

	public void registrarOpinion(Opinion unaOpinion) {
		opinionesRecibidas.add(unaOpinion);

	}

	public Opciones opinionDePublicador() {
		return especie;
	}

	public Opciones resultadoActual() {

		return evaluador.resultadoActual(this.getOpinionesRecibidas());
	}

	public Opciones resultadoActualPorExpertos() {

		return evaluador.resultadoActualPorExpertos(this.getOpinionesRecibidas());
	}

	public Opciones resultadoActualPorBasico() {

		return evaluador.resultadoActualPorBasico(this.getOpinionesRecibidas());
	}

	public boolean hayAcuerdoEntreExpertos(Opinion unaOpinion) {

		return evaluador.hayAcuerdoEntreExpertos(this.getOpinionesRecibidas(), unaOpinion);
	}

	// GETTERS AND SETTERS
	public Foto getFoto() {
		return foto;
	}

	public Opciones getEspecie() {
		return especie;
	}

	public void setEspecie(Opciones especie) {
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
}
