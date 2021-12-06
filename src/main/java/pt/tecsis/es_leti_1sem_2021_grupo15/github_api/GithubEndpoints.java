package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

/**
 * Contem os URLs dos endpoints do API do GitHub.
 * @author manuel-covas
 */

public class GithubEndpoints {
	
	/** URL base do API do GitHub. */
	private final static String BASE_URL = "https://api.github.com";
	
	/** 
	 * URL do endpoint {@code /user/<username>}. <br>
	 * Este endpoint devolve os detalhes do utilizador indicado.
	 */
	public final static String GET_USER_URL(String username) {
		return BASE_URL + "/users/" + username;
	}
	
	/** 
	 * URL do endpoint {@code /user}. <br>
	 * Este endpoint devolve os detalhes do utilizador autenticado.
	 */
	public final static String GET_SELF_USER_URL = BASE_URL + "/user";
	
	/** 
	 * URL do endpoint {@code repos/<owner>/<repository>}. <br>
	 * Este endpoint devolve os detalhes do repositório indicado.
	 */
	public static String GET_REPOSITORY_URL(String owner, String repository) {
		return BASE_URL + "/repos/" + owner + "/" + repository;
	}
	
	/** 
	 * URL do endpoint {@code repos/<owner>/<repository>/contents/<path>}. <br>
	 * Este endpoint devolve o contúdo do ficheiro requisitado.
	 */
	public static String GET_REPOSITORY_CONTENT_URL(String owner, String repository, String path) {
		return BASE_URL + "/repos/" + owner + "/" + repository + "/contents/" + path;
	}
	
	/** 
	 * URL do endpoint {@code repos/<owner>/<repository>/tags}. <br>
	 * Este endpoint devolve as tags do repositório especificado.
	 */
	public static String GET_REPOSITORY_TAGS_URL(String owner, String repository) {
		return BASE_URL + "/repos/" + owner + "/" + repository + "/tags";
	}
	
	/** 
	 * URL do endpoint {@code repos/<owner>/<repository>/commits/<ref>}. <br>
	 * Este endpoint devolve os detalhes do commit requisitado.
	 */
	public static String GET_COMMIT_URL(String owner, String repository, String hash) {
		return BASE_URL + "/repos/" + owner + "/" + repository + "/commits/" + hash;
	}
	
	/** 
	 * URL do endpoint {@code repos/<owner>/<repository>/branches}. <br>
	 * Este endpoint devolve os branches do repositório indicado.
	 */
	public static String GET_BRANCHES_URL(String owner, String repository) {
		return BASE_URL + "/repos/" + owner + "/" + repository + "/branches";
	}
	
	/** 
	 * URL do endpoint {@code repos/<owner>/<repository>/commits?sha=<branch>}. <br>
	 * Este endpoint devolve os branches do branch do repositório indicado.
	 */
	public static String GET_BRANCH_COMMITS_URL(String owner, String repository, String branch) {
		return BASE_URL + "/repos/" + owner + "/" + repository + "/commits?sha=" + branch;
	}
}