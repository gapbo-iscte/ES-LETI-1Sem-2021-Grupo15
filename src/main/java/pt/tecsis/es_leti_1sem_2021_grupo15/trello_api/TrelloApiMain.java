package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;


public class TrelloApiMain {
	
	
	private static final String trelloKey="coloca_a_tua_key";
	private static final String trelloAccessToken="coloca_o_teu_token";
	
	
	public static void main(String[] args) {
	     
	     Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
	   
	     
	    List<Board> boards = TrelloQuadros.getNomeDoProjeto(trelloApi);
	    List<TList> quadros = TrelloQuadros.getQuadros(trelloApi,boards.get(0));
	
	
	
	
	
	
	}
	
	
	
	
     
}


