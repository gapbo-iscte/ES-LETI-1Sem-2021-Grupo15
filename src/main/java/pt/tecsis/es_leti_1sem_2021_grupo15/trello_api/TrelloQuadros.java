package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Board;



/**
 * @author Goncalo Benido
 *
 */
public class TrelloQuadros {
	
	
	public static List<Board> quadros= null;
	public static Trello trelloApi = null;
	
	

	/**Usado para estabelecer a conexão ao TrelloApi
	 * @param trelloKey - Key do Utilizador ({@link String})
	 * @param trelloAccessToken - Token do utilizador ({@link String})
	 */
	public static void Inicializar(String trelloKey, String trelloAccessToken) {
			
			trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);
		     
			
	}
	
	
	

	/**
	 * 
	 * @param NomeDoMembro - nome do utilizador ({@link String})
	 * @return Devolve todos os quadros existentes na conta do utilizador ({@link List<Board>})
	 */
	public static List<Board> BuscarQuadros(String NomeDoMembro) {
		
		quadros = trelloApi.getBoardsByMember(NomeDoMembro);
		
		return quadros;
	}
	
	
	
	
	
	
	

}
