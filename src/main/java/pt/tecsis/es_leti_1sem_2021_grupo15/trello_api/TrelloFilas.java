package pt.tecsis.es_leti_1sem_2021_grupo15.trello_api;

import java.util.List;



/**
 * @author Goncalo Benido
 *
 */
public class TrelloFilas {
	

	/**
	 * 
	 * @param IdDoQuadro - ID do quadro que pretende usar ({@link String})
	 * @return Devolve todas as filas existentes em um quadro ({@link List<org.trello4j.model.List>})
	 */
	public static List<org.trello4j.model.List> getFilasQuadro(String IdDoQuadro){
			List<org.trello4j.model.List> filasQuadro = TrelloQuadros.trelloApi.getListByBoard(IdDoQuadro);
	
			for (org.trello4j.model.List  fila: filasQuadro) {	
				System.out.println(fila.getName() + "-" + fila.getId() );	
			}
			
			return filasQuadro;
						
		}
		



	
	
	
	
	

}
