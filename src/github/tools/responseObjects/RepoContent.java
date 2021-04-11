package github.tools.responseObjects;

public class RepoContent {
    private String name;
    private String path;
    private String hash;
    private int size;
    private String url;
    private String type;

    public RepoContent(String name, String path, String hash, int size, String url, String type) {
        this.name = name;
        this.path = path;
        this.hash = hash;
        this.size = size;
        this.url = url;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
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

    public String getType() {
        return type;
    }
}
