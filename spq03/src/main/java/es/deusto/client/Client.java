package es.deusto.client;

        import java.rmi.RMISecurityManager;
        import es.deusto.data.MoneyDTO;
        import es.deusto.data.UserDTO;
        import es.deusto.remote.ITransferer;

@SuppressWarnings("deprecation")
public class Client {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
            System.exit(0);
        }

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
            ITransferer objHello = (ITransferer) java.rmi.Naming.lookup(name);
            // Register to be allowed to send messages
            System.out.println("Register a user for the first time: dipina");
            objHello.registerUser("dipina", "dipina");
            System.out.println("Change the password as the user is already registered: cortazar");
            objHello.registerUser("dipina", "cortazar");
            System.out.println("* MoneyDTO coming from the server: '" + objHello.sayMoney( "dipina", "cortazar", 69) + "'");
            System.out.println("* MoneyDTO coming from the server: '" + objHello.sayMoney("dipina", "cortazar", 100) + "'");


        } catch (Exception e) {
            System.err.println("RMI Example exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}