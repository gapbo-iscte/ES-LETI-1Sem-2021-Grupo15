package pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import okhttp3.Request;

public class GitHubCredentialsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {}
	
	@Before
	public void setUp() throws Exception {}
	@After
	public void tearDown() throws Exception {}
	
	
	/**
	 * Testes do construtor {@link GitHubCredentials#GitHubCredentials(String)}
	 */
	
	@Test
	public final void testGitHubCredentials() {
		
		// Verificar que instanciar esta classe com um token nulo produz uma exceção NUllPointer.
		
		assertThrows(NullPointerException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				new GitHubCredentials(null);
			}
		});
		
		// Verificar que o construtor não produz exceções quando utlizado corretamente.
		
		new GitHubCredentials("personal_access_token");
	}
	
	/**
	 * Testes do método {@link GitHubCredentials#getHttpClient()}
	 */
	
	@Test
	public final void testGetHttpClient() {
		// Verificar que o cliente OkHttp é criado e devolvido.
		assertNotNull(new GitHubCredentials("personal_acces_test").getHttpClient());
	}

	
	/**
	 * Testes do método {@link GitHubCredentials#authenticateRequest(okhttp3.Request.Builder)}
	 */
	
	@Test
	public final void testAuthenticateRequest() {
		
		// Verificar que o método adiciona o header HTTP "Authorization" corretamente
		
		GitHubCredentials credenciais = new GitHubCredentials("personal_access_token");
		Request.Builder request = new Request.Builder().url("https://exemplo.pt");
		
		credenciais.authenticateRequest(request);
		
		assertEquals("token personal_access_token", request.build().header("Authorization"));
	}

}
