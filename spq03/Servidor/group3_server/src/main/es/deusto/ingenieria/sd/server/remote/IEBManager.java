package es.deusto.ingenieria.sd.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.jdo.lab.Vuelo;
import es.deusto.ingenieria.sd.server.DTO.*;
import es.deusto.ingenieria.sd.vueling.data.dto.VueloDTO;

public interface IEBManager extends Remote{

	
	public boolean login(String nick, String contrasenia)throws RemoteException;	
	public List<DTOVuelo> buscarVuelo(String origen, String destino, String fecha)throws RemoteException;
	public void reservarVuelo(DTOVuelo v, String email, int plazas)throws RemoteException;
	
}
