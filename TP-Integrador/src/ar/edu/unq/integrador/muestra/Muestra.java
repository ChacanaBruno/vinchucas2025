package ar.edu.unq.integrador.muestra;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.estadoMuestra.EstadoMuestra;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.notificador.NotificadorDeZonas;
import ar.edu.unq.integrador.notificador.Notificador;
import ar.edu.unq.integrador.notificador.Interesado;
import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.usuario.Usuario;

public class Muestra implements Notificador{
	// Atributos
	private Formulario formulario;
	private EstadoMuestra estadoMuestra;
	private List<Opinion> opiniones;
	private Notificador notificador;

	// Constructor
	public Muestra(Formulario formulario, EstadoMuestra estadoMuestra, List<Opinion> opiniones) {
		this.setFormulario(formulario);
		this.setEstadoMuestra(estadoMuestra);
		this.setOpiniones(opiniones);
		this.setNotificador(new NotificadorDeZonas());
	}

	// Accessors
	public LocalDate getFecha() {
		return this.getFormulario().getFechaCreacion();
	}

//	public void setFecha(LocalDate f) {
//		this.getFormulario().setFechaCreacion(f);
//	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public EstadoMuestra getEstadoMuestra() {
		return estadoMuestra;
	}

	public void setEstadoMuestra(EstadoMuestra estadoMuestra) {
		this.estadoMuestra = estadoMuestra;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}
	
	public Notificador getNotificador() {
		return notificador;
	}

	public void setNotificador(Notificador notificador) {
		this.notificador = notificador;
	}

	// Metodos
	public Usuario getAutor() {
		return this.getFormulario().getAutor();
	}

	public String getFoto() {
		return this.getFormulario().getFoto();
	}

	public Ubicacion getUbicacion() {
		return this.getFormulario().getUbicacion();
	}

	public void agregarOpinion(Opinion op) {
		Usuario autor = op.getAutor();
		List<Opinion> opiniones = this.getOpiniones();

		if (elUsuarioYaOpino(autor)) {
			throw new RuntimeException("El usuario ya registra una opinion en la muestra");
		} else {
			opiniones.add(op);
		}
	}

	public boolean elUsuarioYaOpino(Usuario usuario) {
		List<Opinion> opiniones = this.getOpiniones();
		return opiniones.stream().anyMatch(o -> o.getAutor().equals(usuario));
	}

	public void recibirOpinion(Opinion o) {
		this.getEstadoMuestra().recibirOpinion(o, this);
	}

	public boolean hayConsensoEntreExpertos() {
		List<Opinion> opiniones = this.getOpiniones();

		return opiniones.stream().filter(o -> o.esOpinionExperta())
				.collect(Collectors.groupingBy(o -> o.getConcepto(), Collectors.counting())).values().stream()
				.anyMatch(count -> count >= 2);
	}

	public Concepto resultadoActual() {
		return this.getEstadoMuestra().resultadoActual(this);
	}

	public boolean estaVerificada() {
		return this.getEstadoMuestra().esVerificada();
	}

	public LocalDate getFechaUltimaVotacion() {
		return this.getOpiniones().stream().map(Opinion::getFecha).max(LocalDate::compareTo).orElse(null);
	}
	
	// para poder avisar que se verificó
	@Override
	public void notificarVerificacion(Notificador notificador) { // delego la notificacion de verificacion en el notificador que tengo como colaborador
		this.getNotificador().notificarVerificacion(notificador);
	}

	@Override
	public void suscribirInteresado(Interesado interesado) { // delego al notificador almacenado el trabajo de suscribir un interesado
		this.getNotificador().suscribirInteresado(interesado);
	}

	@Override
	public void desuscribirInteresado(Interesado or) { // delego al notificador almacenado el trabajo de suscribir un interesado
		this.getNotificador().desuscribirInteresado(or);
	}
}
