import client.GitHubApiClient;
import client.QueryParams;
import client.RequestFailedException;
import client.RequestParams;
import responseObjects.*;

public class testing {
    public static void main(String[] args) {
        GitHubApiClient gitHubApiClient = new GitHubApiClient("a-r-t", "REDACTED");

        /*
        try {
            RequestParams requestParams = new RequestParams();
            requestParams.addParam("name", "testrepoapi");
            CreateRepoResponse createRepoResponse = gitHubApiClient.createRepo(requestParams);
            createRepoResponse.printJson();
        } catch (RequestFailedException e) {
            e.printStackTrace();
        }
        */

        /*
        GetRepoInfoResponse getRepoInfoResponse = gitHubApiClient.getRepoInfo("CSC109", "GitHubApiClient");
        getRepoInfoResponse.printJson();
        System.out.println(getRepoInfoResponse.getDescription());
        */


        /*
        RequestParams requestParams = new RequestParams();
        requestParams.addParam("name", "HelloWorld");
        UpdateRepoResponse updateRepoResponse = gitHubApiClient.updateRepo("CSC109", "HelloWorld", requestParams);
        updateRepoResponse.printJson();
        */

        /*
        DeleteRepoResponse deleteRepoResponse = gitHubApiClient.deleteRepo("a-r-t", "testrepoapi");
        deleteRepoResponse.printJson();
        */

        /*
        ListRepoContributorsResponse listRepoContributorsResponse = gitHubApiClient.listRepoContributors("BrianCarducci", "DiscordBot", null);
        listRepoContributorsResponse.printJson();
        System.out.println(listRepoContributorsResponse.getContributors().get(0).getUsername());
        */


        /*
        QueryParams queryParams = new QueryParams();
        queryParams.addParam("type", "private");
        ListReposResponse listReposResponse = gitHubApiClient.listRepos(queryParams);
        listReposResponse.printJson();
        */

        /*
        ListBranchesInRepoResponse listBranchesInRepoResponse = gitHubApiClient.listBranchesInRepo("CSC109", "DmvSimulator", null);
        listBranchesInRepoResponse.printJson();
        System.out.println(listBranchesInRepoResponse.getBranches().get(0).getName());
         */

        /*
        GetBranchInfoResponse getBranchInfoResponse = gitHubApiClient.getBranchInfoFromRepo("CSC109", "DmvSimulator", "master");
        getBranchInfoResponse.printJson();
        System.out.println(getBranchInfoResponse.getLatestCommitAuthorEmail());
         */

        /*
        ListRepoCollaboratorsResponse listRepoCollaboratorsResponse = gitHubApiClient.listRepoCollaborators("BrianCarducci", "DiscordBot");
        listRepoCollaboratorsResponse.printJson();
         */

        /* update branch goes here */

        /*
        boolean isACollaborator = gitHubApiClient.isUserACollaboratorInRepo("BrianCarducci", "DiscordBot", "a-r-t");
        System.out.println("Is a collaborator: " + isACollaborator);
         */

        /*
        AddUserToRepoResponse addUserToRepoResponse = gitHubApiClient.addUserToRepo("CSC109", "TestRepo", "BrianCarducci");
        addUserToRepoResponse.printJson();
         */

        /*
        RemoveUserFromRepoResponse removeUserFromRepoResponse = gitHubApiClient.removeUserFromRepo("CSC109", "TestRepo", "BrianCarducci");
        removeUserFromRepoResponse.printJson();
         */

        /*
        ListCommitsInRepoResponse listCommitsInRepoResponse = gitHubApiClient.listCommitsInRepo("CSC109", "DmvSimulator", null);
        listCommitsInRepoResponse.printJson();
        */

        /*
        GetCommitResponse getCommitResponse = gitHubApiClient.getCommit("CSC109", "DmvSimulator", "3ba49d56155ec17d695d8b26dff96e1ae67851f4", null);
        getCommitResponse.printJson();
         */

        /*
        ListRepoLanguagesResponse listRepoLanguagesResponse = gitHubApiClient.listRepoLanguages("a-r-t", "SER-225-Game");
        listRepoLanguagesResponse.printJson();
        System.out.println(listRepoLanguagesResponse.getLanguages().get(0).getLanguageName());
         */

        /*
        GetUserResponse getUserResponse = gitHubApiClient.getUser("BrianCarducci");
        getUserResponse.printJson();
         */

        /* UpdateUser here */
    }
}
