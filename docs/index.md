# GitHub Api Client

## What is this?

This repo contains a collection of classes that will allow for easy calls out to the [GitHub API](https://docs.github.com/en).

## Requirements

- Java 8 or higher
- [GSON package](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.6/gson-2.8.6.jar)
    - Place GSON `.jar` file in project build path

## Authentication

For authentication, which GitHub requires in order to use their API, you need to generate a GitHub API token for your account.
Click [here](./access-token) to learn how to generate an access token.

## Usage

A client.GitHubApiClient object instance can be created like so, where `username` is your GitHub username and `password` is your GitHub API access token:

```java
client.GitHubApiClient gitHubApiClient = new client.GitHubApiClient(username, password);
```

From here, you can call any of the available methods.
For example, if you wanted to get info about a repo, you would call the `getRepoInfo` method.
The below example will return info for this repo:

```java
client.GitHubApiClient gitHubApiClient = new client.GitHubApiClient(username, password);
JsonObject repoInfo = gitHubApiClient.getRepoInfo("CSC109", "client.GitHubApiClient");
System.out.println(repoInfo);
```

All GitHub Api Client responses are a GSON `JsonObject`. A piece of the JSON response from the above call looks like this:

```json
{
  "id": 352434376,
  "name": "client.GitHubApiClient",
  "full_name": "CSC109/client.GitHubApiClient",
  "private": false,
  "owner": {
    "login": "CSC109",
    "id": 77031722,
    "node_id": "MDEyOk9yZ2FuaXphdGlvbjc3MDMxNzIy",
    "avatar_url": "https://avatars.githubusercontent.com/u/77031722?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/CSC109"
  },
  "html_url": "https://github.com/CSC109/client.GitHubApiClient",
  "description": "Client for easy calls to GitHub API"
}
```

You can extract certain values from the JSON response using GSON's `JsonObject` methods:

Below will get the `description` field's value:
```java
client.GitHubApiClient gitHubApiClient = new client.GitHubApiClient(username, password);
JsonObject repoInfo = gitHubApiClient.getRepoInfo("CSC109", "client.GitHubApiClient");
String description = repoInfo.get("description");
System.out.println(description); // will print out "Client for easy calls to GitHub API"
```

Below will get the `login` field's value (which is the user that created the repo):
```java
client.GitHubApiClient gitHubApiClient = new client.GitHubApiClient(username, password);
JsonObject repoInfo = gitHubApiClient.getRepoInfo("CSC109", "client.GitHubApiClient");
String login = repoInfo.get("owner").getAsJsonObject().get("login");
System.out.println(login); // will print out "CSC109"
```



