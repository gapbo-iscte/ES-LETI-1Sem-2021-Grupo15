package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

import org.trello4j.model.Action;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

public class TrelloAcoes {
	
	private static Double tempo_gasto = 0.0;
	private static Double tempo_previsto = 0.0;
	private static Double tempo_restante = 0.0;
		
	
	public List<Action> getAcoesPorQuadro(String IdDoQuadro ){
			
		//String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
		
		List<Action> actions = TrelloQuadros.trelloApi.getActionsByBoard(IdDoQuadro);
		
		for (Action action : actions) {
            System.out.println(action.getMemberCreator().getUsername()+"-"+action.getData().getText() + action.getDate());
		}
		
		
		return actions;  	
	}
	
	
	//Este método retorna um array tempos[] onde tempos[0] = tempo_gasto; tempos[1]=tempo_previsto; tempos[2]=tempo_restante;
	public static Double[] getTempoPorQuadro(String IdDoQuadro ){
		
		//String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
		
		List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
		
		System.out.println(filas.size());
		
		tempo_gasto = 0.0;
		tempo_previsto = 0.0;
		tempo_restante = 0.0;
		
		for(org.trello4j.model.List fila: filas){
				
				
				List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
				
				
				
				for(Card carta: cartas){
					
					
					List<Action> acoes_carta = TrelloQuadros.trelloApi.getActionsByCard(carta.getId());
					
				
				
					for (Action action : acoes_carta) {
						String horas = action.getData().getText();
						
						
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
	
					System.out.println("Numero de horas gastas:" + tempo_gasto);
					System.out.println("Numero de horas previstas:" + tempo_previsto);
					System.out.println("Número de horas restantes:" + tempo_restante);
					
					
					return tempos;  	
			
					
				}
			
			
		
	
	
	
	
	//Método retorna HashMap<String,Double[]>, o double[0]=tempo_gasto_por_membro; double[1]=tempo_previsto_por_membro: double[2]=tempo_restante_por_membro
	public static HashMap<String,Double[]> getTempoPorMembro(String IdDoQuadro ){
		
		//String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
		
		
		
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
	
	
	
	
	//NOME_DO_SPRINT = S1, S2 , etc..   Vai devolver um HashMap<Strin,Double[]> sendo a String o nome de cada Sprint, Double[0]=tempo_gasto_por_Sprint; Double[1]=tempo_previsto_por_sprint; Double[2]=tempo_restante_por_Sprint
	public static HashMap<String,Double[]> getTempoPorSprint(String IdDoQuadro, String NomeDoSprint ){
			
			//String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
			
			HashMap<String,Double[]> tempoPorSprint = new HashMap<String,Double[]>();
			
			List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(IdDoQuadro);
			
			System.out.println(filas.size());
			
			for(org.trello4j.model.List fila: filas){
				
				if(fila.getName().contains("[" + NomeDoSprint + "]")){
					
					
					List<Card> cartas = TrelloCartas.getCartasPorFila(fila.getId(), IdDoQuadro);
					
					
					tempo_gasto = 0.0;
					tempo_previsto = 0.0;
					tempo_restante = 0.0;
					
					for(Card carta: cartas){
						
						
						List<Action> acoes_carta = TrelloQuadros.trelloApi.getActionsByCard(carta.getId());
						
						
					System.out.println(acoes_carta.size());
					
					
					for (Action action : acoes_carta) {
						String horas = action.getData().getText();
						
						
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
						
					Double[] tempos = {tempo_gasto, tempo_previsto, tempo_restante};
						
						tempoPorSprint.put(fila.getName(),tempos);
						
						System.out.println(fila.getName() + " --- " + "Numero de horas gastas:" + tempo_gasto);
						//System.out.println(membro.getFullName() + " --- " + "Numero de horas previstas:" + tempo_previsto);
						
					}
				
				}
			
			
			return tempoPorSprint;  	
		}
	
	
	
	
	
	
		public static HashMap<String,Double[]> getTempoPorSprintPorMembro(String IdDoQuadro, String NomeDoSprint ){
				
				//String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
				
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
		
		
		
		
		//Obter a data de ínicio e fim de um Sprint
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
			
			System.out.println("Data de Ínicio:" + datas[0] + '\n' + "Data de Fim:" + datas[1]);
			
				
			return datas;
		}
	
	
		
	
	
	
	
	
	

	
	

}
