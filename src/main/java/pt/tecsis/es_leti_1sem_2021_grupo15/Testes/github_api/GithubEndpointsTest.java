package pt.tecsis.es_leti_1sem_2021_grupo15.Testes.github_api;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GithubEndpoints;

public class GithubEndpointsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public final void testGET_REPOSITORY_CONTENT_URL() {
		
		// Verificar que os URLs devolvidos estão corretos.
		
		assertEquals("https://api.github.com/user", GithubEndpoints.GET_SELF_USER_URL);
		assertEquals("https://api.github.com/repos/torvalds/linux/contents/README", GithubEndpoints.GET_REPOSITORY_CONTENT_URL("torvalds", "linux", "README"));
	}
}