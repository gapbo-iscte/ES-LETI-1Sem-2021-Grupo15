package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;
import java.util.List;

import org.trello4j.Trello;

public class TrelloFilas {
	
	//public static HashMap<String,String> filas = new HashMap<String,String>();
	
	
	
	public static List<org.trello4j.model.List> getFilasQuadro(Trello trelloApi, String NomeDoQuadro){
			
			String ProjectID= TrelloID.getQuadroID(NomeDoQuadro);
			
			
			
			
			List<org.trello4j.model.List> filasQuadro = trelloApi.getListByBoard(ProjectID);
			
			
			for (org.trello4j.model.List  fila: filasQuadro) {
				//filas.put(fila.getId(),fila.getName());
				
				System.out.println(fila.getName() + "-" + fila.getId() );
				
			}
			
			return filasQuadro;
			
			
		}
		



	
	
	
	
	

}
