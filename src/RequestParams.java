import com.google.gson.Gson;

import java.util.LinkedHashMap;

public class RequestParams {
    private LinkedHashMap<String,Object> params;

    public RequestParams() {
        params = new LinkedHashMap<>();
    }

    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(params);
    }
}
