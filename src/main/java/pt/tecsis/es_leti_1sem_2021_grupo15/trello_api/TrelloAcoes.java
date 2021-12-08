package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;


import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.trello4j.model.Action;
import org.trello4j.model.Card;
import org.trello4j.model.Member;



/**
 * @author Goncalo Benido
 *
 */
public class TrelloAcoes {
	
	private static Double tempo_gasto = 0.0;
	private static Double tempo_previsto = 0.0;
	private static Double tempo_restante = 0.0;
		
	
	

	/**
	 * @param IdDoQuadro - ID do quadro onde pretente procurar os comentarios ({@link String})
	 * @return Devolve uma lista do objecto Action que contem os comentarios de cada carta do quadro ({@link List<Action>})
	 */
	public List<Action> getAcoesPorQuadro(String IdDoQuadro ){
		
		List<Action> actions = TrelloApiMain.trelloApi.getActionsByBoard(IdDoQuadro);
		
		for (Action action : actions) {
            System.out.println(action.getMemberCreator().getUsername()+"-"+action.getData().getText() + action.getDate());
		}
		
		
		return actions;  	
	}
	


	/**
	 * @param IdDoQuadro - ID do quadro que pertente obter o numero de horas de trabalho ({@link String})
	 * @return Este metodo retorna um array tempos[] onde tempos[0] = tempo_gasto; tempos[1]=tempo_previsto; tempos[2]=tempo_restante; ({@link Double[]})
	 */
	public static Double[] getTempoPorQuadro(String IdDoQuadro ){
		
		HashMap<String,Double[]> tempoPorSprintPorMembro = getTempoPorMembro(IdDoQuadro);
			
		tempo_gasto = 0.0;
		tempo_previsto = 0.0;
		tempo_restante = 0.0;
			
		for(Entry<String,Double[]> entry: tempoPorSprintPorMembro.entrySet()){
									
				tempo_gasto = tempo_gasto + entry.getValue()[0];
				
				tempo_previsto = tempo_previsto + entry.getValue()[1];
				
				tempo_restante = tempo_restante + entry.getValue()[2];

		}
			
		Double[] tempos = {tempo_gasto, tempo_previsto, tempo_restante};	
				
		return tempos;	
			
			
	}
		
	

	/**
	 * @param IdDoQuadro - ID do quadro que pertente obter os tempo por membro ({@link String})
	 * @return Metodo retorna HashMap<String,Double[]>, o double[0]=tempo_gasto_por_membro; double[1]=tempo_previsto_por_membro: double[2]=tempo_restante_por_membro ({@link HashMap<String,Double[]>})
	 */
	public static HashMap<String,Double[]> getTempoPorMembro(String IdDoQuadro ){
		
		HashMap<String,Double[]> tempoPorMembro = new HashMap<String,Double[]>();
		
		List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
		
		List<Member> membros = TrelloApiMain.trelloApi.getMembersByBoard(IdDoQuadro);
		
		
		for(Member membro: membros){
					
			tempo_gasto = 0.0;
			tempo_previsto = 0.0;
			tempo_restante = 0.0;
			
			for(org.trello4j.model.List fila: filas){
				List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
			
	
				for(Card carta: cartas){
								
					List<Action> acoes_carta = TrelloApiMain.trelloApi.getActionsByCard(carta.getId());
					
						for(Action acao: acoes_carta){
							
							
							if(membro.getUsername().equalsIgnoreCase(acao.getMemberCreator().getUsername())){
								
								String horas = acao.getData().getText();
									
									
									if( horas != null){
										String[] parts = horas.split(" ");
										if(parts[0].equalsIgnoreCase("plus!")){
											
											System.out.println(horas);
											
											String[] part = parts[1].split("/");
											
											
											tempo_gasto = Double.parseDouble(part[0]) + tempo_gasto;
											tempo_previsto = Double.parseDouble(part[1]) + tempo_previsto;
											
											
										}
										
									}
									
							
								}
		
							}
					}
						
				
			
				}
			
			Double[] tempos = {tempo_gasto, tempo_previsto, tempo_restante};
			
			tempoPorMembro.put(membro.getUsername(),tempos);
			
			System.out.println(membro.getUsername() + " --- " + "Numero de horas gastas:" + tempo_gasto);
			System.out.println(membro.getUsername() + " --- " + "Numero de horas previstas:" + tempo_previsto);
			
			}
		
		return tempoPorMembro;  	
	}

	
	
	

	/**
	 * @param IdDoQuadro - ID do quadro que pretende saber o numero de sprints que existem ({@link String})
	 * @return Devolve o número de Sprints no quadro ({@link Integer})
	 */
	public static int getNumeroDeSprints(String IdDoQuadro){
		
		int numeroDeSprints = 0;
		
		List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
		
		System.out.println(filas.size());
		
		for(org.trello4j.model.List fila: filas){
			
			if(fila.getName().contains("[S")){
				
				numeroDeSprints++;
				
			}
			
		}
		
		return numeroDeSprints;
		
		
	}
	

	
	/**
	 * @param IdDoQuadro - ID do quadro que pretende saber o tempo por Sprint ({@link String})
	 * @return Vai devolver um HashMap<Strin,Double[]> sendo a String o nome de cada Sprint, Double[0]=tempo_gasto_por_Sprint; Double[1]=tempo_previsto_por_sprint; Double[2]=tempo_restante_por_Sprint ({@link HashMap<String,Double[]>})
	 */
	public static HashMap<String,Double[]> getTempoPorSprint(String IdDoQuadro){
		
		int numeroDeSprints = getNumeroDeSprints(IdDoQuadro);
		
		System.out.println(numeroDeSprints);
		
		
		HashMap<String,Double[]> tempoPorSprint = new HashMap<String,Double[]>();
		
		
		for(int i = 1; i != numeroDeSprints+1; i++){
		
		String SprintName = "[" + i + "]" + "Sprint Backlog"; 
		
		String nomeDoSprint = 'S' + String.valueOf(i); 
		
		HashMap<String,Double[]> tempoPorSprintPorMembro = getTempoPorSprintPorMembro(IdDoQuadro, nomeDoSprint );
			
			
		tempo_gasto = 0.0;
		tempo_previsto = 0.0;
		tempo_restante = 0.0;
			
		for(Entry<String,Double[]> entry: tempoPorSprintPorMembro.entrySet()){
									
				tempo_gasto = tempo_gasto + entry.getValue()[0];
				
				tempo_previsto = tempo_previsto + entry.getValue()[1];
			
				tempo_restante = tempo_restante + entry.getValue()[2];

				
		}
			
		Double[] tempos = {tempo_gasto, tempo_previsto, tempo_restante};	
			
		tempoPorSprint.put(SprintName,tempos);	
		
		}
			
			
		return tempoPorSprint;	
			
			
	}
			

	
		/**
		 * @param IdDoQuadro - ID do quadro que pretende saber o tempo por Sprint po Membro ({@link String})
		 * @param NomeDoSprint - NOME_DO_SPRINT = "S1", S1 representa Sprint 1 ({@link String}) 
		 * @return Vai devolver um HashMap<Strin,Double[]> sendo a String o nome de cada Membro, Double[0]=tempo_gasto_por_Sprint; Double[1]=tempo_previsto_por_sprint; Double[2]=tempo_restante_por_Sprint ({@link HashMap<String,Double[]>})
		 */
	
		public static HashMap<String,Double[]> getTempoPorSprintPorMembro(String IdDoQuadro, String NomeDoSprint ){
				
			HashMap<String,Double[]> tempoPorSprintPorMembro = new HashMap<String,Double[]>();
				
				List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
				
				List<Member> membros = TrelloApiMain.trelloApi.getMembersByBoard(IdDoQuadro);
				
				
				for(org.trello4j.model.List fila: filas){
					
					if(fila.getName().contains('[' + NomeDoSprint + ']')){
						
						
						List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
					
						for(Member membro: membros){
							
							tempo_gasto = 0.0;
							tempo_previsto = 0.0;
							tempo_restante = 0.0;
					
							for(Card carta: cartas){
								
								
											
								List<Action> acoes_carta = TrelloApiMain.trelloApi.getActionsByCard(carta.getId());
								
									for(Action acao: acoes_carta){
										
										
										if(membro.getUsername().equalsIgnoreCase(acao.getMemberCreator().getUsername())){
											
											String horas = acao.getData().getText();
											
											
											if( horas != null){
												String[] parts = horas.split(" ");
												if(parts[0].equalsIgnoreCase("plus!")){
													
													System.out.println(horas);
													
													String[] part = parts[1].split("/");
													
													
													tempo_gasto = Double.parseDouble(part[0]) + tempo_gasto;
													tempo_previsto = Double.parseDouble(part[1]) + tempo_previsto;
													tempo_restante = tempo_previsto - tempo_gasto;
													
													
												}
												
											}
										}
									}
									
							
						
							}
							
							Double[] tempos = {tempo_gasto, tempo_previsto, tempo_restante};
							tempoPorSprintPorMembro.put(membro.getUsername(),tempos);
							
							System.out.println(membro.getUsername() + " --- " + "Numero de horas gastas:" + tempo_gasto);
							System.out.println(membro.getUsername() + " --- " + "Numero de horas previstas:" + tempo_previsto);
						}
						
					
					}
				}
				
				
				return tempoPorSprintPorMembro;  	
			}
		
		
	
}