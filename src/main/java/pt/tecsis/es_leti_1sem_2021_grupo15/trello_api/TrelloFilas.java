package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.Trello;
import org.trello4j.model.Card;



/**
 * @author Goncalo Benido
 *
 */
public class TrelloFilas {
	

	/**
	 * 
	 * @param IdDoQuadro - ID do quadro que pretende usar ({@link String})
	 * @return Devolve todas as filas existentes em um quadro ({@link List<org.trello4j.model.List>})
	 */
	public static List<org.trello4j.model.List> getFilasQuadro(String IdDoQuadro){

			//String ProjectID= TrelloID.getQuadroID(NomeDoQuadro);

			List<org.trello4j.model.List> filasQuadro = TrelloQuadros.trelloApi.getListByBoard(IdDoQuadro);
	
			for (org.trello4j.model.List  fila: filasQuadro) {	
				System.out.println(fila.getName() + "-" + fila.getId() );	
			}
			
			return filasQuadro;
						
		}
	
	
	
	//Retorna o conteudo de cada carta, feito com o nome do Sprint (EX: S1, S2 , S3, etc..) e o ID do Quadro, devolve uma String com o Conteudo das cartas da fila do Sprint Backlog correspondente
		public static String[] getCartasDescricaoPorSprint(String idQuadro, String NomeDoSprint){  
			
			List<org.trello4j.model.List> filas = getFilasQuadro(idQuadro);
			
			String[] desc = null;
			
			
			for(org.trello4j.model.List fila: filas){
				
				if(fila.getName().contains('[' + NomeDoSprint + ']')){
				
			
					List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), idQuadro);
					
			
						desc = new String[cartas.size()];
			
						int i = 0;

							for (Card carta : cartas) {
					            desc[i]=carta.getName();
					            i++;
							}
							break;
				}
			}
			
			return desc;
		     
		     
		}
		
		
		
		//Método que retorna um String[] onde String[0]= Sprint Planning ; Sprint[1]= Sprint Review; Sprint[2]= Sprint Retrospective
		
		public static String[] textosPorSprint(String idDoQuadro, String NomeDoSprint) {
			
			String planning = null;
			String review = null;
			String retrospective = null;
			
			
			List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(idDoQuadro);
			
			System.out.println(filas.size());
			
			for(org.trello4j.model.List fila: filas){
				
				if(fila.getName().toUpperCase().contains("SPRINT PLANNING") || fila.getName().toUpperCase().contains("SPRINT REVIEW") || fila.getName().toUpperCase().contains("SPRINT RETROSPECTIVE") ){
					
					
					List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), idDoQuadro);
					
					for(Card carta: cartas){
						
						
						if(carta.getName().contains('[' + NomeDoSprint + ']')){
							
							if(fila.getName().toUpperCase().contains("SPRINT PLANNING") ){
							
							planning = carta.getDesc();
							
							break;
						
							
						}else if(fila.getName().toUpperCase().contains("SPRINT REVIEW") ){
								
								review = carta.getDesc();
								
								break;
								
								
							}else if(fila.getName().toUpperCase().contains("SPRINT RETROSPECTIVE") ){
									
								retrospective = carta.getDesc();
								
								break;
									
								}
							}
					}
				}
						
			}
			
			String[] textos = {planning, review,retrospective};
			
			
			return textos;  	
		}
		



	
	
	
	
	

}
