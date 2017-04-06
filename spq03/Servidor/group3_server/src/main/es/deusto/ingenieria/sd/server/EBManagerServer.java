package es.deusto.ingenieria.sd.server;

import java.rmi.Naming;



import es.deusto.ingenieria.sd.server.remote.EBManager;
import es.deusto.ingenieria.sd.server.remote.IEBManager;

import es.deusto.ingenieria.sd.server.gateway.gatewayfacebook;
import es.deusto.ingenieria.sd.server.gateway.Igatewayfacebook;


public class EBManagerServer {

	public static void main(String[] args) {
		if (args.length != 5) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		String name1 = "//" + args[0] + ":" + args[1] + "/" + args[3];
		String name2 = "//" + args[0] + ":" + args[1] + "/" + args[4];
		//String IberiaServer = args[5] + ":" + args[6];
		//System.out.println("- Iberia Server '" + IberiaServer);
		try {
			Naming.rebind(name, EBManager.getInstance());
			EBManager.getInstance().setEBService(new EBService());
			//Naming.rebind(name1, gatewayfacebook.getInstance());  NO FUNCIONA PORQUE NO ES REMOTA
			
			gatewayfacebook.getInstance().setService(args[0], args[1], args[3]);
			GatewayVueling.getInstance().setService(args[0], args[1], args[4]);
			System.out.println("- EBManagerServer '" + name + "' active and waiting...");
			
			GatewayIberia.getInstance().setService(args[0], 1234);
			//System.out.println("- EBManagerServer Iberia Server '" + IberiaServer + "' active and waiting...");
			System.out.println("- EBManagerServer Iberia Server active and waiting...");
			
			/*EBService appService = new EBService();
			IEBManager MainServer = new EBManager(appService);
			Naming.rebind(name, MainServer);
			System.out.println("- ServiceFacade '" + name + "' active and waiting...");*/
			
			
		} catch (Exception e) {
			System.err.println("$ ServiceFacade exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}