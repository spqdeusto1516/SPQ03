package es.deusto.ingenieria.sd.facebook.db;

import java.util.HashMap;

import es.deusto.ingenieria.sd.facebook.data.Usuario;


public class DBManager {
	
	private HashMap<String, Usuario> Husuarios;
	public static DBManager instance = new DBManager();

	public static DBManager getInstance() {
		return instance;
	}
	
	
	public DBManager(){
		
		Husuarios= new HashMap<String, Usuario>();
		Usuario uirati= new Usuario("ira.zar@opendeusto.es","123");
		Usuario ujon= new Usuario("jon.marco@opendeusto.es","123");
		Usuario uainhoa= new Usuario("ainhoa.goirigolzarri@opendeusto.es","123");
		Usuario umikel= new Usuario("mikelruiz93@opendeusto.es","123");
		Husuarios.put("ira.zar@opendeusto.es", uirati);
		Husuarios.put("ainhoa.goirigolzarri@opendeusto.es", uainhoa);
		Husuarios.put("mikelruiz93@opendeusto.es", umikel);
		Husuarios.put("jon.marco@opendeusto.es", ujon);
	
	}
	
	public HashMap<String, Usuario> getHusuarios() {
		return Husuarios;
	}


	public void setHusuarios(HashMap<String, Usuario> husuarios) {
		Husuarios = husuarios;
	}
	
	
	
	

}
