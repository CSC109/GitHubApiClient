package github.tools.responseObjects;

import com.google.gson.JsonObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class GetRepoFileResponse extends ApiObjectResponse {
    private String fileName;
    private String filePath;
    private String hash;
    private String text;
    private int size;
    private String url;

    public GetRepoFileResponse(JsonObject json) {
        super(json);
        mapParams(json);
    }

    public void mapParams(JsonObject json) {
        fileName = json.get("name").getAsString();
        filePath = json.get("path").getAsString();
        hash = json.get("sha").getAsString();
        text = new String(Base64.getMimeDecoder().decode(json.get("content").getAsString()), StandardCharsets.UTF_8);
        size = json.get("size").getAsInt();
        url = json.get("html_url").getAsString();
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

    public String getText() {
        return text;
    }

    public int getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }
}
