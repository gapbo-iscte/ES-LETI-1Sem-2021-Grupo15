package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Representa um utilizador do GitHub e contem detalhes sobre o mesmo.
 * @author manuel-covas
 */

public class GitHubUser {
	
	public final int id, publicRepos, publicGists, followers, following;
	public final String login, avatar_url, url, htmlUrl, reposUrl, name, location;
	public final DateTime createdAt, updatedAt;
	
	
	/**
	 * Interpreta os dados do utilizador do GitHub em formato JSON e
	 * constroi uma instância desta classe com os mesmos.
	 * @param {@link String} userJSON
	 */
	
	public GitHubUser(String userJSON) throws JSONException {
		
		JSONObject json = new JSONObject(userJSON);
		
		this.login = json.getString("login");
		this.id = json.getInt("id");
		this.avatar_url = json.getString("avatar_url");
		this.url = json.getString("url");
		this.htmlUrl = json.getString("html_url");
		this.reposUrl = json.getString("repos_url");
		this.name = json.isNull("name") ? "" : json.getString("name");
		this.location = json.isNull("location") ? "" : json.getString("location");
		this.publicRepos = json.getInt("public_repos");
		this.publicGists = json.getInt("public_gists");
		this.followers = json.getInt("followers");
		this.following = json.getInt("following");
		this.createdAt = DateTime.parse(json.getString("created_at"));
		this.updatedAt = DateTime.parse(json.getString("updated_at"));
	}
}