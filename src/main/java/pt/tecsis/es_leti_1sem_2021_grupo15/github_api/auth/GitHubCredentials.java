package pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Contem credenciais que permitem realizar ações em nome de uma conta GitHub.
 * @author manuel-covas
 */

public class GitHubCredentials {
	
	private final OkHttpClient httpClient;
	private final String personalAccessToken;
	
	
	/**
	 * Constroi uma instância desta classe com as credenciais indicadas.
	 * @param personalAccessToken ({@link String})
	 */
	
	public GitHubCredentials(String personalAccessToken) {
		
		if (personalAccessToken == null)
			throw new NullPointerException("O personal access token não pode ser nulo.");
		
		this.httpClient = new OkHttpClient();
		this.personalAccessToken = personalAccessToken;
	}
	
	
	/**
	 * Devolve o cliente OkHttp utilizado para executar os pedidos em nome desta conta.
	 * @return {@link OkHttpClient}
	 */
	
	public OkHttpClient getHttpClient() {
		return httpClient;
	}
	
	
	/**
	 * Adiciona ao Builder do pedido HTTP o header {@code Authorization} com as credenciais necessárias.
	 * @param {@link Request.Builder}
	 * @return {@link Request.Builder}
	 */
	
	public Request.Builder authenticateRequest(Request.Builder requestBuilder) {
		return requestBuilder.addHeader("Authorization", "token " + personalAccessToken);
	}
}