import static org.junit.Assert.*;
import static org.mockito.Mockito.*; 

import java.rmi.RemoteException;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;  
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.mockito.Mock;  
import org.mockito.runners.MockitoJUnitRunner;  

import es.deusto.server.db.DB;
import es.deusto.server.db.dao.IDAO;

@RunWith(MockitoJUnitRunner.class) 
public class Mock {

	DB database;
	
	@Mock
	IDAO dao;
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(Mock.class);
	}
	
	@Before
	public void setUp() throws Exception {		
		database = new DB(dao);
	}
	

}
