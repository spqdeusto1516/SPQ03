package es.deusto.ingenieria.sd.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.client.remote.RMIServiceLocator;
//import es.deusto.ingenieria.sd.vueling.data.dto.VueloDTO;
import es.deusto.ingenieria.sd.server.DTO.*;
import es.deusto.ingenieria.sd.client.GUI.buscarVentana;
import es.deusto.ingenieria.sd.client.GUI.loginVentana;

import es.deusto.ingenieria.sd.server.*;



public class EBController {
	
	private RMIServiceLocator rsl;
	
	
	public EBController(String[] args) throws RemoteException {
		rsl = new RMIServiceLocator();
		rsl.setService(args);
		new loginVentana(this);
	}
	

	
    public boolean login(String nick, String contrasenia) {
    	try {
    		
    		return rsl.getService().login(nick, contrasenia);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    	return false;
    }
    
    public List<DTOVuelo> buscarVuelo(String origen, String destino, String fecha) {
    	try {
    		return rsl.getService().buscarVuelo(origen,destino, fecha);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    	return null;
    }
    
   /* public int reservarVuelo(String vueloId, int plazas){
    	
    	
    	try{
    		return rsl.getService().reservarVuelo(vueloId, plazas);
    	}catch(Exception e){
    		e.printStackTrace();
    		return -1;
    	}
    }*/
    public void reservarVuelo(DTOVuelo v, String email,int plazas){
    	try{
    		rsl.getService().reservarVuelo(v, email, plazas);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
    
    public void exit() {
		System.exit(0);
	}
    
	
    public static void main(String[] args)throws RemoteException {
		
    	new EBController(args);
    	/*
    	try {
			new EBController(args);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
    
	/*
	public EBController(String[] args) throws RemoteException{
		// Add your code HERE - Related to the Service Locator
					
		rsl = new RMIServiceLocator();
		//pide el servicio
		rsl.setService(args[0], args[1], args[2]);
		//new SMSWindow(this);
		//new SwitchServerGUI(this);
	}
	
	public void switchServer(String ip, String port, String name){
		// Add your code HERE - Related to switching servers
		
		//vamos a cambiar de servidor
		rsl.setService(ip, port, name);
		System.out.println("- Switching to server: " + ip + ":" + port + "/" + name);
	}
	*/
	
	
	
	
	
	
	
}
