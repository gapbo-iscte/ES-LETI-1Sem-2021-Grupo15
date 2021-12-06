package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;
import java.util.List;

import org.trello4j.model.Action;
import org.trello4j.model.Card;
import org.trello4j.model.Member;



/**
 * @author Goncalo Benido
 *
 */
public class TrelloGitTempos {
	
	
	private static Double tempo_gasto = 0.0;
	private static Double numeroDeAtividades = 0.0;
	
	

	//Vai precisar de dar o ID do Quadro, vai devolver um HashMap coma chave o nome do membro, e Double[] com o Double[0] = numero de atividades ; Double[1] = total de horas por commit 
	public static HashMap<String,Double[]> getTempoPorCommitPorMembro(String IdDoQuadro){

		
		//Double numeroDeAtividades = 0.0;
				
		HashMap<String,Double[]> tempoPorCommitPorMembro = new HashMap<String,Double[]>();
		
		List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
		
		List<Member> membros = TrelloQuadros.trelloApi.getMembersByBoard(IdDoQuadro);
		
		
		for(Member membro: membros){
			tempo_gasto = 0.0;
			numeroDeAtividades = 0.0;
			
			
		for(org.trello4j.model.List fila: filas){
			
							
				List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
				
					
					
					
			
					for(Card carta: cartas){
						
						numeroDeAtividades++;
						
						System.out.println(numeroDeAtividades);
						
						
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
		
		
		Double[] dados = {numeroDeAtividades,tempo_gasto};
				
		tempoPorCommitPorMembro.put(membro.getUsername(),dados);
		
		System.out.println(membro.getUsername() + " --- " + "Numero de horas gastas:" + tempo_gasto);
		System.out.println(membro.getUsername() + " --- " + "Numero de atividades:" + numeroDeAtividades);
			
			
		}
		
		
		return tempoPorCommitPorMembro;  	
	}

	
	
	
	
	
	

}
