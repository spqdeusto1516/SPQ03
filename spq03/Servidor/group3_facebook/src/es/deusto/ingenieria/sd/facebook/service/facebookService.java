package es.deusto.ingenieria.sd.facebook.service;

import java.util.HashMap;

import es.deusto.ingenieria.sd.facebook.data.Usuario;
import es.deusto.ingenieria.sd.facebook.db.DBManager;


public class facebookService {

	public static facebookService instance = null;

	public static facebookService getInstance() {
		if (instance == null) {
			instance = new facebookService();
		}
		return instance;
	}

	public facebookService() {
	}
	
	public synchronized boolean loginAutenticar(String nick, String contrasenia){
		HashMap<String, Usuario> Husuarios;
		Husuarios=DBManager.getInstance().getHusuarios();
		Usuario usr;
		
	
		usr=Husuarios.get(nick);
		if(usr==null){
			return false;
		}else{
			return true;
		}
		
	}
	
	
}
