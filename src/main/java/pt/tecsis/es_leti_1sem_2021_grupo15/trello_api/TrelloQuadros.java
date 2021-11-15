package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;


import java.util.HashMap;
import java.util.List;

import org.trello4j.Trello;
import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;



public class TrelloQuadros {
	
	
	
	//public static HashMap<String,String> projetos = new HashMap<String,String>();
	
	
	
	
	//Requerimento feito com os dados da conta do utilizador (EX:Projeto_ES)
	public static HashMap<String,String> getNomeDoQuadros(Trello trelloApi) {
		
		
		List<Board> boards = trelloApi.getBoardsByMember("goncalobenido");
		
		HashMap<String,String> projetos = new HashMap<String,String>();
	     
		for (Board  quadro: boards) {
		  projetos.put(quadro.getId(),quadro.getName());
	    
			System.out.println(quadro.getName() + "-" + quadro.getId() );
			
		}
		
		return projetos;
	}
	
	

	
	
	
	
	
	
	/*public static String getQuadroID(Trello trelloApi,String NomeDoQuadro) {
		
		
		String ProjectID ="";
		
		HashMap<String,String> projetos= getNomeDoQuadros(trelloApi);
		
		for (String ID: projetos.keySet()) {
			if (NomeDoQuadro.equals(projetos.get(ID))){
				
				ProjectID = ID;
				
				
			}
			
		}
		return ProjectID;
		
	}*/
	
	
	

}
