package es.deusto.ingenieria.sd.facebook.data;

public class Usuario {

	private String nick;
	private String contrasenia;
	
	public Usuario(String nick, String contrasenia) {
		super();
		this.nick = nick;
		this.contrasenia = contrasenia;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
	
	
}
