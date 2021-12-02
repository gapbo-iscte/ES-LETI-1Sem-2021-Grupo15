package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.model.Action;
import org.trello4j.model.Member;

public class TrelloAcoes {
	
	private static Double tempo_gasto = 0.0;
	private static Double tempo_previstas = 0.0;
		
	
	public static  List<Action> getAcoesPorQuadro(String NomeDoQuadro ){
			
		String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
		
		List<Action> actions = TrelloQuadros.trelloApi.getActionsByBoard(QuadroID);
		
		for (Action action : actions) {
            System.out.println(action.getMemberCreator().getUsername()+"-"+action.getData().getText() + action.getDate());
		}
		
		
		return actions;  	
	}
	
	
	
	public static void getTempoPorQuadro(String NomeDoQuadro ){
		
		String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
		
		
		List<Action> actions = TrelloQuadros.trelloApi.getActionsByBoard(QuadroID);
		
		for (Action action : actions) {
            String horas = action.getData().getText();
            
            if( horas != null){
            
	            System.out.println(horas);
	            
	            String[] parts = horas.split(" ");
	            
	            String[] parts1 = parts[1].split("/");
	            
	            tempo_gasto = Double.parseDouble(parts1[0]) + tempo_gasto;
	            
	            tempo_previstas = Double.parseDouble(parts1[1]) + tempo_previstas;
	            
            }
		}
		System.out.println("Numero de horas gastas:" + tempo_gasto);
		System.out.println("Numero de horas previstas:" + tempo_previstas);
		
		
		//return actions;  	
	}
	
	
	public static void getTempoPorMembro(String NomeDoQuadro ){
			
			String QuadroID = TrelloID.getQuadroID(NomeDoQuadro);
			
			 List<Member> membros = TrelloQuadros.trelloApi.getMembersByBoard(QuadroID);
			
			for(Member membro: membros){
				
				
				List<Action> acoes_membro = TrelloQuadros.trelloApi.getActionsByMember(membro.getId());
								
				tempo_gasto = 0.0;
				tempo_previstas = 0.0;
			 			 
				 for (Action action : acoes_membro) {
		            String horas = action.getData().getText();
		            
		            if( horas != null){
		            
			            System.out.println(horas);
			            
			            String[] parts = horas.split(" ");
			            
			            String[] parts1 = parts[1].split("/");
			            
			            tempo_gasto = Double.parseDouble(parts1[0]) + tempo_gasto;
			            
			            tempo_previstas = Double.parseDouble(parts1[1]) + tempo_previstas;
			            
		            }
				}
				 
				 System.out.println(membro.getFullName() + " --- " + "Numero de horas gastas:" + tempo_gasto);
				 System.out.println(membro.getFullName() + " --- " + "Numero de horas previstas:" + tempo_previstas);
				 
			}
			
			
			//return actions;  	
		}
	
	
	
	
	
	

}
