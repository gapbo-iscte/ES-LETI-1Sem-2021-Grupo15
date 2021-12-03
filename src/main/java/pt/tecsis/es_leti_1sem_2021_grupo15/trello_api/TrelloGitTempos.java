package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;
import java.util.List;

import org.trello4j.model.Action;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

public class TrelloGitTempos {
	
	
	private static Double tempo_gasto = 0.0;
	
	public static HashMap<String,Double> getTempoPorCommitPorMembro(String IdDoQuadro){
		
				
		HashMap<String,Double> tempoPorCommitPorMembro = new HashMap<String,Double>();
		
		List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
		
		List<Member> membros = TrelloQuadros.trelloApi.getMembersByBoard(IdDoQuadro);
		
		
		for(Member membro: membros){
			tempo_gasto = 0.0;
		for(org.trello4j.model.List fila: filas){
			
							
				List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
				
					
					
					
			
					for(Card carta: cartas){
						
						String desc = carta.getDesc().strip().toUpperCase();
						
						if(desc.contains("COMMIT")){
						
						
									
							List<Action> acoes_carta = TrelloQuadros.trelloApi.getActionsByCard(carta.getId());
					
								for(Action acao: acoes_carta){
						
						
									if(membro.getUsername().equalsIgnoreCase(acao.getMemberCreator().getUsername())){
										
										String horas = acao.getData().getText();
							
							
										if( horas != null){
											String[] parts = horas.split(" ");
											if(parts[0].equalsIgnoreCase("plus!")){
									
												System.out.println(horas);
									
												String[] part = parts[1].split("/");
									
									
										tempo_gasto = Double.parseDouble(part[0]) + tempo_gasto;
										
											}
										}
									}
								}
						}
							
					
				
					}					
					
				}
				
		tempoPorCommitPorMembro.put(membro.getUsername(),tempo_gasto);
		
		System.out.println(membro.getUsername() + " --- " + "Numero de horas gastas:" + tempo_gasto);
			
			
		}
		
		
		return tempoPorCommitPorMembro;  	
	}

	
	
	
	
	
	

}
