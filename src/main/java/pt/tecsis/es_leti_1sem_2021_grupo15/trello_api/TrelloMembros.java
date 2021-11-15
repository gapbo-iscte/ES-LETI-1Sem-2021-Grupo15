package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.Trello;
import org.trello4j.model.Member;

public class TrelloMembros {
	
	
	
	public static List<Member> getMemberDoQuadro(Trello trelloApi, String NomeDoQuadro) {
		
		String QuadroID = TrelloID.getQuadroID(trelloApi,NomeDoQuadro);
		
		List<Member> membros = trelloApi.getMembersByBoard(QuadroID);
		
		for(Member membro : membros){
			
			System.out.println(membro.getFullName() + "  -  " + membro.getId());
		}
		
		return membros;
		
	}

}
