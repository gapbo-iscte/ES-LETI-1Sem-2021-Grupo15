package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth.GitHubCredentials;

public class GitHubUserTest {

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
	public final void testGitHubUser() {
		
		// Verificar que o processamento de dados em formato JSON funciona corretamente.
		
		GitHubUser linusTorvalds = new GitHubUser("{\n"
			+ "  \"login\": \"torvalds\",\n"
			+ "  \"id\": 1024025,\n"
			+ "  \"node_id\": \"MDQ6VXNlcjEwMjQwMjU=\",\n"
			+ "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/1024025?v=4\",\n"
			+ "  \"gravatar_id\": \"\",\n"
			+ "  \"url\": \"https://api.github.com/users/torvalds\",\n"
			+ "  \"html_url\": \"https://github.com/torvalds\",\n"
			+ "  \"followers_url\": \"https://api.github.com/users/torvalds/followers\",\n"
			+ "  \"following_url\": \"https://api.github.com/users/torvalds/following{/other_user}\",\n"
			+ "  \"gists_url\": \"https://api.github.com/users/torvalds/gists{/gist_id}\",\n"
			+ "  \"starred_url\": \"https://api.github.com/users/torvalds/starred{/owner}{/repo}\",\n"
			+ "  \"subscriptions_url\": \"https://api.github.com/users/torvalds/subscriptions\",\n"
			+ "  \"organizations_url\": \"https://api.github.com/users/torvalds/orgs\",\n"
			+ "  \"repos_url\": \"https://api.github.com/users/torvalds/repos\",\n"
			+ "  \"events_url\": \"https://api.github.com/users/torvalds/events{/privacy}\",\n"
			+ "  \"received_events_url\": \"https://api.github.com/users/torvalds/received_events\",\n"
			+ "  \"type\": \"User\",\n"
			+ "  \"site_admin\": false,\n"
			+ "  \"name\": \"Linus Torvalds\",\n"
			+ "  \"company\": \"Linux Foundation\",\n"
			+ "  \"blog\": \"\",\n"
			+ "  \"location\": \"Portland, OR\",\n"
			+ "  \"email\": null,\n"
			+ "  \"hireable\": null,\n"
			+ "  \"bio\": null,\n"
			+ "  \"twitter_username\": null,\n"
			+ "  \"public_repos\": 6,\n"
			+ "  \"public_gists\": 0,\n"
			+ "  \"followers\": 145475,\n"
			+ "  \"following\": 0,\n"
			+ "  \"created_at\": \"2011-09-03T15:26:22Z\",\n"
			+ "  \"updated_at\": \"2021-08-20T23:59:05Z\"\n"
			+ "}\n"
		);
		
		assertEquals("torvalds", linusTorvalds.login);
		assertEquals(1024025, linusTorvalds.id);
		assertEquals("https://avatars.githubusercontent.com/u/1024025?v=4", linusTorvalds.avatar_url);
		assertEquals("https://api.github.com/users/torvalds", linusTorvalds.url);
		assertEquals("https://github.com/torvalds", linusTorvalds.htmlUrl);
		assertEquals("https://api.github.com/users/torvalds/repos", linusTorvalds.reposUrl);
		assertEquals("User", linusTorvalds.type);
		assertEquals("Linus Torvalds", linusTorvalds.name);
		assertEquals("Portland, OR", linusTorvalds.location);
		assertEquals(6, linusTorvalds.publicRepos);
		assertEquals(0, linusTorvalds.publicGists);
		assertEquals(145475, linusTorvalds.followers);
		assertEquals(0, linusTorvalds.following);
		assertEquals(DateTime.parse("2011-09-03T15:26:22Z"), linusTorvalds.createdAt);
		assertEquals(DateTime.parse("2021-08-20T23:59:05Z"), linusTorvalds.updatedAt);
	}

}
