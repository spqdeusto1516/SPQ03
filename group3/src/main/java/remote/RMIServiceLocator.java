package es.deusto.ingenieria.sd.client.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;


import es.deusto.ingenieria.sd.server.remote.IEBManager;



public class RMIServiceLocator {
	
	private IEBManager service;
	
	public static RMIServiceLocator instance = null;
	
	public RMIServiceLocator(){ 
	    
    }
	
	  public static RMIServiceLocator getInstance()
	    {
	   return instance;
	    }
	
	  public void setService(String[] args) {    
	    	// Add your code to get the TARGET reference HERE    
	    	// Inicia el gestor de politicas de seguridad
	    			if (System.getSecurityManager() == null) {
	    				System.setSecurityManager(new SecurityManager());
	    			}

	    			// Cadena de conexion que le vamos a paras luego al registro RMI
	    			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

	    			
	    			try{
	    			 this.service = (IEBManager) java.rmi.Naming.lookup(name);
	    				System.out.println("* Server '" + name + "' active and waiting...");
	    			} catch (Exception e) {
	    				System.err.println("- Exception running the server: " + e.getMessage());
	    				e.printStackTrace();
	    			}
	    }
	  
	  
	  public IEBManager getService() {    	
	    	// Add your code to return the TARGET reference HERE
	    	return service;
	    }
	  
	

}
