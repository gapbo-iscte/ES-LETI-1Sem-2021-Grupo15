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
}