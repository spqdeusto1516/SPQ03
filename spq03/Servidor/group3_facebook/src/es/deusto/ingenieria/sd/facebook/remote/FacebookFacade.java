package es.deusto.ingenieria.sd.facebook.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.ingenieria.sd.facebook.data.Usuario;
import es.deusto.ingenieria.sd.facebook.db.DBManager;
import es.deusto.ingenieria.sd.facebook.service.facebookService;



public class FacebookFacade extends UnicastRemoteObject implements IFacebookFacade{
	@SuppressWarnings("unused")
	private static FacebookFacade instance;
	private static final long serialVersionUID = 1L;
	private facebookService fbservice;
	private DBManager man;
	public FacebookFacade(facebookService server) throws RemoteException {
		super();
		this.fbservice=server;
	}
	
	
	@Override
	public boolean loginAutenticar(String nick, String contrasenia) throws RemoteException {

		
		return this.fbservice.loginAutenticar(nick, contrasenia);
		/*if(man.getHusuarios().get(nick).getContrasenia().equals(contrasenia)){
			return true;
		}else{
			return false;
		}
		*/
	}

}
