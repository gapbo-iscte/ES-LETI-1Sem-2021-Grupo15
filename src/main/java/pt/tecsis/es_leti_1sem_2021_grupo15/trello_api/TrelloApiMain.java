package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.io.IOException;
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

import pt.tecsis.es_leti_1sem_2021_grupo15.GUI.GUICsv;
import pt.tecsis.es_leti_1sem_2021_grupo15.GUI.GUITabelas;
import pt.tecsis.es_leti_1sem_2021_grupo15.GUI.GUITabelas.Tabela;


public class TrelloApiMain {
	
	public static Trello trelloApi = null;
	
	
	
		
	/**Usado para estabelecer a conexão ao TrelloApi
	 * @param trelloKey - Key do Utilizador ({@link String})
	 * @param trelloAccessToken - Token do utilizador ({@link String})
	 */
	public static void Inicializar(String trelloKey, String trelloAccessToken){
			
	trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);
			     
	}
	
	
	//////////////////////////////////////////////////////////////////VAI DESAPARECER//////////////////////////////////////////////////////////////////////////////////////////
		
			
			
		private static final String trelloKey="dab38c0e84b88819e31482cf6017c733";
		private static final String trelloAccessToken="835f871fa169a6727334a677943a48dbc4369af0ebb26356f9b2c3cc291e1ff0";
		
		public static void main(String[] args) {
	     
		//PRIMEIRO METODO OBRIGATÓRIO A SER CHAMADO *******************************************************************************************************************
		Inicializar(trelloKey, trelloAccessToken);
		
		
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

		 
		 
		/* for(int i = 0; i!=tempos.length; i++){
			 
			 System.out.println(tempos[i]);
			 
		 }*/
		 
		 
		// HashMap<String,Double[]> gasto = TrelloAcoes.getTempoPorMembro(qu);
		 
		/*HashMap<String,Double[]> tempoPorCommitPorMembro = TrelloGitTempos.getTempoPorCommitPorMembro(qu);

		for(Entry<String,Double[]> entry : tempoPorCommitPorMembro.entrySet()){
			 
			System.out.println(entry.getKey()); 
			for(double d: entry.getValue()){
				System.out.println(d);
			}
			System.out.println("----------------------------------");
			
			
		 }*/
		
		 
		 /*String[] arraygasto = TrelloDatas.getDataPorSprint(qu,"S2");
		 
		 System.out.println(arraygasto.length);
		 
		 for(int i = 0; i != arraygasto.length; i++){
			 
			System.out.println(arraygasto[i]); 
			 
		 }*/
		
		
		
		
		//String[] datas = TrelloAcoes.getDataPorSprint(qu,"S1");
		

		
		//String[] textos = TrelloFilas.textosPorSprint(qu,"S1");
		
		//String[] textos = TrelloFilas.getCartasDescricaoPorSprint(qu,"S3");
		
		
		
		/*HashMap<String, String[]> datas = TrelloDatas.getDataTestes(qu);
		
		for(Entry<String,String[]> entry : datas.entrySet()){
			 
			System.out.println(entry.getKey()); 
			for(String d: entry.getValue()){
				System.out.println(d);
			}
			System.out.println("----------------------------------");
			
			System.out.println(datas.size());
			
			 
		 }*/

		 
		
	//	GUITabelas.tabelaTotal(qu,"20");

		
		//GUITabelas.tabelaPorSprint(qu, "S3");
		 
	//	GUITabelas.tabelaCustoPorSprint(qu, "S3", "20");
		
		//GUITabelas.exportTabela(GUITabelas.tabelaTotal(qu,"20"), path);
		
		//GUITabelas.exportTabela(GUITabelas.tabelaCustoPorSprint(qu, "S3", "20"), path);
		
		//GUITabelas.exportTabela(GUITabelas.tabelaPorSprint(qu, "S3"), path);
		
		//GUITabelas.tabelaTempoTestes(qu);
		
		//GUITabelas.tabelaGerouCommits(qu,"20");
		
		GUITabelas.tabelaNaoGerouCommits(qu,"20");
		
		
		
		
	//	String bem = GUICsv.pathCorrection("Custo total do Projeto");
	//	System.out.println(bem);
		
		
		
		/*String[] textos = TrelloFilas.textosPorSprint(qu, "S3");
		
		 for(int i = 0; i != textos.length; i++){
			 
				System.out.println(textos[i]); 
				 
			 }*/
	    
//	   List<Member> membros = TrelloMembros.getMemberDoQuadro("Projeto_ES");
	    
	//Perceber o que é um attach	List<Attachment> attaches = TrelloCartas.getAttachmentPorCarta("Texto resultante das reuniões de SPRINT Planning, SPRINT Review e SPRINT Retrospective, para cada SPRINT do projeto","Projeto_ES");
	
	
	
	
	
	
	
	}
	
	
	
	
     
}


