package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

/**
 * Contem os URLs dos endpoints do API do GitHub.
 * @author manuel-covas
 */

public class GithubEndpoints {
	
	/** URL base do API do GitHub. */
	private final static String BASE_URL = "https://api.github.com";
	
	
	/** 
	 * URL do endpoint {@code /user}. <br>
	 * Este endpoint devolve os detalhes do utilizador autenticado.
	 */
	public final static String GET_SELF_USER_URL = BASE_URL + "/user";
	
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
}