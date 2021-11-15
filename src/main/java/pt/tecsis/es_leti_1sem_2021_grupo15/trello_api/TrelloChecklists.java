package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;
import java.util.List;

import org.trello4j.Trello;
import org.trello4j.model.Card;
import org.trello4j.model.Checklist;

public class TrelloChecklists {
	
	
	public static List<Checklist> getCheckList(Trello trelloApi, String NomeDoQuadro){
		
		String QuadroID= TrelloID.getQuadroID(trelloApi, NomeDoQuadro);
		
		
		List<Checklist> checks = trelloApi.getChecklistByBoard(QuadroID);
		
		

		for (Checklist check : checks) {
            System.out.println(check.getName());
		}
		
		
		
		
		return checks;
		
		
	}
	

}
