package ar.edu.unq.integrador.organizacion;

import ar.edu.unq.integrador.muestra.Muestra;
import ar.edu.unq.integrador.ubicacion.Ubicacion;
import ar.edu.unq.integrador.zonaDeCobertura.Interesado;
import ar.edu.unq.integrador.zonaDeCobertura.ZonaDeCobertura;

public class Organizacion implements Interesado {
	private Ubicacion ubicacion;
	private TipoOrganizacion organizacion;
	private int personas;

	public Organizacion(Ubicacion ubicacion, TipoOrganizacion organizacion, int personas, FuncionalidadExterna plugin) {
		super();
		this.ubicacion = ubicacion;
		this.organizacion = organizacion;
		this.personas = personas;
		this.plugin = plugin;
	}

	private FuncionalidadExterna plugin;

	public FuncionalidadExterna getPlugin() {
		return plugin;
	}

	public void setPlugin(FuncionalidadExterna plugin) {
		this.plugin = plugin;
	}

	@Override
	public void subscribirseAZona(ZonaDeCobertura zona) {
		zona.getNotificador().subscribir(this);

	}

	@Override
	public void desubscribirseAZona(ZonaDeCobertura zona) {
		zona.getNotificador().desuscribir(this);

	}

	@Override
	public void recibirNotifacionDeMuestra(ZonaDeCobertura zona, Muestra muestra) {
		// puling
		plugin.nuevoEvento(this, zona, muestra);

	}

	@Override
	public void recibirNotifacionDeMuestraValidada(ZonaDeCobertura zona, Muestra muestra) {
		plugin.nuevoEvento(this, zona, muestra);

	}
}
