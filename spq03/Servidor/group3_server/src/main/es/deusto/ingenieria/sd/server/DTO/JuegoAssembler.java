package es.deusto.ingenieria.sd.server.DTO;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.jdo.lab.Juego;
import es.deusto.ingenieria.sd.vueling.data.dto.JuegoDTO;


public class JuegoAssembler {
	
	/*public List<DTOJuego> assemble(List<JuegoDTO> JuegosVueling, List<JuegoIberiaDTO> JuegosIberia){
		List<DTOVuelo> vuelosDTO = new ArrayList<>();
		
		if (vuelosVueling != null){
			for(VueloDTO t: vuelosVueling){
				vuelosDTO.add(new DTOVuelo(t.getId_vuelo(), t.getFecha(),t.getHora(),t.getOrigen(),t.getDestino(),t.getPlazas()));
			}
		}
		if (vuelosIberia != null){
			for(VueloIberiaDTO t: vuelosIberia){
				vuelosDTO.add(new DTOVuelo(t.getId_vuelo(), t.getFecha(),t.getHora(),t.getOrigen(),t.getDestino(),t.getPlazas()));
			}
		}
		System.out.println("* Assembling Vuelos...");
		
		return vuelosDTO;
	}*/
	
}