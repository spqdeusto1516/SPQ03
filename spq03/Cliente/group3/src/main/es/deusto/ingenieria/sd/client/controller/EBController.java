package es.deusto.ingenieria.sd.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.client.remote.RMIServiceLocator;
//import es.deusto.ingenieria.sd.vueling.data.dto.JuegoDTO;
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
    
    public List<DTOJuego> buscarJuego(String Consolan, String fecha) {
    	try {
    		return rsl.getService().buscarJuego(Consola, fecha);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    	return null;
    }
    
   /* public int reservarJuego(String JuegoId, int unidades){
    	
    	
    	try{
    		return rsl.getService().reservarJuego(JuegoId, unidades);
    	}catch(Exception e){
    		e.printStackTrace();
    		return -1;
    	}
    }*/
    public void reservarJuego(DTOJuego v, String email,int unidades){
    	try{
    		rsl.getService().reservarJuego(v, email, unidades);
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
