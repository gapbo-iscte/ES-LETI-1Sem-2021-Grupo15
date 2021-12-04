package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;


import java.util.HashMap;
import java.util.List;

import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;



public class TrelloQuadros {
	
	
	
	//public static HashMap<String,String> projetos = new HashMap<String,String>();
	
	public static List<Board> quadros= null;
	public static Trello trelloApi = null;
	
	
	//INICIALIZAR O TRELLO
	public static void Inicializar(String trelloKey, String trelloAccessToken) {
			
			trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);
		     
			
	}
	

	
	//Método de inicialização da conexão das credencias do utilizador com a API trello4j, vai devolver todos os quadros existentes na conta do utilizador (EX:Projeto_ES)
	public static List<Board> BuscarQuadros(String NomeDoMembro) {
		
		quadros = trelloApi.getBoardsByMember(NomeDoMembro);
		
		return quadros;
	}
	
	
	
	
	
	
	

}
