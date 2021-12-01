package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;
import java.util.List;

import org.trello4j.Trello;

public class TrelloID {
	
	
	
	
	//Passagem de um nome para a obtenção do seu ID    
		public String getQuadroID(String nomeQuadro) {
							
				String ObterID ="";
				
				
				//if (map == TrelloQuadros.projetos){
					//HashMap<String,String> projetos = TrelloQuadros.getNomeDoQuadros(trelloApi);
				
					
				//}
				
				
					
				for (int i = 0 ; i < TrelloQuadros.quadros.size() ; i++) {
					if (TrelloQuadros.quadros.get(i).getName().equalsIgnoreCase(nomeQuadro)){
						
						ObterID = TrelloQuadros.quadros.get(i).getId();			
					}
					
				}
				
				System.out.println(ObterID);
				return ObterID;
				
			}
		
		
		
		public String getFilaID(String nomeQuadro , String NomeFila) {
			
			String ObterID ="";	
			
			List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(nomeQuadro);

				
			for (int i = 0 ; i < filas.size() ; i++) {
				if (filas.get(i).getName().equalsIgnoreCase(NomeFila)){
					
					ObterID = filas.get(i).getId();			
				}
				
			}
			return ObterID;
			
		}
		

}
