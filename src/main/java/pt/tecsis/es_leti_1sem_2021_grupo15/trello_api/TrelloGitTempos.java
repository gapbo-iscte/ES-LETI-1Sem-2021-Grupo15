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
	private static Double numeroDeAtividadesComCommit = 0.0;
	private static Double numeroTotalDeAtividades = 0.0;
	
	

	//Vai precisar de dar o ID do Quadro, vai devolver um HashMap coma chave o nome do membro, e Double[] com o Double[0] = numero de atividades ; Double[1] = total de horas por commit; Double[2] = numero_de_atividades_sem_commit; 
	public static HashMap<String,Double[]> getTempoPorCommitPorMembro(String IdDoQuadro){

		
		//Double numeroDeAtividades = 0.0;
		
		boolean participouNoCommit = true;
				
		HashMap<String,Double[]> tempoPorCommitPorMembro = new HashMap<String,Double[]>();
		
		List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
		
		List<Member> membros = TrelloApiMain.trelloApi.getMembersByBoard(IdDoQuadro);
		
		
		for(Member membro: membros){
			tempo_gasto = 0.0;
			numeroDeAtividadesComCommit = 0.0;
			numeroTotalDeAtividades = 0.0;
			
		for(org.trello4j.model.List fila: filas){
			
							
				List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);

					for(Card carta: cartas){
						
						
						
						System.out.println(numeroDeAtividadesComCommit);
						
						
						String desc = carta.getDesc().strip().toUpperCase();
						
						if(desc.contains("COMMIT")){
							
							participouNoCommit = true; 
							
							List<Action> acoes_carta = TrelloApiMain.trelloApi.getActionsByCard(carta.getId());
					
								for(Action acao: acoes_carta){
									
						
									if(membro.getUsername().equalsIgnoreCase(acao.getMemberCreator().getUsername())){
										
										if(participouNoCommit){
											numeroDeAtividadesComCommit++;
											participouNoCommit = false;
										}
										
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
						
						if(carta.getIdMembers().contains(membro.getId())){
							
						numeroTotalDeAtividades++;
				
					}					
					
				}
		}
		
		
		Double[] dados = {numeroDeAtividadesComCommit,tempo_gasto, numeroTotalDeAtividades - numeroDeAtividadesComCommit};
				
		tempoPorCommitPorMembro.put(membro.getUsername(),dados);
		
		System.out.println(membro.getUsername() + " --- " + "Numero de horas gastas:" + tempo_gasto);
		System.out.println(membro.getUsername() + " --- " + "Numero de atividades com commit:" + numeroDeAtividadesComCommit);
		System.out.println(membro.getUsername() + " --- " + "Numero de atividades sem commit:" + (numeroTotalDeAtividades - numeroDeAtividadesComCommit));

			
			
		}
		
		
		return tempoPorCommitPorMembro;  	
	}

	
	
	
	
	
	

}
