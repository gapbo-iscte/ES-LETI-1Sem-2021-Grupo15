package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.Trello;
import org.trello4j.model.Card;
import org.trello4j.model.Card.Attachment;

public class TrelloCartas {
	

	//Vai devolver uma lista com todas as caratas existentes num Quadro (EX:Reuni�es, Daily Scrum, etc...)		
	public List<Card> getTodasAsCartas(String IdDoQuadro){
		
		
		List<Card> cartas = TrelloQuadros.trelloApi.getCardsByBoard(IdDoQuadro);
		
		for (Card carta : cartas) {
            System.out.println(carta.getName());
		}
		
		return cartas;
		
		
	}
	
	//Devolve a decrições da carta sabendo o título da carta
	public String getCartasDescricaoPorQuadro(String NomeDaCarta, String idQuadro){  //, //Board projectName) {
		
		List<Card> cartas = getTodasAsCartas(idQuadro);
		
		String desc = null;
		
		for (Card carta : cartas) {
			if(carta.getName().equalsIgnoreCase(NomeDaCarta)){
				System.out.println(carta.getName()+ "  -----------------  " + carta.getDesc() );//+"-"+cartas.getIdBoard());
				
				desc = carta.getDesc();
			}	
		}
		
		return desc;
		
	}
	
	
	
	
	//Devolve as cartas existetes numa fila (Uma fila é considerada um conjunto de cartas restritas a um bloco)
	public static List<Card> getCartasPorFila(String idFila, String IdDoQuadro){
		
		//String FilaID= TrelloID.getFilaID(TrelloQuadros.trelloApi, IdDoQuadro,Fila);
		
		List<Card> cards = TrelloQuadros.trelloApi.getCardsByList(idFila);
		
		return cards;
		
	}
	
	
	
	//Devolve os títulos de todas as cartas se encontrão numa fila especificada
	public String[] getCartasTítuloPorFila(String Fila, String IdDoQuadro){
		
				
			List<Card> cartasPorFila = getCartasPorFila(Fila, IdDoQuadro);
			
			String[] titulos = new String[cartasPorFila.size()];
			
			int i = 0;
			
			for (Card carta : cartasPorFila) {
	            System.out.println(carta.getName());//+"- "+ cartas.getId()+"-"+cartas.getIdBoard());
	            titulos[i]=carta.getName();
	            i++;
			}
			
			
			
			return titulos;
			
			
	}
	
	
	
	//Retorna o conteudo de cada carta, feito com o id da Fila e o id do Quadro  (EX:[R0] - Entendimento do projeto e configura��es iniciais -------- etc...)
	public String[] getCartasDescricaoPorFila(String idFila, String idQuadro){  
			
		
		List<Card> cartas = getCartasPorFila(idFila, idQuadro);
		
		String[] desc = new String[cartas.size()];
		
		int i = 0;

		for (Card carta : cartas) {
            System.out.println(carta.getName()+ "  -----------------  " + carta.getDesc() );
            desc[i]=carta.getDesc();
		}
		
		return desc;
	     
	     
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
