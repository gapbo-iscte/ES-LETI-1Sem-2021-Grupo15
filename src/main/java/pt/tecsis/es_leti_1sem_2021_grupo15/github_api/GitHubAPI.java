package pt.tecsis.es_leti_1sem_2021_grupo15.github_api;

import java.io.IOException;
import java.util.Base64;

import javax.naming.AuthenticationException;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pt.tecsis.es_leti_1sem_2021_grupo15.github_api.auth.GitHubCredentials;

public class GitHubAPI {
	
	/**
	 * Devolve os detalhes do utilizador indicado.
	 * @param  username - nome de utilizador ({@link String})
	 * @return {@link GitHubUser}
	 * @throws IOException
	 * @throws AuthenticationException
	 */
	
	public static GitHubUser getUser(String username) throws IOException, Exception {
		
		String userURL = GithubEndpoints.GET_USER_URL(username); 
		
		Request.Builder requestBuilder = new Request.Builder().url(userURL).addHeader("Accept", "application/json");
		
		Request request = requestBuilder.build();
		Response response = new OkHttpClient().newCall(request).execute();
		
		if (response.code() != 200)
			throw new Exception("Obtenção dos detalhes do utilizador GitHub falhou: HTTP Status " + response.code() + " " + response.message());
		
		return new GitHubUser(response.body().string());
	}
	
	
	/**
	 * Devolve os detalhes do utilizador correspondente às credenciais indicadas.
	 * @param  credentials - credenciais a utilizar ({@link GitHubCredentials})
	 * @return {@link GitHubUser}
	 * @throws IOException
	 * @throws AuthenticationException
	 */
	
	public static GitHubUser getSelf(GitHubCredentials credentials) throws IOException, AuthenticationException {
		
		String selfURL = GithubEndpoints.GET_SELF_USER_URL; 
		
		Request.Builder requestBuilder = new Request.Builder().url(selfURL).addHeader("Accept", "application/json");
		
		Request request = credentials.authenticateRequest(requestBuilder).build();
		Response response = credentials.getHttpClient().newCall(request).execute();
		
		if (response.code() != 200)
			throw new AuthenticationException("Obtenção dos detalhes do utilizador GitHub falhou: HTTP Status " + response.code() + " " + response.message());
		
		return new GitHubUser(response.body().string());
	}
	
	
	/**
	 * Devolve os detalhes do repositório indicado.
	 * @param  owner - nome de utilizador do dono do repositório ({@link String})
	 * @param  repository - nome do repositório ({@link String})
	 * @param  credentials - credenciais a utilizar ou {@code null} para aceder sem credenciais ({@link GitHubCredentials})
	 * @throws IOException
	 * @throws AuthenticationException
	 */
	
	public static GitHubRepository getRepository(String owner, String repository, GitHubCredentials credentials) throws IOException, AuthenticationException {
		
		String repositoryURL = GithubEndpoints.GET_REPOSITORY_URL(owner, repository);
		
		Request.Builder requestBuilder = new Request.Builder().url(repositoryURL).addHeader("Accept", "application/json");
		Response response;
		
		if (credentials == null) {
			Request request = requestBuilder.build();
			response = new OkHttpClient().newCall(request).execute();
		} else {
			Request request = credentials.authenticateRequest(requestBuilder).build();
			response = credentials.getHttpClient().newCall(request).execute();
		}
		
		if (response.code() != 200)
			throw new AuthenticationException("Obtenção dos detalhes do repositório " + owner + "/" + repository + " falhou: HTTP Status " + response.code() + " " + response.message());
		
		return new GitHubRepository(response.body().string());
	}
	
	
	/**
	 * Devolve o conteúdo do ficheiro requisitado.
	 * @param  owner - nome de utilizador do dono do repositório ({@link String})
	 * @param  repository - nome do repositório ({@link String})
	 * @param  path - caminho para o ficheiro ({@link String})
	 * @param  credentials - credenciais a utilizar ou {@code null} para aceder sem credenciais ({@link GitHubCredentials})
	 * @return o conteúdo do ficheiro ({@link String})
	 * @throws IOException
	 * @throws AuthenticationException
	 */
	
	public static String getRepositoryFile(String owner, String repository, String path, GitHubCredentials credentials) throws IOException, AuthenticationException {
		
		String contentURL = GithubEndpoints.GET_REPOSITORY_CONTENT_URL(owner, repository, path);
		
		Request.Builder requestBuilder = new Request.Builder().url(contentURL).addHeader("Accept", "application/json");
		Response response;
		
		if (credentials == null) {
			Request request = requestBuilder.build();
			response = new OkHttpClient().newCall(request).execute();
		} else {
			Request request = credentials.authenticateRequest(requestBuilder).build();
			response = credentials.getHttpClient().newCall(request).execute();
		}
		
		if (response.code() != 200)
			throw new AuthenticationException("Obtenção do conteúdo do ficheiro " + path + " falhou: HTTP Status " + response.code() + " " + response.message());
		
		JSONObject fileObject = new JSONObject(response.body().string());
		String base64Content = fileObject.getString("content").replaceAll("\n", "");
		
		return new String(Base64.getDecoder().decode(base64Content));
	}
	
	
	/**
	 * Devolve o commit correspondente ao URL indicado.
	 * @param  commitURL - url do commit pretendido ({@link String})
	 * @param  credentials - credenciais a utilizar ou {@code null} para aceder sem credenciais ({@link GitHubCredentials})
	 * @return o commit requisitado ({@link GitHubCommit})
	 * @throws IOException
	 * @throws AuthenticationException
	 */
	
	public static GitHubCommit getCommit(String commitURL, GitHubCredentials credentials) throws IOException, AuthenticationException {
		
		Request.Builder requestBuilder = new Request.Builder().url(commitURL).addHeader("Accept", "application/json");
		Response response;
		
		if (credentials == null) {
			Request request = requestBuilder.build();
			response = new OkHttpClient().newCall(request).execute();
		} else {
			Request request = credentials.authenticateRequest(requestBuilder).build();
			response = credentials.getHttpClient().newCall(request).execute();
		}
		
		if (response.code() != 200)
			throw new AuthenticationException("Obtenção do commit de URL " + commitURL + " falhou: HTTP Status " + response.code() + " " + response.message());
		
		return new GitHubCommit(response.body().string());
	}
	
	
	/**
	 * Devolve o commit pretendido.
	 * @param  owner - nome de utilizador do dono do repositório ({@link String})
	 * @param  repository - nome do repositório ({@link String})
	 * @param  commitHash - hash do commit pretendido ({@link String})
	 * @param  credentials - credenciais a utilizar ou {@code null} para aceder sem credenciais ({@link GitHubCredentials})
	 * @return o commit requisitado ({@link GitHubCommit})
	 * @throws IOException
	 * @throws AuthenticationException
	 */
	
	public static GitHubCommit getCommit(String owner, String repository, String commitHash, GitHubCredentials credentials) throws IOException, AuthenticationException {
		
		String commitURL = GithubEndpoints.GET_COMMIT_URL(owner, repository, commitHash);
		
		return getCommit(commitURL, credentials);
	}
	
	
	/**
	 * Devolve as tags do repositório indicado.
	 * @param  owner - nome de utilizador do dono do repositório ({@link String})
	 * @param  repository - nome do repositório ({@link String})
	 * @param  credentials - credenciais a utilizar ou {@code null} para aceder sem credenciais ({@link GitHubCredentials})
	 * @return as tags do repositório indicado ({@link GitHubTag}[])
	 * @throws IOException
	 * @throws AuthenticationException
	 */
	
	public static GitHubTag[] getRepositoryTags(String owner, String repository, GitHubCredentials credentials) throws IOException, AuthenticationException {
		
		String tagsURL = GithubEndpoints.GET_REPOSITORY_TAGS_URL(owner, repository);
		
		Request.Builder requestBuilder = new Request.Builder().url(tagsURL).addHeader("Accept", "application/json");
		Response response;
		
		if (credentials == null) {
			Request request = requestBuilder.build();
			response = new OkHttpClient().newCall(request).execute();
		} else {
			Request request = credentials.authenticateRequest(requestBuilder).build();
			response = credentials.getHttpClient().newCall(request).execute();
		}
		
		if (response.code() != 200)
			throw new AuthenticationException("Obtenção do commit de URL " + tagsURL + " falhou: HTTP Status " + response.code() + " " + response.message());
		
		// Parse response
		
		JSONArray responseJSON = new JSONArray(response.body().string());
		GitHubTag tags[] = new GitHubTag[responseJSON.length()];
		
		for (int i = 0; i < tags.length; i++)
			tags[i] = new GitHubTag(responseJSON.get(i).toString());
		
		return tags;
	}
	
	
	/**
	 * Devolve os branches do repositório indicado.
	 * @param  owner - nome de utilizador do dono do repositório ({@link String})
	 * @param  repository - nome do repositório ({@link String})
	 * @param  credentials - credenciais a utilizar ou {@code null} para aceder sem credenciais ({@link GitHubCredentials})
	 * @return as tags do repositório indicado ({@link GitHubBranch}[])
	 * @throws IOException
	 * @throws AuthenticationException
	 */
	
	public static GitHubBranch[] getRepositoryBranches(String owner, String repository, GitHubCredentials credentials) throws IOException, AuthenticationException {
		
		String branchesURL = GithubEndpoints.GET_BRANCHES_URL(owner, repository);
		
		Request.Builder requestBuilder = new Request.Builder().url(branchesURL).addHeader("Accept", "application/json");
		Response response;
		
		if (credentials == null) {
			Request request = requestBuilder.build();
			response = new OkHttpClient().newCall(request).execute();
		} else {
			Request request = credentials.authenticateRequest(requestBuilder).build();
			response = credentials.getHttpClient().newCall(request).execute();
		}
		
		if (response.code() != 200)
			throw new AuthenticationException("Obtenção dos branches do repositório " + owner + " / " + repository + " falhou: HTTP Status " + response.code() + " " + response.message());
		
		// Parse response
		
		JSONArray responseJSON = new JSONArray(response.body().string());
		GitHubBranch branches[] = new GitHubBranch[responseJSON.length()];
		
		for (int i = 0; i < branches.length; i++)
			branches[i] = new GitHubBranch(responseJSON.get(i).toString());
		
		return branches;
	}
	
	
	/**
	 * Devolve os commits do branch do repositório indicado.
	 * @param  owner - nome de utilizador do dono do repositório ({@link String})
	 * @param  repository - nome do repositório ({@link String})
	 * @param  branch - nome do branch ({@link String})
	 * @param  credentials - credenciais a utilizar ou {@code null} para aceder sem credenciais ({@link GitHubCredentials})
	 * @return os commits deste branch ({@link GitHubCommit}[])
	 * @throws IOException 
	 * @throws AuthenticationException 
	 */
	
	public static GitHubCommit[] getBranchCommits(String owner, String repository, String branch, GitHubCredentials credentials) throws IOException, AuthenticationException {
		
		String branchCommitsURL = GithubEndpoints.GET_BRANCH_COMMITS_URL(owner, repository, branch);
		
		Request.Builder requestBuilder = new Request.Builder().url(branchCommitsURL).addHeader("Accept", "application/json");
		Response response;
		
		if (credentials == null) {
			Request request = requestBuilder.build();
			response = new OkHttpClient().newCall(request).execute();
		} else {
			Request request = credentials.authenticateRequest(requestBuilder).build();
			response = credentials.getHttpClient().newCall(request).execute();
		}
		
		if (response.code() != 200)
			throw new AuthenticationException("Obtenção dos branches do repositório " + owner + "/" + repository + " falhou: HTTP Status " + response.code() + " " + response.message());
		
		// Parse response
		JSONArray responseJSON = new JSONArray(response.body().string());
		GitHubCommit commits[] = new GitHubCommit[responseJSON.length()];
				
		for (int i = 0; i < commits.length; i++)
			commits[i] = new GitHubCommit(responseJSON.getJSONObject(i).toString());
		return commits;
	}
}