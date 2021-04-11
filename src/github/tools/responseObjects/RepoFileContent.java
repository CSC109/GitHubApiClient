package github.tools.responseObjects;

public class RepoFileContent {
    private String fileName;
    private String filePath;
    private String hash;
    private String text;
    private int size;
    private String url;

    public RepoFileContent(String fileName, String filePath, String hash, String text, int size, String url) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.hash = hash;
        this.text = text;
        this.size = size;
        this.url = url;
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
