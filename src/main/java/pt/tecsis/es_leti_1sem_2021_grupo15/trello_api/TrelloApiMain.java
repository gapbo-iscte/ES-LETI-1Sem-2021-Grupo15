package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;

import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Card.Attachment;


public class TrelloApiMain {
	
	
	private static final String trelloKey="dab38c0e84b88819e31482cf6017c733";
	private static final String trelloAccessToken="835f871fa169a6727334a677943a48dbc4369af0ebb26356f9b2c3cc291e1ff0";
	
	
	public static void main(String[] args) {
	     
		Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken); 
	   
	     
		//HashMap<String,String> projetos = TrelloQuadros.getNomeDoProjeto(trelloApi);
	   // List<Card> quadros = TrelloQuadros.getQuadros(trelloApi);//,boards.get(0));
	    
		//List<Card> checklists =  TrelloCartas.getTituloDeTodasAsCartas(trelloApi,"Projeto_ES");
	
	    //List<Action> actions = TrelloAcoes.getAcoesPorQuadro(trelloApi,"Projeto_ES");
		
		
		//HashMap<String,String> filas = TrelloFilas.getFilasQuadro(trelloApi,"Projeto_ES");
	
		//List<Card> checklists =  TrelloCartas.getTituloDasCartasPorFila(trelloApi,"Reuniões","Projeto_ES");
		
	//	List<Card> checklists =  TrelloCartas.getCartasDescricaoPorFila(trelloApi,"Sprints","Projeto_ES");
		
		
		
		//List<Checklist> checks = TrelloChecklists.getCheckList(trelloApi, "Projeto_ES");
	    
	    
	    //List<Member> membros = TrelloMembros.getMemberDoQuadro(trelloApi,"Projeto_ES");
	    
		List<Attachment> attaches = TrelloCartas.getAttachmentPorCarta(trelloApi,"Texto resultante das reuniões de SPRINT Planning, SPRINT Review e SPRINT Retrospective, para cada SPRINT do projeto","Projeto_ES");
	}
	
	
	
	
     
}


