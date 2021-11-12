package pt.tecsis.es_leti_1sem_2021_grupo15.Testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.tecsis.es_leti_1sem_2021_grupo15.GUI.GUI;

public class GUITeste extends GUI {
	
	private static GUI gui;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gui = new GUI();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testAddComponentsToPane() {
		
	}

	@Test
	public final void testMain() {
		assertNotNull(gui);
	}

}
