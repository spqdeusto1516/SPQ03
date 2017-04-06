package es.deusto.ingenieria.sd.server.DTO;

import java.io.Serializable;


public class DTOJuego implements Serializable{
	String id_Juego;
	String fecha;
	String hora;
	String Consola;
	int Unidades;
	
	
	public DTOJuego(String id_Juego, String fecha, String hora, String consola, int unidades) {
		
		this.id_Juego = id_Juego;
		this.fecha = fecha;
		this.hora = hora;
		this.Consola = Consola;
		this.destino = destino;
		this.Unidades = Unidades;
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
