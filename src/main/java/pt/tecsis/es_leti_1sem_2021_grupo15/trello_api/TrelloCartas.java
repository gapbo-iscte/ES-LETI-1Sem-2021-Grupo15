package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.Trello;
import org.trello4j.model.Card;
import org.trello4j.model.Card.Attachment;

public class TrelloCartas {
	

	//Tambem da título dos cartoes, Requerimento feito com os ID do Board (EX:Reuni�es, Daily Scrum, etc...)		
	public static List<Card> getTodasAsCartas(String NomeDoQuadro){
		
		
		String ProjectID= TrelloID.getQuadroID(NomeDoQuadro);
		
		List<Card> cartas = TrelloQuadros.trelloApi.getCardsByBoard(ProjectID);
		
		for (Card carta : cartas) {
            System.out.println(carta.getName());//+"- "+ cartas.getId()+"-"+cartas.getIdBoard());
		}
		
		return cartas;
		
		
	}
	
	
	
	
	private static List<Card> getCartasPorFila(String Fila, String NomeDoQuadro){
		
		String FilaID= TrelloID.getFilaID(TrelloQuadros.trelloApi, NomeDoQuadro,Fila);
		
		List<Card> cards = TrelloQuadros.trelloApi.getCardsByList(FilaID);
		
		return cards;
		
	}
	
	
	
	
	public static List<Card> getTitulosCartasPorFila(String Fila, String NomeDoQuadro){
				
			List<Card> checklists = getCartasPorFila(Fila, NomeDoQuadro);
			
			for (Card cartas : checklists) {
	            System.out.println(cartas.getName());//+"- "+ cartas.getId()+"-"+cartas.getIdBoard());
			}
			
			return checklists;
			
			
	}
	
	
	
	//Retorna o Título de cada Carta, e o conteudo de cada carta, requerimento feito com os nome da fila e o nome do Quadro  (EX:[R0] - Entendimento do projeto e configura��es iniciais -------- etc...)
	public static  List<Card> getCartasDescricaoPorFila(String Fila, String NomeDoQuadro){  
			
		
		List<Card> cards = getCartasPorFila(Fila, NomeDoQuadro);

		for (Card cartas : cards) {
            System.out.println(cartas.getName()+ "  -----------------  " + cartas.getDesc() );
		}
		
		return cards;
	     
	     
	}
	
	
	
	public static  List<Card> getCartasDescricaoPorQuadro(String NomeDaCarta, String NomeDoQuadro){  //, //Board projectName) {
			
		
		List<Card> cards = getTodasAsCartas(NomeDoQuadro);

		for (Card cartas : cards) {
			if(cartas.getName().equalsIgnoreCase(NomeDaCarta)){
            System.out.println(cartas.getName()+ "  -----------------  " + cartas.getDesc() );//+"-"+cartas.getIdBoard());
			}
		}
		
		return cards;
	     
	     
	}
	
	
	
	
	
	/*public static List<Attachment> getAttachmentPorCarta(Trello trelloApi, String Carta, String NomeDoQuadro){
		
		List<Card> cartas = getTituloDeTodasAsCartas(trelloApi,NomeDoQuadro);
		
		List<Attachment> attaches = null;
		
		for(Card carta : cartas){
			if(carta.getName() == Carta){
				
				attaches = trelloApi.getAttachmentsByCard(carta.getId());		
			}
		}
		
		for(Attachment attach : attaches){
			
			System.out.println(attach.getName());
			
		}
		
		return attaches;
		
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
