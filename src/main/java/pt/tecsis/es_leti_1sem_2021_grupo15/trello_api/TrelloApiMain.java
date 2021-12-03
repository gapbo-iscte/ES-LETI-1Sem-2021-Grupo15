package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;
import java.util.List;

import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Card.Attachment;
import org.trello4j.model.Checklist;
import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;


public class TrelloApiMain {
	
	
	private static final String trelloKey="dab38c0e84b88819e31482cf6017c733";
	private static final String trelloAccessToken="835f871fa169a6727334a677943a48dbc4369af0ebb26356f9b2c3cc291e1ff0";
	
	
	public static void main(String[] args) {
	     
		//Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken); 
		
		
		String NomeMembro=  trelloAccessToken.toString();    //"goncalobenido";
		
		//String QuadroNome = "Projeto_ES";
	   
	    
		//PRIMEIRO METODO OBRIGATÓRIO A SER CHAMADO *******************************************************************************************************************
		TrelloQuadros.Inicializar(trelloKey, trelloAccessToken);
		
		
		List<Board> quadros = TrelloQuadros.BuscarQuadros(TrelloMembros.getMembroDoQuadro(trelloAccessToken).getUsername());
		
		String qu = quadros.get(0).getId();
		
		
		for(Board quadro: quadros){
			
			System.out.println(quadro.getName() + quadro.getId());
			
			
		}
	   
		
		
		
		
	    
		//List<Card> checklists =  TrelloCartas.getTodasAsCartas(qu);
	
//	    List<Action> actions = TrelloAcoes.getAcoesPorQuadro("Projeto_ES");
		
		
	//	List<org.trello4j.model.List> filas = TrelloFilas.getFilasQuadro(qu);
	
		//String[] checklists =  TrelloCartas.getCartasTítuloPorFila("617aad529e383145723dfba5",qu);
		
		//String[] checklists =  TrelloCartas.getCartasDescricaoPorFila("617aad529e383145723dfba5",qu);
		
	//	String checklists =  TrelloCartas.getCartasDescricaoPorQuadro("[DS1] - Daily Scrum",qu);
		
//		List<Checklist> checks = TrelloChecklists.getCheckList("Projeto_ES");
	    
	  //  TrelloAcoes.getTempoPorQuadro(qu);
	    
		// HashMap<String,Double[]> gasto = TrelloAcoes.getTempoPorSprintPorMembro(qu,"S2");
		 
		// HashMap<String,Double[]> gasto = TrelloAcoes.getTempoPorMembro(qu);
		 
		 //Double[] arraygasto = TrelloAcoes.getTempoPorQuadro(qu);
		
		//HashMap<String,Double> tempoPorCommitPorMembro = TrelloGitTempos.getTempoPorCommitPorMembro(qu);
		 
		 
	    
//	   List<Member> membros = TrelloMembros.getMemberDoQuadro("Projeto_ES");
	    
	//Perceber o que é um attach	List<Attachment> attaches = TrelloCartas.getAttachmentPorCarta("Texto resultante das reuniões de SPRINT Planning, SPRINT Review e SPRINT Retrospective, para cada SPRINT do projeto","Projeto_ES");
	
	
	
	
	
	
	
	}
	
	
	
	
     
}


