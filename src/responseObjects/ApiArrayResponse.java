package responseObjects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

public class ApiArrayResponse {
    private JsonArray json;

    public ApiArrayResponse(JsonArray json) {
        this.json = json;
        mapParams(json);
    }

    public JsonArray getJson() {
        return json;
    }

    public void printJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String formatted = gson.toJson(json);
        System.out.println(formatted);
    }

    public void mapParams(JsonArray json) { }
}
