package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
	    
		// HashMap<String,Double[]> gasto = TrelloAcoes.getTempoPorSprint(qu);
		 
		// HashMap<String,Double[]> gasto = TrelloAcoes.getTempoPorMembro(qu);
		 
		HashMap<String,Double[]> tempoPorCommitPorMembro = TrelloGitTempos.getTempoPorCommitPorMembro(qu);

		for(Entry<String,Double[]> entry : tempoPorCommitPorMembro.entrySet()){
			 
			System.out.println(entry.getKey()); 
			for(double d: entry.getValue()){
				System.out.println(d);
			}
			System.out.println("----------------------------------");
			
			 
		 }
		
		 
		/* Double[] arraygasto = TrelloAcoes.getTempoPorQuadro(qu);
		 
		 for(int i = 0; i != arraygasto.length-1; i++){
			 
			System.out.println(arraygasto[i]); 
			 
		 }*/
		
		
		//String[] datas = TrelloAcoes.getDataPorSprint(qu,"S1");
		
		
		//String[] textos = TrelloFilas.textosPorSprint(qu,"S1");
		
		//String[] textos = TrelloFilas.getCartasDescricaoPorSprint(qu,"S3");
		
		/*for(int i = 0; i!=textos.length;i++){
			
			System.out.println(textos[i]);
			
			
		}*/
		 
		 
	    
//	   List<Member> membros = TrelloMembros.getMemberDoQuadro("Projeto_ES");
	    
	//Perceber o que é um attach	List<Attachment> attaches = TrelloCartas.getAttachmentPorCarta("Texto resultante das reuniões de SPRINT Planning, SPRINT Review e SPRINT Retrospective, para cada SPRINT do projeto","Projeto_ES");
	
	
	
	
	
	
	
	}
	
	
	
	
     
}


