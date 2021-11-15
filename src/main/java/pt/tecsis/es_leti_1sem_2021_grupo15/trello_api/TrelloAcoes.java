package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.Trello;
import org.trello4j.model.Action;

public class TrelloAcoes {
		
	
	public static  List<Action> getAcoesPorQuadro(Trello trelloApi, String NomeDoQuadro ){
			
		String QuadroID = TrelloID.getQuadroID(trelloApi,NomeDoQuadro);
		
		List<Action> actions = trelloApi.getActionsByBoard(QuadroID);
		
		for (Action action : actions) {
            System.out.println(action.getMemberCreator().getUsername()+"-"+action.getData().getText() + action.getDate());
		}
		
		
		return actions;  	
	}
	

}
