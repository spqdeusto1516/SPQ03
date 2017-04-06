package es.deusto.ingenieria.sd.server.dao;


import java.util.List;

import es.deusto.ingenieria.sd.jdo.lab.Reserva;
import es.deusto.ingenieria.sd.jdo.lab.Usuario;

public interface IReservasDAO {
	public void storeReserva(Reserva product);
	public List<Usuario> getUsuario();
}
