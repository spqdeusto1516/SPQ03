package es.deusto.ingenieria.sd.jdo.lab;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;



public class Main {
	
	public static List<Usuario> listaU (){
		
		Usuario usu = new Usuario("ira.zar@opendeusto.es", "Irati Zarraga");
		Usuario usu1 = new Usuario("jon.marco@opendeusto.es", "Jon Marco");	
		Usuario usu2 = new Usuario("ainhoa@opendeusto.es", "Ainhoa Goirigolzarri");
		Usuario usu3 = new Usuario("mikel@opendeusto.es", "Mikel Ruiz");

		List<Usuario> u = new ArrayList<>();
	
		u.add(usu);
		u.add(usu1);
		u.add(usu2);
		u.add(usu3);
		
		return u;
	}
	
	public static void main(String[] args) {
		
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
	
			Transaction tx = pm.currentTransaction();
//INSERTA LOS USUARIOS DE UNO EN UNO EN LA BASE DE DATOS
			try {				
				Usuario usu = new Usuario("ira.zar@opendeusto.es", "Irati Zarraga");
				Usuario usu1 = new Usuario("jon.marco@opendeusto.es", "Jon Marco");	
				Usuario usu2 = new Usuario("ainhoa@opendeusto.es", "Ainhoa Goirigolzarri");
				Usuario usu3 = new Usuario("mikel@opendeusto.es", "Mikel Ruiz");	
				tx.begin();
				pm.makePersistent(usu);
				pm.makePersistent(usu1);
				pm.makePersistent(usu2);
				pm.makePersistent(usu3);
				System.out.println("Inserting contents into the database ....");
				tx.commit();
			} catch (Exception ex) {
				System.out.println("# Error storing objects: " + ex.getMessage());				
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				
				pm.close();
			}	
//ELIMINA UN UNICO USUARIO DE LA BASE DE DATOS			
			try {
				//Get the Persistence Manager
				pm = pmf.getPersistenceManager();
				//Obtain the current transaction
				tx = pm.currentTransaction();		
				//Start the transaction
				tx.begin();

				Query<Usuario> qu = pm.newQuery(Usuario.class);
				qu.setFilter("email == 'ira.zar@opendeusto.es'");
				qu.deletePersistentAll();
				System.out.println("Eliminando de la base de datos");
				//End the transaction
				tx.commit();
			} catch (Exception ex) {
				System.err.println(" $ Error al eliminar de la base de datos: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
				
				if (pm != null && !pm.isClosed()) {
					pm.close();
				}
			}
			
			
//SELECCIONA UN UNICO USUARIO DE LA BASE DE DATOS				
			try {
				//Get the Persistence Manager
				pm = pmf.getPersistenceManager();
				//Obtain the current transaction
				tx = pm.currentTransaction();		
				//Start the transaction
				tx.begin();

				Query<Usuario> qu = pm.newQuery(Usuario.class);
				qu.setFilter("email == 'jon.marco@opendeusto.es'");
				System.out.println("El usuario seleccionado es:"+qu.execute().toString());
				//End the transaction
				tx.commit();
			} catch (Exception ex) {
				System.err.println(" $ Error al seleccionar de la base de datos: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
				
				if (pm != null && !pm.isClosed()) {
					pm.close();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			}
				
	}
}
