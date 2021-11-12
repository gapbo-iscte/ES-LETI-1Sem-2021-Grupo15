package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;


import java.util.List;

import org.trello4j.Trello;
import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;



public class TrelloQuadros {
	
	//Requerimento é feito com os dados da conta do utilizador (EX:Projeto_ES)
	public static List<Board> getNomeDoProjeto(Trello trelloApi) {
		
		//List<Board> member = trelloApi.//getMemberBoards("utilizador_trello");{
	     /*for (Board  quadro: member) {
		  System.out.println(quadro.getName()+ "-" +quadro.getId());
	    }*/
		List<Board> boards = trelloApi.getBoardsByMember("goncalobenido");
			System.out.println(boards.get(0).getName() + "-" + boards.get(0).getId() );
			
		//}
		
		return boards;
	}
	
	
	//Requerimento é feito com os ID do Board (EX:Reuniões, Daily Scrum, etc...)
	public static List<org.trello4j.model.List> getList(Trello trelloApi){//, //Board projectName) {
		
		//Board board;
		/*List<TList> lists = projectName.fetchLists();
	     
	     for (Board  quadro: getNomeDoProjeto(trelloApi) ) {
	         projectName = trelloApi.getBoard(quadro.getId());
	         for (TList lista : lists) {
	             System.out.println(lista.getName()+"- "+ lista.getId()+"-"+lista.getIdBoard());
	            // System.out.println(board.getName()+"- "+ board.getId()+"-"+board.getIdBoard());
	         }
	     
	     }*/
		
		List<Board> board= getNomeDoProjeto(trelloApi);
		
		List<org.trello4j.model.List> checklists = trelloApi. getListByBoard(board.get(0).getId());
		
		/*for (org.trello4j.model.List cartas : checklists) {
            System.out.println(cartas.getName());//+"- "+ cartas.getId()+"-"+cartas.getIdBoard());
		}*/
		
		return checklists;
		
		
	}
	
	
	
	//Requerimento é feito com os ID da Lista (EX:[R0] - Entendimento do projeto e configurações iniciais)
	public static  List<Card> getQuadros(Trello trelloApi){  //, //Board projectName) {
			
		//Board board;
		/*List<TList> lists = projectName.fetchLists();
	     
	     for (Board  quadro: getNomeDoProjeto(trelloApi) ) {
	         projectName = trelloApi.getBoard(quadro.getId());
	         for (TList lista : lists) {
	             System.out.println(lista.getName()+"- "+ lista.getId()+"-"+lista.getIdBoard());
	            // System.out.println(board.getName()+"- "+ board.getId()+"-"+board.getIdBoard());
	         }
	     
	     }*/
		
		 List<org.trello4j.model.List> lista = getList(trelloApi);
		
		List<Card> cards = trelloApi.getCardsByList(lista.get(0).getId());

		for (Card cartas : cards) {
            System.out.println(cartas.getName()+"- "+ cartas.getId());//+"-"+cartas.getIdBoard());
		}
		
		return cards;
	     
	     
	}
	
	
	
	public static  List<Action> getActions(Trello trelloApi, String cartaId ){
			
		
		
		List<Action> actions = trelloApi.getActionsByCard(cartaId);
		
		for (Action action : actions) {
            System.out.println(action.getMemberCreator().getUsername()+"-"+action.getData().getText());
		}
		
		
		return actions;  	
	}
	
	
	
	

}
