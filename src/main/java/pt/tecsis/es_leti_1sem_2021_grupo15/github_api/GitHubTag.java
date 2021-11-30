package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

import java.io.IOException;

import javax.naming.AuthenticationException;

import org.joda.time.DateTime;
import org.json.JSONObject;

import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth.GitHubCredentials;


/**
 * Representa uma tag de um repositório GitHub.
 * @author manuel-covas
 */

public class GitHubTag {

	public final String name, commitURL,  zipBallURL, tarBallURL;
	
	public GitHubTag(String tagJSON) {
		
		JSONObject json = new JSONObject(tagJSON);
		
		this.name = json.getString("name");
		this.commitURL = json.getJSONObject("commit").getString("url");
		this.zipBallURL = json.getString("zipball_url");
		this.tarBallURL = json.getString("tarball_url");	
	}
	
	
	/**
	 * Devolve o commit associado com esta tag.
	 * @param credentials - credenciais a utilizar ou {@code null} para aceder sem credenciais ({@link GitHubCredentials})
	 * @return
	 * @throws IOException 
	 * @throws AuthenticationException 
	 */
	
	public GitHubCommit getCommit(GitHubCredentials credentials) throws AuthenticationException, IOException {
		return GitHubAPI.getCommit(commitURL, credentials);
	}
}
