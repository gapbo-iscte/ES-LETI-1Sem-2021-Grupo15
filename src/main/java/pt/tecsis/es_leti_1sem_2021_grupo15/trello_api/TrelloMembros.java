package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.model.Member;


/**
 * @author Goncalo Benido
 *
 */
public class TrelloMembros {
	

	/**
	 * @param IdDoQuadro - ID do quadro que está a usar ({@link String})
	 * @return Devolve uma lista do tipo Member onde conte'm todos os membros existentes em um quadro ({@link List<Member>})
	 */
	public static List<Member> getMembrosDoQuadro(String IdDoQuadro) {
		
		List<Member> membros = TrelloApiMain.trelloApi.getMembersByBoard(IdDoQuadro);
		
		for(Member membro : membros){
			
			System.out.println(membro.getFullName() + "  -  " + membro.getId());
		}
		
		return membros;
		
	}
	
	
	
	/**
	 * @param trelloAccessToken - Token do utilizador ({@link String})
	 * @return Devolve o utilizador a que o token pertence({@link Member})
	 */
	public static Member getMembroDoQuadro(String trelloAccessToken) {
		
		Member user = TrelloApiMain.trelloApi.getMemberByToken(trelloAccessToken);
		
	return user;
	}

}
