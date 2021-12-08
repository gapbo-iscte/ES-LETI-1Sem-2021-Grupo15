package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.model.Board;



/**
 * @author Goncalo Benido
 *
 */
public class TrelloQuadros {
	
	
	public static List<Board> quadros= null;

	

	/**
	 * 
	 * @param NomeDoMembro - nome do utilizador ({@link String})
	 * @return Devolve todos os quadros existentes na conta do utilizador ({@link List<Board>})
	 */
	public static List<Board> BuscarQuadros(String NomeDoMembro) {
		
		quadros = TrelloApiMain.trelloApi.getBoardsByMember(NomeDoMembro);
		
		return quadros;
	}
	
	
	
	
	
	
	

}
