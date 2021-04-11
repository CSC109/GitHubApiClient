[Home](./)

# GitHub Api Client Methods Overview
{:.no_toc}

* auto-gen TOC:
{:toc}

## Create Client Object Instance

```java
GitHubApiClient gitHubApiClient = new GitHubApiClient(user, token);
```

The `user` and `token` can be changed anytime using the setter methods.

## Response Objects

All GitHub Api Client responses have their own response type objects.
For `getRepoInfo`, the response object is `GetRepoInfoResponse`.
These response objects parse through what GitHub sends back and maps certain fields.
For example, `GetRepoInfoResponse` has a `description` field:

```java
GitHubApiClient gitHubApiClient = new GitHubApiClient(user, token);
GetRepoInfoResponse repoInfo = gitHubApiClient.getRepoInfo("CSC109", "GitHubApiClient");
System.out.println(repoInfo.getDescription());
```

**Look at the response classes to see which fields are available**.

Since all GitHub responses are in JSON, and the responses are very large for most requests,
each response object has the full response blob as a GSON `JsonObject` object.
A piece of the JSON response from the above call looks like this:

```json
{
  "id": 352434376,
  "name": "GitHubApiClient",
  "full_name": "CSC109/GitHubApiClient",
  "private": false,
  "owner": {
    "login": "CSC109",
    "id": 77031722,
    "node_id": "MDEyOk9yZ2FuaXphdGlvbjc3MDMxNzIy",
    "avatar_url": "https://avatars.githubusercontent.com/u/77031722?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/CSC109"
  },
  "html_url": "https://github.com/CSC109/GitHubApiClient",
  "description": "Client for easy calls to GitHub API"
}
```

You can print the full response out using the `printJson()` method on every response object:

```java
GitHubApiClient gitHubApiClient = new GitHubApiClient(user, token);
GetRepoInfoResponse repoInfo = gitHubApiClient.getRepoInfo("CSC109", "GitHubApiClient");
repoInfo.printJson();
```

You can extract certain values from the JSON response using GSON's `JsonObject` methods in case you need one that isn't mapped to an object field.
It isn't really that fun to do which is why this library abstracted as much of that away from you as possible, but if you really need a field it's a guaranteed way to get it.

Below will get the `description` field's value:

```java
GitHubApiClient gitHubApiClient = new GitHubApiClient(user, token);
JsonObject repoInfo = gitHubApiClient.getRepoInfo("CSC109", "GitHubApiClient");
String description = repoInfo.getJson().get("description").getAsString();
System.out.println(description); // will print out "Client for easy calls to GitHub API"
```

Below will get the `login` field's value (which is the user that created the repo):
```java
GitHubApiClient gitHubApiClient = new GitHubApiClient(user, token);
JsonObject repoInfo = gitHubApiClient.getRepoInfo("CSC109", "GitHubApiClient");
String login = repoInfo.getJson().get("owner").getAsJsonObject().get("login").getAsString();
System.out.println(login); // will print out "CSC109"
```

## Methods

Note: "authenticated user" means the GitHub user whose username/token is being supplied to the client object instance.

### Create Repo

Creates a new repo for the authenticated user.

The below will create a new repo named "NewRepo". The "name" request parameter is required.

```java
RequestParams requestParams = new RequestParams();
requestParams.addParam("name", "NewRepo"); // name of repo

CreateRepoResponse createRepoResponse = gitHubApiClient.createRepo(requestParams);
```

There are a lot of optional request params for repo creation.
Some notable ones:

```java
RequestParams requestParams = new RequestParams();
requestParams.addParam("name", "NewRepo");                   // name of repo
requestParams.addParam("description", "this is a new repo"); // repo description
requestParams.addParam("private", false);                    // if repo is private or not

CreateRepoResponse createRepo = gitHubApiClient.createRepo(requestParams);
```

The request will fail if a repo with the given name already exists.

### Get Repo Info

Gets information about a repo. The owner of the repo and name of the repo must be supplied.
The below will get the repo owned by user CSC109 named GitHubApiClient

```java
GetRepoInfoResponse repoInfo = gitHubApiClient.getRepoInfo("CSC109", "GitHubApiClient");
```

### Update Repo

Updates certain information about a repo based on what is specified in the request params.
The owner of the repo and name of the repo must be supplied.

The below will update the repo owned by user CSC109 named GitHubApiClient. It will change its name to "cat".

```java
RequestParams requestParams = new RequestParams();
requestParams.addParam("name", "cat"); // change name of repo

UpdateRepoResponse updateRepo = gitHubApiClient.updateRepo("CSC109", "GitHubApiClient", requestParams)
```

You are only allowed to update repos that the authenticated user has write access to!

There are a lot of optional request params for repo updating.
Some notable ones:

```java
RequestParams requestParams = new RequestParams();
requestParams.addParam("name", "cat");                // change name of repo
requestParams.addParam("description", "I love cats"); // change description of repo
requestParams.addParam("private", true);              // change if repo is private or not

UpdateRepoResponse updateRepo = gitHubApiClient.updateRepo("CSC109", "GitHubApiClient", requestParams)
```

### Delete Repo

Deletes a repo. 
The owner of the repo and name of the repo must be supplied.

```java
DeleteRepoResponse deleteRepo = gitHubApiClient.deleteRepo("CSC109", GitHubApiClient");
```

You are only allowed to update repos that the authenticated user owns.

Uh be careful with this method.

### List Repo Contributors

Lists all contributors to a repo (e.g. anyone that's ever made a commit will show up in this).
The owner of the repo and name of the repo must be supplied.

```java
ListRepoContributorsResponse contributors = gitHubApiClient.ListRepoContributorsResponse("CSC109", GitHubApiClient", null);
```

### List Repos

Lists all repos the authenticated user has access to.
Note that this does not just include repos the authenticated user owns -- it also includes repos they have permission to.
If the user has been added to a repo, that repo will be included.

```java
ListReposResponse repos = gitHubApiClient.listRepos(null);
```

To narrow it down to just repos the authenticated user owns, a query param `type` can be used to specify "owner".

```java
QueryParams queryParams = new QueryParmas();
queryParams.addParam("type", "owner"); // only list repos that authenticated user owns

ListReposResponse repos = gitHubApiClient.listRepos(queryParams);
```

### List Branches In Repo

Lists all branches in a repo.
The owner of the repo and name of the repo must be supplied.

```java
ListBranchesInRepoResponse branches = gitHubApiClient.listBranchesInRepo("CSC109", "GitHubApiClient", null);
```

### Get Branch Info From Repo

Gets information about a branch in a repo.
The owner of the repo, name of the repo, and branch name must be supplied.

```java
GetBranchInfoResponse branch = gitHubApiClient.getBranchInfoFromRepo("CSC109", "GitHubApiClient", "master");
```

### Rename Branch In Repo

Renames a branch in a repo. The authenticated user must have write permission to this repo.
The owner of the repo, name of the repo, and branch to rename must be supplied.
The new name of the branch must be included as a request param.

```java
RequestParams requestParams = new RequestParams();
requestParams.addParam("new_name", "main"); // change branch to the name "main"


RenameBranchInRepoReponse renameBranch = gitHubApiClient.renameBranch("CSC109", "GitHubApiClient", "master", requestParams);
```

### List Repo Collaborators

Gets all collaborators from a repo. Collaborators are users that have permission to use the repo (such as if they were added as a member).
The owner of the repo and name of the repo must be supplied.

```java
ListRepoCollaboratorsResponse collaborators = gitHubApiClient.listRepoCollaborators("CSC109", "GitHubApiClient"); 
```

### Check if user is a collaborator of a repo

Not much to this one, it will just tell you if a user is a collaborator of a repo or not.
The owner of the repo, name of the repo, and user to check must be supplied.

```java
boolean isUserACollaborator = gitHubApiClient.isUserACollaboratorInRepo("CSC109", "GitHubApiClient", "a-r-t");
```

### Add User to Repo

This will send an invite email to a user to add them as a member to a repo.
The owner of the repo, name of the repo, and user to invite must be supplied.

```java
AddUserToRepoResponse addUser = gitHubApiClient.addUserToRepo("CSC109", "GitHubApiClient", "BillyBob"); 
```

### Remove User from Repo

This will remove a user as a member of a repo.
The owner of the repo, name of the repo, and user to remove must be supplied.

```java
RemoveUserToRepoResponse addUser = gitHubApiClient.removeUserFromRepo("CSC109", "GitHubApiClient", "BillyBob"); 
```

This one is a bit weird, it sometimes takes a bit to actually remove them.

### List Commits in Repo

This will get all commits in a repo.
The owner of the repo and name of the repo must be supplied.

```java
ListCommitsInRepoResponse commits = gitHubApiClient.listCommitsInRepo("CSC109", "GitHubApiClient"); 
```

### Get Commit

This will get info about a commit.
The owner of the repo, name of the repo, and commit hash must be supplied.

```java
GetCommitResponse commit = gitHubApiClient.getCommit("CSC109", "GitHubApiClient", "c1aceef9f491074291f928fb8df6a7bc06755da0", null); 
```

### List Repo Languages

This is a neat one. GitHub actually will tell you the programming language breakdown of a repo.
The owner of the repo and name of the repo must be supplied.

```java
ListRepoLanguagesResponse languages = gitHubApiClient.listRepoLanguages("CSC109", "GitHubApiClient"); 
```

It will tell you which languages are used and how many bytes in all files are written in that language.
An interesting design decision to say the least. :man_shrugging:

### Get User

Gets information about a GitHub user.
The desired user's name must be specified.

```java
GetUserResponse user = gitHubApiClient.getUser("a-r-t");
```

Note that if you get the user for the authenticated user, more info will be returned.
Specifically, number of private repos and total disk space used on GitHub's servers.
If getting info for a different user, those fields will just be zero.

Also, not every user has their email visible on their profile (it's a setting), so you may not get an email back from some users.

### Update User

Updates information for the authenticated user.
There are a bunch of optional request params that can be updated.
Below are examples:

```java
RequestParams requestParams = new RequestParams();
requestParams.addParam("name", "CATS");         // change user's name to "CATS"
requestParams.addParam("email", "cats@qu.edu"); // change user's email to "cats@qu.edu"
requestParams.addParam("bio", "I love cats");   // change user's bio to "I love cats"

UpdateUserResponse updateUser = gitHubApiClient.updateUser(requestParams);
```

### List Pull Requests

This will get all pull requests in a repo.
The owner of the repo and name of the repo must be supplied.

```java
ListPullRequestsResponse listPullRequestsResponse = gitHubApiClient.listPullRequests("CSC109", "GitHubApiClient", null);
```

There are a number of query parameters that can be used to narrow down which pull requests are returned.
The most useful one is `state` which can be set to either "open", "closed", or "all".
By default (if not specified), only open pull requests will be returned.
Below is an example of returning all pull requests regardless of if they are open:

```java
QueryParams queryParams = new QueryParams();
queryParams.addParam("state", "all");

ListPullRequestsResponse listPullRequestsResponse = gitHubApiClient.listPullRequests("CSC109", "GitHubApiClient", queryParams);
```

### Get Pull Request

This will return a particular pull request from a repo.
The owner of the repo, name of the repo, and pull request number must be supplied.
Pull request numbers start at 1.

```java
GetPullRequestResponse getPullRequestResponse = gitHubApiClient.getPullRequest("CSC109", "GitHubApiClient", 1);
```

Additional information is returned for each Pull Request from this method compared to what is returned from the [List Pull Requests](#list-pull-requests) method.

### Get Repo Directory Contents

This will return the contents of a directory in a repo.
The owner of the repo, name of the repo, directory path, and branch name must be supplied.
The directory path must be valid and must NOT begin with a leading `/` or end with a trailing `/`.
To get the root directory, just pass an empty String in `""` for the directory path.

```java
GetRepoDirectoryResponse getRepoDirectoryResponse = gitHubApiClient.getRepoDirectory("CSC109", "GitHubApiClient", "src/git/tools/client", "master");
```

The above example will return all directory/file contents in the directory `src/git/tools/client` of the repo in the `master` branch.

### Get Repo File Content

This will return info and the content of a file in a repo.
The owner of the repo, name of the repo, file path, and branch name must be supplied.
The file path must be valid and must NOT begin with a leading `/`.

```java
GetRepoFileResponse getRepoFileResponse = gitHubApiClient.getRepoFile("CSC109", "GitHubApiClient", "src/git/tools/client/GitHubApiClient.java", "master");
```

The above example will return all directory/file contents in the directory `src/git/tools/client/GitHubApiClient.java` of the repo in the `master` branch. 

### Get All Files in Repo

This will return a list of file info/content for all files in a repo.
The owner of the repo, name of the repo, and branch name must be supplied.

```java
ArrayList<RepoFileContent> repoFileContent = gitHubApiClient.getAllFilesInRepo("CSC109", "GitHubApiClient", "master");
```

### Create File In Repo

This will create a new file in a repo at a specified path.
The owner of the repo, name of the repo, desired file path (includes the new file's name), the branch name, the content of the file (text), and a commit message (since this automatically creates a commit).

```java
CreateFileResponse createFile = gitHubApiClient.createFile("CSC109", "GitHubApiClient", "src/git/tools/client/testfile.java", "master", "new file test", "created a new file");
```

The above example will create a new file named `testfile.java` in the directory `src/git/tools/client` in the `master` branch.
The file will contain the text "new file test" and the commit that is generated for this file creation will have the message "created a new file".

### Update a File in Repo

This will update an existing file in a repo -- replace its current content (text) with new content.
The owner of the repo, name of the repo, file path of the file to update, the branch name, the content of the file to replace what is currently there (text), and a commit message (since this automatically creates a commit).
A valid existing file path must be specified.

```java
UpdateFileResponse updateFile = gitHubApiClient.updateFile("CSC109", "GitHubApiClient", "src/git/tools/client/GitHubApiClient.java", "master", "replacement text", "updated GitHubApiClient.java file");
``` 

The above example will update an existing file named `GitHubApiClient.java` in the directory `src/git/tools/client` in the `master` branch.
The file's content will be replaced with the text "replacement text" and the commit that is generated for this file creation will have the message "updated GitHubApiClient.java file".
