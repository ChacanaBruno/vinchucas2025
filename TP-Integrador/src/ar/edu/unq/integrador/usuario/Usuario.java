package ar.edu.unq.integrador.usuario;

import ar.edu.unq.integrador.opinion.Opinion;
import ar.edu.unq.integrador.ubicacion.Ubicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.integrador.estadoMuestra.*;
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

	// Constructor
	public Usuario (String n, Categoria c, List<Muestra> ms, List<Opinion> os) {
		this.setNombre(n);
		this.setCategoria(c);
		this.setMuestrasEnviadas(ms);
		this.setOpinionesRealizadas(os);
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
	
	public void enviarMuestra(Muestra m) { // no envia la muestra a ningun lado, la registra en sus "envios"...
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
	
}
