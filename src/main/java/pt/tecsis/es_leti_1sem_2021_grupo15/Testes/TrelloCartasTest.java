package pt.tecsis.es_leti_1sem_2021_grupo15.Testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.trello4j.model.Card;
import pt.tecsis.es_leti_1sem_2021_grupo15.trello_api.TrelloCartas;

class TrelloCartasTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
				
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	final void testgetTodasAsCartas() {
		
		Assertions.assertNotNull(TrelloCartas.getTodasAsCartas("61a0fd2af0db9b4e69edb703"));
	}

}
