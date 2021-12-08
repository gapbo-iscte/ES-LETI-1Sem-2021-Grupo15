package pt.tecsis.es_leti_1sem_2021_grupo15.Testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.trello4j.Trello;
import org.trello4j.model.Board;
import org.trello4j.model.List;
import org.trello4j.model.Member;

import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloAcoes;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloApiMain;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloDatas;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloFilas;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloMembros;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloQuadros;

class TrelloApiMainTest {
    String trellokey;
    String trellotoken;
    String username;
	

	@BeforeEach
	void setUp() throws Exception {
		trellotoken= "dab38c0e84b88819e31482cf6017c733";
		trellokey="835f871fa169a6727334a677943a48dbc4369af0ebb26356f9b2c3cc291e1ff0";
		username="goncalobenido";
		TrelloApiMain.Inicializar(trellokey, trellotoken);

	}

	

	@Test
	public void BuscarQuadrosTeste() {
		Board quadroEsperado = new Board();
		quadroEsperado.setId("61a0fd2af0db9b4e69edb703");
		
		Board trello = TrelloQuadros.BuscarQuadros(username).get(0);
		Assertions.assertEquals(quadroEsperado.getId(), trello.getId());
	}
	
	@Test
	public void getMembroDoQuadroTeste() {
		Member membroEsperado = new Member();
		membroEsperado.setId("615c88d18ab3f0167b5a5df3");
		
		Member trello = TrelloMembros.getMembroDoQuadro(username);
		Assertions.assertEquals(membroEsperado.getId(), trello.getId());
	}
	
	@Test
	public void getMembrosDoQuadroTeste() {
		Member membroEsperado = new Member();
		membroEsperado.setId("615c88d18ab3f0167b5a5df3");
		
		Member trello = TrelloMembros.getMembrosDoQuadro("615c89b6a359063061e30315").get(0);
		Assertions.assertEquals(membroEsperado.getId(), trello.getId());
	}
	
	@Test
	public void getDataPorSprintTeste() {
		Board quadroEsperado = new Board();
		quadroEsperado.setId("61a0fd2af0db9b4e69edb703");
		
		String[] datasEsperadasSprint1 = {"2021-10-28T00:00:00+00:00 ", "2021-11-11T23:59:59+00:00"};

		
		String[] datasObtidasSprint1 = TrelloDatas.getDataPorSprint(quadroEsperado.getId(),"S1");
		Assertions.assertEquals(datasEsperadasSprint1.hashCode(),datasObtidasSprint1.hashCode());
	}
	
	@Test
	public void getCartasDescricaoPorSprintTeste() {

		Board quadroEsperado = new Board();
		quadroEsperado.setId("61a0fd2af0db9b4e69edb703");
		
		String[] cartasEsperadasSprint1 = {"Criar uma classe GUI para criar uma interface grafica que vá dar a informação sobre:-identificação do projeto -identificação de cada elemento -data do inicio do projeto",
				"Texto resultante da descrição de cada cartão",
				"Estabelecer a conexão entre o utilizador ao Trello",
				"Trello- Obter o nome do projeto, e dos quadros",
				"Total número de horas do projeto e por colaborador",
				"GitHub API - Chamadas autenticadas",
				"GitHub API - Chamada GET /user",
				"GitHub API - Chamada GET conteúdo de um ficheiro",
				"(Testes) GitHub API - Composição de testes",
				"Desculpa Covas testes tem um inicio e um fim"};
		
		
		String[] cartasObtidasSprint1 = TrelloFilas.getCartasDescricaoPorSprint(quadroEsperado.getId(),"S1");
		Assertions.assertEquals(cartasEsperadasSprint1.hashCode(),cartasObtidasSprint1.hashCode());
	}
	
	@Test
	public void getNumeroDeSprintTeste() {

		Board quadroEsperado = new Board();
		quadroEsperado.setId("61a0fd2af0db9b4e69edb703");
		
		int numeroDeSprintsEsperado = 3;
		
		
		int numeroDeSprintDoTrello = TrelloAcoes.getNumeroDeSprints(quadroEsperado.getId());
		Assertions.assertEquals(numeroDeSprintsEsperado,numeroDeSprintDoTrello);
	}

	@Test
	public void getTempoPorSprintTeste() {

		Board quadroEsperado = new Board();
		quadroEsperado.setId("61a0fd2af0db9b4e69edb703");
		
		HashMap<String,Double[]> numeroDeSprintsEsperado = new HashMap<String,Double[]>();

		
		String s1 ="[1]Sprint Backlog";
		String s2 ="[2]Sprint Backlog";
		String s3 ="[3]Sprint Backlog";
		
		Double[] horasS1 = {49.09, 62.77,13.68};
		Double[] horasS2 = {8.870000000000001, 10.879999999999999, 2.009999999999999};
		Double[] horasS3 = {25.299999999999997, 41.9,16.6};
		
		numeroDeSprintsEsperado.put(s3, horasS3);
		numeroDeSprintsEsperado.put(s1, horasS1);
		numeroDeSprintsEsperado.put(s2, horasS2);
		
		HashMap<String,Double[]> numeroDeSprintsObtido = TrelloAcoes.getTempoPorSprint(quadroEsperado.getId());
		Assertions.assertEquals(numeroDeSprintsEsperado,numeroDeSprintsObtido);
	}
	

}
