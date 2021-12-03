package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

import java.io.IOException;

import org.joda.time.DateTime;
import org.json.JSONObject;

/**
 * Representa um repositório de um repositório GitHub.
 * @author manuel-covas
 */

public class GitHubRepository {

	public final String nome, descricao;
	public final DateTime createdAt;
	private final String ownerUsername;
	
	/**
	 * Interpreta os dados devolvidos pelo GitHub em formato JSON e
	 * constroi uma instância desta classe com os mesmos.
	 * @param repositoryJSON {@link String}
	 */
	
	public GitHubRepository(String repositoryJSON) {
		
		JSONObject json = new JSONObject(repositoryJSON);
		
		this.nome = json.getString("name");
		this.descricao = json.getString("description");
		this.createdAt = DateTime.parse(json.getString("created_at"));
		this.ownerUsername = json.getJSONObject("owner").getString("login");
	}
	
	
	/**
	 * Devolve o dono deste repositório.
	 * @return {@link GitHubUser}
	 * @throws IOException
	 * @throws Exception
	 */
	
	public GitHubUser getOwner() throws IOException, Exception {
		return GitHubAPI.getUser(ownerUsername);
	}
}
