package github.tools.responseObjects;

import com.google.gson.JsonObject;

public class CreateFileResponse extends ApiObjectResponse {
    private String fileName;
    private String filePath;
    private String hash;
    private int size;
    private String url;

    public CreateFileResponse(JsonObject json) {
        super(json);
        mapParams(json);
    }

    public void mapParams(JsonObject json) {
        JsonObject contentJson = json.get("content").getAsJsonObject();
        fileName = contentJson.get("name").getAsString();
        filePath = contentJson.get("path").getAsString();
        hash = contentJson.get("sha").getAsString();
        size = contentJson.get("size").getAsInt();
        url = contentJson.get("html_url").getAsString();
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getHash() {
        return hash;
    }

    public int getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }
}
