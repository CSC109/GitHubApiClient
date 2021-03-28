import com.google.gson.JsonObject;

public class GitHubApiClient {
    private String baseUrl = "https://api.github.com";
    private BasicAuth basicAuth;

    public GitHubApiClient(String user, String password) {
        this.basicAuth = new BasicAuth(user, password);
    }

    // https://docs.github.com/en/rest/reference/repos#create-a-repository-for-the-authenticated-user
    public JsonObject createRepo(RequestParams requestParams) {
        String endpoint = String.format("%s/user/repos", baseUrl);
        return HttpRequest.post(endpoint, requestParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#get-a-repository
    public JsonObject getRepoInfo(String repoOwner, String repoName) {
        String endpoint = String.format("%s/repos/%s/%s", baseUrl, repoOwner, repoName);
        return HttpRequest.get(endpoint, null, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#update-a-repository
    public JsonObject updateRepo(String repoOwner, String repoName, RequestParams requestParams) {
        String endpoint = String.format("%s/repos/%s/%s", baseUrl, repoOwner, repoName);
        return HttpRequest.patch(endpoint, requestParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#delete-a-repository
    public JsonObject deleteRepo(String repoOwner, String repoName) {
        String endpoint = String.format("%s/repos/%s/%s", baseUrl, repoOwner, repoName);
        return HttpRequest.delete(endpoint, null, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#list-repository-contributors
    public JsonObject listRepoContributors(String repoOwner, String repoName, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/contributors", baseUrl, repoOwner, repoName);
        return HttpRequest.get(endpoint, queryParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#list-repositories-for-a-user
    public JsonObject listRepos(String username, QueryParams queryParams) {
        String endpoint = String.format("%s/users/%s/repos", baseUrl, username);
        return HttpRequest.get(endpoint, queryParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#list-branches
    public JsonObject listBranchesInRepo(String repoOwner, String repoName, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/branches", baseUrl, repoOwner, repoName);
        return HttpRequest.get(endpoint, queryParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#get-a-branch
    public JsonObject getBranchInfoFromRepo(String repoOwner, String repoName, String branchName) {
        String endpoint = String.format("%s/repos/%s/%s/branches/%s", baseUrl, repoOwner, repoName, branchName);
        return HttpRequest.get(endpoint, null, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#rename-a-branch
    public JsonObject renameBranchInRepo(String repoOwner, String repoName, String branchName, RequestParams requestParams) {
        String endpoint = String.format("%s/repos/%s/%s/branches/%s/rename", baseUrl, repoOwner, repoName, branchName);
        return HttpRequest.post(endpoint, requestParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#check-if-a-user-is-a-repository-collaborator
    public JsonObject listRepoCollaborators(String repoOwner, String repoName) {
        String endpoint = String.format("%s/repos/%s/%s/collaborators", baseUrl, repoOwner, repoName);
        return HttpRequest.get(endpoint, null, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#list-repository-collaborators
    public JsonObject checkIfUserIsACollaboratorInRepo(String repoOwner, String repoName, String username) {
        String endpoint = String.format("%s/repos/%s/%s/collaborators/%s", baseUrl, repoOwner, repoName, username);
        return HttpRequest.get(endpoint, null, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#add-a-repository-collaborator
    public JsonObject addUserToRepo(String repoOwner, String repoName, String username) {
        String endpoint = String.format("%s/repos/%s/%s/collaborators/%s", baseUrl, repoOwner, repoName, username);
        return HttpRequest.put(endpoint, null, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#remove-a-repository-collaborator
    public JsonObject removeUserFromRepo(String repoOwner, String repoName, String username) {
        String endpoint = String.format("%s/repos/%s/%s/collaborators/%s", baseUrl, repoOwner, repoName, username);
        return HttpRequest.delete(endpoint, null, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#list-commits
    // note: "sha" is the query param that can be used to specify branch, it's not so obvious
    public JsonObject listCommitsInRepo(String repoOwner, String repoName, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/commits", baseUrl, repoOwner, repoName);
        return HttpRequest.get(endpoint, queryParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#get-a-commit
    public JsonObject getCommit(String repoOwner, String repoName, String commitHash, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/commits/%s", baseUrl, repoOwner, repoName, commitHash);
        return HttpRequest.get(endpoint, queryParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#get-repository-content
    public JsonObject getRepoContent(String repoOwner, String repoName, String filePath, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/contents/%s", baseUrl, repoOwner, repoName, filePath);
        return HttpRequest.get(endpoint, queryParams, basicAuth);
    }

    // https://docs.github.com/en/rest/reference/repos#create-or-update-file-contents
    // note: will either create a new file or replace an existing one
    public JsonObject createOrUpdateRepoFileContents(String repoOwner, String repoName, String filePath, RequestParams requestParams) {
        String endpoint = String.format("%s/repos/%s/%s/contents/%s", baseUrl, repoOwner, repoName, filePath);
        return HttpRequest.put(endpoint, requestParams, basicAuth);
    }

    public JsonObject deleteRepoFile(String repoOwner, String repoName, String filePath, RequestParams requestParams) {
        String endpoint = String.format("%s/repos/%s/%s/contents/%s", baseUrl, repoOwner, repoName, filePath);
        return HttpRequest.delete(endpoint, requestParams, basicAuth);
    }
}



