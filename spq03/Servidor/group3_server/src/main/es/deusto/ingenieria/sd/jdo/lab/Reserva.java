package es.deusto.ingenieria.sd.jdo.lab;

import java.util.spi.CalendarDataProvider;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import org.datanucleus.store.types.converters.CalendarDateConverter;

@PersistenceCapable(detachable="true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Reserva {
	private Usuario u;
	private Juego v;
	
	public Reserva(Usuario u, Juego v) {
		super();
		this.u = u;
		this.v = v;
	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	public Juego getV() {
		return v;
	}

	public void setV(Juego v) {
		this.v = v;
	}

	
	
	
	
}

