package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.model.Member;

public class TrelloMembros {
	
	
	//Devolve uma lista do tipo Member onde contém todos os membros existentes em um quadro
	public static List<Member> getMembrosDoQuadro(String IdDoQuadro) {
		
		//String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
		
		List<Member> membros = TrelloQuadros.trelloApi.getMembersByBoard(IdDoQuadro);
		
		for(Member membro : membros){
			
			System.out.println(membro.getFullName() + "  -  " + membro.getId());
		}
		
		return membros;
		
	}
	
	
	
	
	public static Member getMembroDoQuadro(String trelloAccessToken) {
		
		Member user = TrelloQuadros.trelloApi.getMemberByToken(trelloAccessToken);
		
	return user;
	}

}
