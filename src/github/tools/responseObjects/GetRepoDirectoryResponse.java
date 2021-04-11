package github.tools.responseObjects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;

public class GetRepoDirectoryResponse extends ApiArrayResponse {
    private ArrayList<RepoContent> repoContent = new ArrayList<>();

    public GetRepoDirectoryResponse(JsonArray json) {
        super(json);
        mapParams(json);
    }

    public void mapParams(JsonArray json) {
        for (int i = 0; i < json.size(); i++) {
            JsonObject repoContentJson = (JsonObject) json.get(i);
            String name = repoContentJson.get("name").getAsString();
            String path = repoContentJson.get("path").getAsString();
            String hash = repoContentJson.get("sha").getAsString();
            int size = repoContentJson.get("size").getAsInt();
            String url = repoContentJson.get("html_url").getAsString();
            String type = repoContentJson.get("type").getAsString();
            repoContent.add(new RepoContent(name, path, hash, size, url, type));
        }
    }

    public ArrayList<RepoContent> getRepoContent() {
        return repoContent;
    }
}
