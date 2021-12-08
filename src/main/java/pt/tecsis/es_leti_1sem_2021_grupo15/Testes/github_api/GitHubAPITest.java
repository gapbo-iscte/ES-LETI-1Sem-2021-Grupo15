package pt.tecsis.es_leti_1sem_2021_grupo15.Testes.github_api;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubAPI;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubBranch;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubCommit;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubRepository;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubTag;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.GitHubUser;
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
	public final void testGetUser() throws IOException, Exception {
		
		// Verificar que é devolvido o utilizador correto
		
		GitHubUser user = GitHubAPI.getUser("torvalds");
		
		assertEquals("torvalds", user.login);
		assertEquals(1024025, user.id);
		
		// Verificar que é levantada uma exceção
		
		assertThrows(Exception.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				GitHubAPI.getUser("");
			}
		});
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
		
		// Verificar que é levantada uma exceção.
		
		assertThrows(Exception.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				GitHubAPI.getRepositoryFile("", "", "", null);
			}
		});
	}

	@Test
	public final void testGetCommit() throws AuthenticationException, IOException {
		
		// Verificar que é devolvido o commit certo
		
		GitHubCommit commit = GitHubAPI.getCommit("torvalds", "linux", "7a10d8c810cfad3e79372d7d1c77899d86cd6662", null);
		
		assertEquals("7a10d8c810cfad3e79372d7d1c77899d86cd6662", commit.hash);
		assertEquals("https://github.com/torvalds/linux/commit/7a10d8c810cfad3e79372d7d1c77899d86cd6662", commit.htmlURL);
		
		// Verificar que é levantada uma exceção.
		
		assertThrows(AuthenticationException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				GitHubAPI.getCommit("", "", "", null);
			}
		});
	}
	
	@Test
	public final void testGetBranchCommits() throws AuthenticationException, IOException {
		
		// Verificar que são devolvidos commits.
		
		GitHubCommit[] branchCommits = GitHubAPI.getBranchCommits("octocat", "hello-worid", "master", null);
		
		assertEquals("7e068727fdb347b685b658d2981f8c85f7bf0585", branchCommits[0].hash);
		assertEquals("https://github.com/octocat/hello-worId/commit/7e068727fdb347b685b658d2981f8c85f7bf0585", branchCommits[0].htmlURL);
		
		// Verificar que é levantada uma exceção.
		
		assertThrows(AuthenticationException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				GitHubAPI.getBranchCommits("", "", "", null);
			}
		});
	}
	
	@Test
	public final void testGetRepository() throws AuthenticationException, IOException {
		
		// Verificar que é devolvido o repositório.
		
		GitHubRepository repository = GitHubAPI.getRepository("torvalds", "linux", null);
		
		assertEquals("linux", repository.nome);
		
		// Verificar que é levantada uma exceção.
		
		assertThrows(AuthenticationException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				GitHubAPI.getRepository("", "", null);
			}
		});
	}
	
	@Test
	public final void testGetRepositoryBranches() throws AuthenticationException, IOException {
		
		// Verificar que a chamada funciona.
		
		GitHubBranch[] branches = GitHubAPI.getRepositoryBranches("torvalds", "linux", null);
		
		assertTrue(branches.length > 0);
		
		// Verificar que é levantada uma exceção.
		
		assertThrows(AuthenticationException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				GitHubAPI.getRepositoryBranches("", "", null);
			}
		});
	}
	
	@Test
	public final void testGetRepositoryTags() throws AuthenticationException, IOException {
		
		// Verificar que a chamada funciona.
		
		GitHubTag[] tags = GitHubAPI.getRepositoryTags("torvalds", "linux", null);
		
		assertTrue(tags.length >= 20);
		
		// Verificar que é levantada uma exceção.
		
		assertThrows(AuthenticationException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				GitHubAPI.getRepositoryTags("EYilS9LCxI", "EYilS9LCxI", null);
			}
		});
	}
}
