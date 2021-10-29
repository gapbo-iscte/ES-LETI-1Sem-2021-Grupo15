package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth.GitHubCredentials;

public class GitHubAPITest {

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
	public final void testGetSelf() {
		
		// Verificar que é produzida a exceção AuthenticationException
		
		assertThrows(AuthenticationException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				GitHubAPI.getSelf(new GitHubCredentials("invalid_personal_access_token"));
			}
		});
	}

	@Test
	public final void testGetRepositoryFile() throws AuthenticationException, IOException {
		
		// Verificar que se obtem o conteúdo de um ficheiro sem exceções.
		
		GitHubAPI.getRepositoryFile("torvalds", "linux", "README", null);
	}

}
