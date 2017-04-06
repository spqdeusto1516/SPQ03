package es.deusto.ingenieria.sd.server.gateway;

import java.rmi.Naming;
import java.rmi.RemoteException;


import es.deusto.ingenieria.sd.facebook.remote.IFacebookFacade;



public class gatewayfacebook implements Igatewayfacebook{

	public static gatewayfacebook instance = null;
	private IFacebookFacade service;
	
	private gatewayfacebook(){
		super();
		
	}
	
	public static gatewayfacebook getInstance() {
		if (instance == null) {
			instance = new gatewayfacebook();
			
		}

		return instance;
	}

	public void setService(String ip, String port, String serverName) {

		try {
			String URL = "//" + ip + ":" + port + "/" + serverName;
			this.service = (IFacebookFacade) Naming.lookup(URL);
			System.out.println("Proxy: " + service);
			System.out.println(" * Autorizcion '" + URL + "' localizado!!");
			
		} catch (Exception ex) {
			System.err.println("# Error locating remote facade LOGIN: " + ex);
			ex.printStackTrace();
		}
	}

	public IFacebookFacade getService() {
		return this.service;
	}

	public boolean loginCorrecto(String nick, String contrasenia){
		try {
			return service.loginAutenticar(nick, contrasenia);
		} catch (RemoteException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	
}
	
	
	
	
	
}
