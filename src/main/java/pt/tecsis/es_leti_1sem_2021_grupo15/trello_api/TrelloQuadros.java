package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.apache.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;


public class TrelloQuadros {
	
	
	public static List<Board> getNomeDoProjeto(Trello trelloApi) {
		
		List<Board> member = trelloApi.getMemberBoards("utilizador_trello");{
	     /*for (Board  quadro: member) {
		  System.out.println(quadro.getName()+ "-" +quadro.getId());
	    }*/
		
			System.out.println(member.get(0).getName()+ "-" + member.get(0).getId());
			
		}
		
		return member;
	}
	
	
	
	public static List<TList> getQuadros(Trello trelloApi, Board projectName) {
			
		//Board board;
		List<TList> lists = projectName.fetchLists();
	     
	     for (Board  quadro: getNomeDoProjeto(trelloApi) ) {
	         projectName = trelloApi.getBoard(quadro.getId());
	         for (TList lista : lists) {
	             System.out.println(lista.getName()+"- "+ lista.getId()+"-"+lista.getIdBoard());
	            // System.out.println(board.getName()+"- "+ board.getId()+"-"+board.getIdBoard());
	         }
	     
	     }
		return lists;
	     
	     
	    
		
		
	}
	
	
	
	

}
