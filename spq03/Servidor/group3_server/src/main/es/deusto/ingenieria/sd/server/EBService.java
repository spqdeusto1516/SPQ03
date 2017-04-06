package es.deusto.ingenieria.sd.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import es.deusto.ingenieria.sd.iberia.data.dto.JuegoIberiaDTO;
import es.deusto.ingenieria.sd.jdo.lab.Reserva;
import es.deusto.ingenieria.sd.jdo.lab.Usuario;
import es.deusto.ingenieria.sd.jdo.lab.Juego;
import es.deusto.ingenieria.sd.server.DTO.DTOJuego;
import es.deusto.ingenieria.sd.server.DTO.JuegoAssembler;
import es.deusto.ingenieria.sd.server.dao.ReservasDAO;

import es.deusto.ingenieria.sd.server.gateway.gatewayfacebook;
import es.deusto.ingenieria.sd.vueling.data.dto.JuegoDTO;;



public class EBService {

	ReservasDAO dao;
	public EBService(){
		//gatewayfacebook.getInstance().setService(ip,puerto, name);
		
	}
	public boolean login(String nick, String contrasenia ){
		return gatewayfacebook.getInstance().loginCorrecto(nick, contrasenia);		
	}
	
	/*public List<DTOJuego> buscarJuego(String origen, String destino, String fecha){
		
		//return GatewayVueling.getInstance().buscarJuego(origen, destino, fecha);

		List<JuegoDTO> JuegosVueling = new ArrayList<>();
		JuegosVueling = GatewayVueling.getInstance().buscarJuego(consola, fecha);
		
		List<JuegoIberiaDTO> JuegosIberia = new ArrayList<>();
		JuegosIberia = GatewayIberia.getInstance().buscarJuego(consola, fecha);
		
		List<DTOJuego> JuegosLista = new ArrayList<>();
		JuegoAssembler as = new JuegoAssembler();
		JuegosLista = as.assemble(JuegosVueling, JuegosIberia);
		return JuegosLista;
	}Hasta tener que buscarlos*/
	
/*
	public int reservarJuego(String JuegoId, int plazas){
		
		int PlazasQuedan = 0;
		
		if (JuegoId.toUpperCase().indexOf("IBERIA") > -1){
			System.out.println(" - EBService Reservando Juego Iberia...");
			PlazasQuedan = GatewayIberia.getInstance().reservarJuego(JuegoId, plazas);
			System.out.println(" - EBService Reservado Juego Iberia. Quedan " + PlazasQuedan + " plazas.");
			return PlazasQuedan;
		}
		if (JuegoId.toUpperCase().indexOf("VUELING") > -1){
			System.out.println(" - EBService Reservando Juego Vueling...");
			GatewayVueling.getInstance().reservarJuego(JuegoId, plazas);
			PlazasQuedan = 111;
		}
		
		return PlazasQuedan;
	}*/
	
	/*public void reservarJuego(DTOJuego v, String email, int plazas){
		dao = new ReservasDAO();
		int PlazasQuedan;
		String c = v.getId_Juego();
		String o = v.getOrigen();
		String d = v.getDestino();
		String f = v.getFecha();
		String h = v.getHora();
		Integer p = v.getPlazas();
		Juego Juego = new Juego(c, f, h, o, d, p);
		Usuario u = new Usuario();
		List<Usuario> lU = new ArrayList<>();
		
		lU = es.deusto.ingenieria.sd.jdo.lab.Main.listaU();
		
		int i = 0;
		while (i<lU.size()) {
			if(lU.get(i).getEmail().equals(email)){
				u = lU.get(i);
				i = lU.size();
			}
			i++;
		}
		System.out.println(u.getEmail()+ " "+u.getName());
		Reserva re = new Reserva(u, Juego);
		dao.storeReserva(re);
		
		if (Juego.getId_Juego().toUpperCase().indexOf("IBERIA") > -1){
			System.out.println(" - EBService Reservando Juego Iberia...");
			GatewayIberia.getInstance().reservarJuego(v.getId_Juego(), plazas);
			
		}
		if (Juego.getId_Juego().toUpperCase().indexOf("VUELING") > -1){
			System.out.println(" - EBService Reservando Juego Vueling...");
			GatewayVueling.getInstance().reservarJuego(v, email, plazas);
			
		}
		
	}HAsta tener que usarlo*/
	
	
	
	
	
	
	
	
	
	/*
	public EBService() {
		Juegos.put("1", new Juego(1, "25/09/2017", "19:30"));
		Juegos.put("2", new Juego(2, "2/07/2017", "9:00"));
	}
	
	public synchronized void createJuego(int id_Juego, String fecha, String hora) {
		
		
		if(!Juegos.containsKey(id_Juego)){
			Juego nuevo = new Juego(id_Juego, fecha, hora);
			//Juegos.put("1", nuevo);  
			//Juegos.put(fecha, nuevo);
			System.out.println("* Creando nuevo Juego: " + id_Juego + " , " + fecha + " , " + hora);
		}
		
	}
	
	public synchronized void deleteJuego(int id_Juego) {
		Juegos.remove(id_Juego);	
		System.out.println("* Removing Juego: " + id_Juego);
	}
	
	
	public synchronized List<Juego> getJuegos() {
		List<Juego> Juegos = new ArrayList<Juego>();
		
		for(Juego mo: this.Juegos.values())
		Juegos.add(new Juego(mo.getId_Juego(), mo.getFecha(), mo.getHora()));

		System.out.println("* Retrieving Juegos ...");
		
		return Vuelos;
	}

	*/
	
	
}
