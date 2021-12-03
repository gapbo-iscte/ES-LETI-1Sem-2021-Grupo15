package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.model.Checklist;

public class TrelloChecklists {
	
	
	public List<Checklist> getCheckList(String NomeDoQuadro){
		
		String QuadroID= TrelloID.getQuadroID(NomeDoQuadro);
		
		
		List<Checklist> checks = TrelloQuadros.trelloApi.getChecklistByBoard(QuadroID);
		
		

		for (Checklist check : checks) {
            System.out.println(check.getName());
		}
		
		
		
		
		return checks;
		
		
	}
	

}
