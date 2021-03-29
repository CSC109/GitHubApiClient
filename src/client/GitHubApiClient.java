package client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import responseObjects.*;
import sun.misc.Request;

public class GitHubApiClient {
    private String baseUrl = "https://api.github.com";
    private BasicAuth basicAuth;

    public GitHubApiClient(String user, String password) {
        this.basicAuth = new BasicAuth(user, password);
    }

    // https://docs.github.com/en/rest/reference/repos#create-a-repository-for-the-authenticated-user
    public CreateRepoResponse createRepo(RequestParams requestParams) {
        String endpoint = String.format("%s/user/repos", baseUrl);
        Response response = HttpRequest.post(endpoint, requestParams, basicAuth);
        return new CreateRepoResponse((JsonObject) response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#get-a-repository
    public GetRepoInfoResponse getRepoInfo(String repoOwner, String repoName) {
        String endpoint = String.format("%s/repos/%s/%s", baseUrl, repoOwner, repoName);
        Response response = HttpRequest.get(endpoint, null, basicAuth);
        return new GetRepoInfoResponse((JsonObject)response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#update-a-repository
    public UpdateRepoResponse updateRepo(String repoOwner, String repoName, RequestParams requestParams) {
        String endpoint = String.format("%s/repos/%s/%s", baseUrl, repoOwner, repoName);
        Response response = HttpRequest.patch(endpoint, requestParams, basicAuth);
        return new UpdateRepoResponse((JsonObject)response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#delete-a-repository
    public DeleteRepoResponse deleteRepo(String repoOwner, String repoName) {
        String endpoint = String.format("%s/repos/%s/%s", baseUrl, repoOwner, repoName);
        Response response = HttpRequest.delete(endpoint, null, basicAuth);
        return new DeleteRepoResponse((JsonObject)response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#list-repository-contributors
    public ListRepoContributorsResponse listRepoContributors(String repoOwner, String repoName, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/contributors", baseUrl, repoOwner, repoName);
        Response response = HttpRequest.get(endpoint, queryParams, basicAuth);
        return new ListRepoContributorsResponse((JsonArray) response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#list-repositories-for-a-user
    public ListReposResponse listRepos(String username, QueryParams queryParams) {
        String endpoint = String.format("%s/users/%s/repos", baseUrl, username);
        Response response = HttpRequest.get(endpoint, queryParams, basicAuth);
        return new ListReposResponse((JsonArray) response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#list-branches
    public ListBranchesInRepoResponse listBranchesInRepo(String repoOwner, String repoName, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/branches", baseUrl, repoOwner, repoName);
        Response response = HttpRequest.get(endpoint, queryParams, basicAuth);
        return new ListBranchesInRepoResponse((JsonArray) response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#get-a-branch
    public GetBranchInfoResponse getBranchInfoFromRepo(String repoOwner, String repoName, String branchName) {
        String endpoint = String.format("%s/repos/%s/%s/branches/%s", baseUrl, repoOwner, repoName, branchName);
        Response response = HttpRequest.get(endpoint, null, basicAuth);
        return new GetBranchInfoResponse((JsonObject) response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#rename-a-branch
    public RenameBranchInRepoResponse renameBranchInRepo(String repoOwner, String repoName, String branchName, RequestParams requestParams) {
        String endpoint = String.format("%s/repos/%s/%s/branches/%s/rename", baseUrl, repoOwner, repoName, branchName);
        Response response = HttpRequest.post(endpoint, requestParams, basicAuth);
        return new RenameBranchInRepoResponse((JsonObject) response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#check-if-a-user-is-a-repository-collaborator
    public ListRepoCollaboratorsResponse listRepoCollaborators(String repoOwner, String repoName) {
        String endpoint = String.format("%s/repos/%s/%s/collaborators", baseUrl, repoOwner, repoName);
        Response response = HttpRequest.get(endpoint, null, basicAuth);
        return new ListRepoCollaboratorsResponse((JsonArray) response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#list-repository-collaborators
    public boolean isUserACollaboratorInRepo(String repoOwner, String repoName, String username) {
        try {
            String endpoint = String.format("%s/repos/%s/%s/collaborators/%s", baseUrl, repoOwner, repoName, username);
            Response response = HttpRequest.get(endpoint, null, basicAuth);
            return response.getStatusCode() == 204;
        } catch (RequestFailedException e) {
            if (e.getStatusCode() == 404) {
                return false;
            }
            throw e;
        }
    }

    // https://docs.github.com/en/rest/reference/repos#add-a-repository-collaborator
    public AddUserToRepoResponse addUserToRepo(String repoOwner, String repoName, String username) {
        String endpoint = String.format("%s/repos/%s/%s/collaborators/%s", baseUrl, repoOwner, repoName, username);
        Response response = HttpRequest.put(endpoint, null, basicAuth);
        if (response.getStatusCode() == 201) {
            return new AddUserToRepoResponse((JsonObject) response.getBody());
        }
        if (response.getStatusCode() == 204) {
            System.err.println("User is already a collaborator in this repo");
        }
        return null;
    }

    // https://docs.github.com/en/rest/reference/repos#remove-a-repository-collaborator
    public RemoveUserFromRepoResponse removeUserFromRepo(String repoOwner, String repoName, String username) {
        String endpoint = String.format("%s/repos/%s/%s/collaborators/%s", baseUrl, repoOwner, repoName, username);
        Response response = HttpRequest.delete(endpoint, null, basicAuth);
        return new RemoveUserFromRepoResponse((JsonObject)response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#list-commits
    // note: "sha" is the query param that can be used to specify branch, it's not so obvious
    public ListCommitsInRepoResponse listCommitsInRepo(String repoOwner, String repoName, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/commits", baseUrl, repoOwner, repoName);
        Response response = HttpRequest.get(endpoint, queryParams, basicAuth);
        return new ListCommitsInRepoResponse((JsonArray)response.getBody());
    }

    // https://docs.github.com/en/rest/reference/repos#get-a-commit
    public GetCommitResponse getCommit(String repoOwner, String repoName, String commitHash, QueryParams queryParams) {
        String endpoint = String.format("%s/repos/%s/%s/commits/%s", baseUrl, repoOwner, repoName, commitHash);
        Response response = HttpRequest.get(endpoint, queryParams, basicAuth);
        return new GetCommitResponse((JsonObject)response.getBody());
    }
}



