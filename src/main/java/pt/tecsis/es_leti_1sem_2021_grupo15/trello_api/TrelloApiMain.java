package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;


public class TrelloApiMain {
	
	 String trelloKey="Key_utilizador";
     String trelloAccessToken="Token_utilizador";
     Trello trelloApi = new TrelloImpl(Key_utilizado, Token_utilizador, new ApacheHttpClient());
   
     Board board;
     List<Board> member=trelloApi.getMemberBoards("nome_utilizador");{
     //podem consultar o vosso nome_utilizador na opção "profile and visibility" da vossa conta no trello
     for (Board  quadro: member) {
         System.out.println(quadro.getName()+ "-" +quadro.getId());
         board = trelloApi.getBoard(quadro.getId());
         List<TList> lists = board.fetchLists();
         for (TList lista : lists) {
             System.out.println(lista.getName()+"- "+ lista.getId()+"-"+lista.getIdBoard());
         }
     }
     
     }
}
