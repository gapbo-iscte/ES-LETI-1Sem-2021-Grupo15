package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;
import java.util.List;

import org.trello4j.model.Card;

public class TrelloDatas {
	
	
	/**
	 * @param IdDoQuadro - ID do quadro que pretende saber o o numero de Sprints existentes nesse quadro ({@link String})
	 * @param NomeDoSprint - NOME_DO_SPRINT = "S1", S1 representa Sprint 1 ({@link String})
	 * @return Devolve um String[] datas, onde datas[0] = data_de_inicio; datas[1] = data_de_fim; ({@link String[]})
	 */
	public static String[] getDataPorSprint(String IdDoQuadro, String NomeDoSprint ){
		
		String [] datas = new String[2];
		
		List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
		
		for(org.trello4j.model.List fila: filas){
			
			if(fila.getName().strip().toUpperCase().equalsIgnoreCase("Sprints")){
				
				
				List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
				
				for(Card carta: cartas){
					
					if(carta.getName().contains(NomeDoSprint)){
					
					
					String[] desc = carta.getDesc().split("\\[Start Timestamp\\]");
					
					//System.out.println(desc[0]);
					
					String[] data = desc[1].split("\\[End Timestamp\\]");
					
					datas = data;
					}
					
				}
				
				
			}
		}

		
			
		return datas;
	}
	
	
	
	/**
	 * @param IdDoQuadro - ID do quadro que pretende saber o o numero de Sprints existentes nesse quadro ({@link String})
	 * @return Devolve um  HashMap<String,String[]>, String o nome do Teste; datas, onde datas[0] = data_de_inicio; datas[1] = data_de_fim; ({@link HashMap<String,String[]>})
	 */
	public static HashMap<String,String[]> getDataTestes(String IdDoQuadro){
		
		HashMap<String,String[]> NomeDatas = new HashMap<String,String[]>();
		
		String nome = null;
		
			String [] datas = new String[2];
				
				List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
				
				System.out.println(filas.size());
				
				for(org.trello4j.model.List fila: filas){
				
						List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
						
						for(Card carta: cartas){
							
							if(carta.getName().toUpperCase().contains("(TESTES)") || carta.getName().toUpperCase().contains("(TESTE)") ){
								
								nome = carta.getName();
				
								String[] desc = carta.getDesc().split("\\[Start Timestamp\\]");
								
								String[] data = desc[1].split("\\[End Timestamp\\]");
								
								datas = data;			
								
								NomeDatas.put(nome,datas);
							}
						}
					}
				
				return NomeDatas;		
	}
	
	
	
	
	
	
	
	
	
	

}
