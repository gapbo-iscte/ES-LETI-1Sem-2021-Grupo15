package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;

import org.trello4j.Trello;

public class TrelloID {
	
	
	
	
	//Passagem de um nome para a obtenção do seu ID    
		public static String getQuadroID(Trello trelloApi, String nomeQuadro) {
							
				String ObterID ="";
				
				
				//if (map == TrelloQuadros.projetos){
					HashMap<String,String> projetos = TrelloQuadros.getNomeDoQuadros(trelloApi);
				
					
				//}
					
				for (String ID: projetos.keySet()) {
					if (nomeQuadro.equals(projetos.get(ID))){
						
						ObterID = ID;			
					}
					
				}
				return ObterID;
				
			}
		
		
		
		public static String getFilaID(Trello trelloApi, String nomeQuadro , String NomeFila) {
			
			String ObterID ="";	
			
			HashMap<String,String> filas = TrelloFilas.getFilasQuadro(trelloApi, nomeQuadro);

				
			for (String ID: filas.keySet()) {
				if (NomeFila.equals(filas.get(ID))){
					
					ObterID = ID;			
				}
				
			}
			return ObterID;
			
		}
		

}
