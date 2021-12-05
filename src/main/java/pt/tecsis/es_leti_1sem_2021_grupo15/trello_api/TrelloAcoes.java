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
		
		List<Action> actions = TrelloQuadros.trelloApi.getActionsByBoard(IdDoQuadro);
		
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
		
		List<Member> membros = TrelloQuadros.trelloApi.getMembersByBoard(IdDoQuadro);
		
		
		for(Member membro: membros){
			
			
			
			tempo_gasto = 0.0;
			tempo_previsto = 0.0;
			tempo_restante = 0.0;
			
			for(org.trello4j.model.List fila: filas){
				List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
			
	
				for(Card carta: cartas){
					
					
								
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
								tempo_previsto = Double.parseDouble(part[1]) + tempo_previsto;
								tempo_restante = tempo_previsto - tempo_gasto;
								
								
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
	 * @param NomeDoSprint - NOME_DO_SPRINT = "S1", S1 representa Sprint 1 ({@link String})
	 * @return Vai devolver um HashMap<Strin,Double[]> sendo a String o nome de cada Sprint, Double[0]=tempo_gasto_por_Sprint; Double[1]=tempo_previsto_por_sprint; Double[2]=tempo_restante_por_Sprint ({@link HashMap<String,Double[]>})
	 */
	public static HashMap<String,Double[]> getTempoPorSprint(String IdDoQuadro, String NomeDoSprint ){
			
			//String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
		
		String SprintName = '[' + NomeDoSprint + ']' + "Sprint Backlog"; 
		
		
			
		HashMap<String,Double[]> tempoPorSprint = new HashMap<String,Double[]>();
			

		HashMap<String,Double[]> tempoPorSprintPorMembro = getTempoPorSprintPorMembro(IdDoQuadro, NomeDoSprint );
			
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
				
				List<Member> membros = TrelloQuadros.trelloApi.getMembersByBoard(IdDoQuadro);
				
				
				for(org.trello4j.model.List fila: filas){
					
					if(fila.getName().contains('[' + NomeDoSprint + ']')){
						
						
						List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
					
						for(Member membro: membros){
							
							tempo_gasto = 0.0;
							tempo_previsto = 0.0;
							tempo_restante = 0.0;
					
							for(Card carta: cartas){
								
								
											
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
						
						System.out.println(desc[0]);
						
						String[] data = desc[1].split("\\[End Timestamp\\]");
						
						datas = data;
						}
						
					}
					
					
				}
			}
			
			System.out.println("Data de �nicio:" + datas[0] + '\n' + "Data de Fim:" + datas[1]);
			
				
			return datas;
		}
}