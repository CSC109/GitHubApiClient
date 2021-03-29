import client.GitHubApiClient;
import client.RequestFailedException;
import client.RequestParams;
import responseObjects.CreateRepoResponse;
import responseObjects.DeleteRepoResponse;

public class testing {
    public static void main(String[] args) {
        GitHubApiClient gitHubApiClient = new GitHubApiClient("a-r-t", "redacted");

        try {
            RequestParams requestParams = new RequestParams();
            requestParams.addParam("name", "testrepoapi");
            CreateRepoResponse createRepoResponse = gitHubApiClient.createRepo(requestParams);
            createRepoResponse.printJson();
        } catch (RequestFailedException e) {
            e.printStackTrace();
        }


        DeleteRepoResponse deleteRepoResponse = gitHubApiClient.deleteRepo("a-r-t", "testrepoapi");
        deleteRepoResponse.printJson();



    }
}
