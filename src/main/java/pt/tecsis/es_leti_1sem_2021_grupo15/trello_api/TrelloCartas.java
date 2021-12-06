package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.model.Card;





/**
 * @author Gonçalo Benido
 *
 */
public class TrelloCartas {
	

	/**
	 * Vai devolver uma lista com todas as caratas existentes num Quadro (EX:Reunioes, Daily Scrum, etc...)
	 * @param IdDoQuadro - ID do quadro onde se encontram as cartas {@link String}
	 * @return {@link List<Card>}
	 */
	public static List<Card> getTodasAsCartas(String IdDoQuadro){
		
		
		List<Card> cartas = TrelloQuadros.trelloApi.getCardsByBoard(IdDoQuadro);
		
		for (Card carta : cartas) {
            System.out.println(carta.getName());
		}
		
		return cartas;
		
		
	}
	

	/**
	 * Devolve a decricoes da carta sabendo o titulo da carta
	 * @param NomeDaCarta - nome da carta que procura {@link String}
	 * @param idQuadro - ID do quadro onde se encontram as cartas {@link String}
	 * @return {@link String}
	 */
	public static String getCartasDescricaoPorQuadro(String NomeDaCarta, String idQuadro){  //, //Board projectName) {
		
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
	
	
	
	
	/**
	 * Devolve as cartas existetes numa fila (Uma fila e' considerada um conjunto de cartas restritas de um bloco)
	 * @param idFila - ID da fila onde estão as cartas que procura {@link String}
	 * @param IdDoQuadro - ID do quadro onde estão as cartas {@link String}
	 * @return Lista com as cartas {@link List<Card>}
	 */
	public static List<Card> getCartasPorFila(String idFila, String IdDoQuadro){
		
		//String FilaID= TrelloID.getFilaID(TrelloQuadros.trelloApi, IdDoQuadro,Fila);
		
		List<Card> cards = TrelloQuadros.trelloApi.getCardsByList(idFila);
		
		return cards;
		
	}
	
	
	

	/**
	 * Devolve os titulos de todas as cartas que se encontrao numa fila especificada
	 * @param  idFila - ID da fila onde estão as cartas que procura {@link String}
	 * @param IdDoQuadro - ID do quadro onde estão as cartas {@link String}
	 * @return {@link String[]}
	 */
	public static String[] getCartasTítuloPorFila(String idFila, String IdDoQuadro){
		
				
			List<Card> cartasPorFila = getCartasPorFila(idFila, IdDoQuadro);
			
			String[] titulos = new String[cartasPorFila.size()];
			
			int i = 0;
			
			for (Card carta : cartasPorFila) {
	            System.out.println(carta.getName());
	            titulos[i]=carta.getName();
	            i++;
			}
			
			
			
			return titulos;
			
			
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
