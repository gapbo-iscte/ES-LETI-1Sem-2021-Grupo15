package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

import org.json.JSONArray;
import org.json.JSONObject;

import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth.GitHubCredentials;

/**
 * Representa um branch de um repositório GitHub.
 * @author manuel-covas
 */

public class GitHubBranch {

	public final String nome, currentCommitHash;
	
	/**
	 * Interpreta os dados devolvidos pelo GitHub em formato JSON e
	 * constroi uma instância desta classe com os mesmos.
	 * @param branchJSON {@link String}
	 */
	
	public GitHubBranch(String branchJSON) {
		
		JSONObject json = new JSONObject(branchJSON);
		
		this.nome = json.getString("name");
		this.currentCommitHash = json.getJSONObject("commit").getString("sha");
	}
}