package es.deusto.ingenieria.sd.jdo.lab;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Usuario {
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	String email;
	String name;
	List<Reserva> reserva = new ArrayList<>();
	
	//CONSTRUCTORES
	public Usuario(String email, String name) {
		super();

		this.name = name;
		this.email = email;
	}

	//GETERS Y SETTERS
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "email=" + email + ", name=" + name;
	}	
	
}

