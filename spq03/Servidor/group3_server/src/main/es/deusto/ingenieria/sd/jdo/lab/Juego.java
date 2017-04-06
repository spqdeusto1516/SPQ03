package es.deusto.ingenieria.sd.jdo.lab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Juego{
	String id_Juego;
	String fecha;
	String hora;
	String Consola;
	int unidades;
	
	
	public Juego(String id_Juego, String fecha, String hora, String Consola, int Unidades) {
		super();
		this.id_Juego = id_Juego;
		this.fecha = fecha;
		this.hora = hora;
		this.Consola = Consola;
		this.unidades = Unidades;
	}

	public int getUnidades() {
		return Unidades;
	}


	public void setUnidades(int Unidades) {
		this.Unidades = Unidades;
	}


	public String getId_Juego() {
		return id_Juego;
	}
	public void setId_Juego(String id_Juego) {
		this.id_Juego = id_Juego;
	}

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getConsola() {
		return Consola;
	}
	public void setConsola(String Consola) {
		this.Consola = Consola;
	}
}
