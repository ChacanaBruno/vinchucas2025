package ar.edu.unq.integrador.usuario;

import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.ubicacion.Ubicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.integrador.estadoMuestra.*;
import ar.edu.unq.integrador.categoria.Basico;
import ar.edu.unq.integrador.categoria.Categoria;
import ar.edu.unq.integrador.concepto.Concepto;
import ar.edu.unq.integrador.formulario.Formulario;
import ar.edu.unq.integrador.muestra.Muestra;

public class Usuario {
	// Atributos
	private String nombre;
	private Categoria categoria;
	private List<Muestra> muestrasEnviadas;
	private List<Opinion> opinionesRealizadas; // esto puede ser un (Map Muestra Opinion)?? facilitaria la subida de rango asi...

	// Constructores
	public Usuario (String n, Categoria c, List<Muestra> ms, List<Opinion> os) {
		this.setNombre(n);
		this.setCategoria(c);
		this.setMuestrasEnviadas(ms);
		this.setOpinionesRealizadas(os);
	}
	
	public Usuario(String nombre) { // este es el constructor default para los usuarios, todos son basicos cuando arrancan, cambian segun criterio de subida de rango...
		this.setNombre(nombre);
		this.setCategoria(new Basico());
		this.setMuestrasEnviadas(new ArrayList<>());
		this.setOpinionesRealizadas(new ArrayList<>());
	}
	
	// Accessors
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Muestra> getMuestrasEnviadas() {
		return muestrasEnviadas;
	}
	public void setMuestrasEnviadas(List<Muestra> muestrasEnviadas) {
		this.muestrasEnviadas = muestrasEnviadas;
	}
	public List<Opinion> getOpinionesRealizadas() {
		return opinionesRealizadas;
	}
	public void setOpinionesRealizadas(List<Opinion> opinionesRealizadas) {
		this.opinionesRealizadas = opinionesRealizadas;
	}

	public Formulario crearFormulario(String foto, Concepto concepto, Ubicacion ubicacion) {
		return new Formulario(foto, concepto, ubicacion, this);
	}
	
	public Muestra crearMuestra(Formulario f) {
		Concepto concepto = f.getConcepto();
		EstadoMuestra estadoInicial = new NoVerificada(); // esto despues lo cambia la opinion que envia segun la categoria en opinarSobreMuestra()
		List<Opinion> opiniones = new ArrayList<>();
		
		Muestra muestra = new Muestra(f, estadoInicial, opiniones);
		
		this.opinarSobreMuestra(concepto, muestra); // esto ya guarda la opinion en el historial del usuario
		this.enviarMuestra(muestra); // y esto la guarda en su historial de muestras creadas por el mismo
		return muestra;
	}
	
	private void enviarMuestra(Muestra m) { // no envia la muestra a ningun lado, la registra en sus "envios"...
		this.getMuestrasEnviadas().add(m);
	}
	
	public void opinarSobreMuestra(Concepto concepto, Muestra muestra) {
		this.getCategoria().opinarSobreMuestra(this, concepto, muestra); 
	}
	
	public void registrarOpinionEnHistorial(Opinion o) {
		this.getOpinionesRealizadas().add(o);
	}
	
	public int cantidadTotalDeOpinionesRealizadas() {
		return this.getOpinionesRealizadas().size();
	}
	
	public int cantidadTotalDeMuestrasEnviadas() {
		return this.getMuestrasEnviadas().size();
	}
	
	public void promoverAExperto() {
		this.getCategoria().promoverAExperto(this);
	}
	
	public boolean cumpleRequisitoDeEnviosDeMuestra() {
		List<Muestra> muestrasEnviadas = this.getMuestrasEnviadas();
		LocalDate hace30Dias = LocalDate.now().minusDays(30);
		
		long cantidad = muestrasEnviadas.stream()
			                            .filter(m -> m.getFecha().isAfter(hace30Dias) || m.getFecha().isEqual(hace30Dias))
			                            .count();

		return cantidad >= 10;
	}
	
	public boolean cumpleRequisitoDeOpinionesRealizadas() {
		List<Opinion> opinionesRealizadas = this.getOpinionesRealizadas();
		LocalDate hace30Dias = LocalDate.now().minusDays(30);
		
		long cantidad = opinionesRealizadas.stream()
			                               .filter(o -> o.getFecha().isAfter(hace30Dias) || o.getFecha().isEqual(hace30Dias)) // opiniones de los ultimos 30 dias
			                               .filter(o -> o.getMuestraOpinada().estaVerificada()) // tienen que estar verificadas
			                               .filter(o -> o.getConcepto().equals(o.getMuestraOpinada().resultadoActual())) // la opinion que envio el usuario tiene que ser la misma que el resultado actual de la muestra verificada, sino no sirve para nada la opinion
			                               .count();

		return cantidad >= 20;
	}
	
	public void degradarABasico() {
		this.getCategoria().degradarABasico(this);
	}
	
}
