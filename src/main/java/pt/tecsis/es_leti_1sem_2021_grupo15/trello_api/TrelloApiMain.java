package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;


import org.trello4j.Trello;
import org.trello4j.TrelloImpl;


public class TrelloApiMain {
	
	public static Trello trelloApi = null;
	
	
	
		
	/**Usado para estabelecer a conexão ao TrelloApi
	 * @param trelloKey - Key do Utilizador ({@link String})
	 * @param trelloAccessToken - Token do utilizador ({@link String})
	 */
	public static void Inicializar(String trelloKey, String trelloAccessToken){
			
	trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);
			     
	}

	
	
	
     
}


