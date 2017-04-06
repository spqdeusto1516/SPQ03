package es.deusto.ingenieria.sd.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ArrayList;

import es.deusto.ingenieria.sd.server.EBService;
import es.deusto.ingenieria.sd.server.DTO.*;
import es.deusto.ingenieria.sd.vueling.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.jdo.lab.Vuelo;


public class EBManager extends UnicastRemoteObject implements IEBManager{

	private EBService appService;
	private static EBManager instance;
	public EBManager(EBService server) throws RemoteException{
		this.appService= server;
		
	}
	
	protected EBManager() throws RemoteException {
		super();
	}
	public static EBManager getInstance() {
		if (instance == null) {
			try {
				instance = new EBManager();
				
				
				
			} catch (Exception ex) {
				System.out.println("#....Error al crear el RemoteFacade....#" + ex);
			}
		}
		return instance;
	}
	
	//para pasar los args
		public void setEBService(EBService s){
			this.appService=s;
			
		}
	
	
	
	public boolean login(String nick, String contrasenia) throws RemoteException {
		System.out.println(nick+" está tratando de conectar.");
		return this.appService.login(nick, contrasenia);
			
				
	}
	
	public List<DTOVuelo> buscarVuelo(String origen, String destino, String fecha)throws RemoteException{
		System.out.println("Buscando vuelos "+ origen +"-"+destino+" para el dia "+fecha);
		
		return this.appService.buscarVuelo(origen, destino, fecha);
	}
	
	public void reservarVuelo (DTOVuelo v, String email, int plazas)throws RemoteException{
		System.out.println("Reserva en EBManager");
	
		this.appService.reservarVuelo(v,email, plazas);
	}
	
	
}
