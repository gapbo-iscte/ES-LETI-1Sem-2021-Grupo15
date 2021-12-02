package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

import org.joda.time.DateTime;
import org.json.JSONObject;


/**
 * Representa um commit de um repositório GitHub.
 * @author manuel-covas
 */

public class GitHubCommit {

	public final String hash, htmlURL, message;
	public final DateTime time;
	
	/**
	 * Interpreta os dados devolvidos pelo GitHub em formato JSON e
	 * constroi uma instância desta classe com os mesmos.
	 * @param {@link String} commitJSON
	 */
	
	public GitHubCommit(String commitJSON) {
		
		JSONObject json = new JSONObject(commitJSON);
		JSONObject commitObject = json.getJSONObject("commit");
		
		this.hash = json.getString("sha");
		this.htmlURL = json.getString("html_url");
		this.message = commitObject.getString("message");
		this.time = DateTime.parse(commitObject.getJSONObject("author").getString("date"));
		
	}
}
