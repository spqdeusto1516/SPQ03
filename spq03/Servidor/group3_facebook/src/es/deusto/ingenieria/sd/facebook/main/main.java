package es.deusto.ingenieria.sd.facebook.main;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.facebook.remote.FacebookFacade;
import es.deusto.ingenieria.sd.facebook.remote.IFacebookFacade;
import es.deusto.ingenieria.sd.facebook.service.facebookService;



public class main {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			facebookService appService = facebookService.getInstance();
			IFacebookFacade MainServer = new FacebookFacade(appService);
			Naming.rebind(name, MainServer);
			
			
			System.out.println("- ServiceFacade '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ ServiceFacade exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
