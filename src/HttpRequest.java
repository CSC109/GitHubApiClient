import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    public static JsonObject get(String endpoint, QueryParams queryParams, BasicAuth basicAuth) {
        try {
            String queryString = "";
            if (queryParams != null) {
                queryString = queryParams.toQueryString();
            }
            URL url = new URL(endpoint + queryString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Accept", "application/json");

            if (basicAuth != null) {
                conn.setRequestProperty("Authorization", basicAuth.toAuthHeader());
            }

            conn.setRequestMethod("GET");

            String response = extractResponseOutput(conn);
            return convertResponseToJsonObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static JsonObject post(String endpoint, RequestParams params, BasicAuth basicAuth) {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Accept", "application/json");

            if (basicAuth != null) {
                conn.setRequestProperty("Authorization", basicAuth.toAuthHeader());
            }

            conn.setRequestMethod("POST");


            if (params != null) {
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);
                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = params.toJsonString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            String response = extractResponseOutput(conn);
            return convertResponseToJsonObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static JsonObject put(String endpoint, RequestParams params, BasicAuth basicAuth) {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Accept", "application/json");

            if (basicAuth != null) {
                conn.setRequestProperty("Authorization", basicAuth.toAuthHeader());
            }

            conn.setRequestMethod("PUT");


            if (params != null) {
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);
                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = params.toJsonString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            String response = extractResponseOutput(conn);
            return convertResponseToJsonObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static JsonObject patch(String endpoint, RequestParams params, BasicAuth basicAuth) {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Accept", "application/json");

            if (basicAuth != null) {
                conn.setRequestProperty("Authorization", basicAuth.toAuthHeader());
            }

            conn.setRequestMethod("PATCH");


            if (params != null) {
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);
                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = params.toJsonString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            String response = extractResponseOutput(conn);
            return convertResponseToJsonObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static JsonObject delete(String endpoint, RequestParams params, BasicAuth basicAuth) {
        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Accept", "application/json");

            if (basicAuth != null) {
                conn.setRequestProperty("Authorization", basicAuth.toAuthHeader());
            }

            conn.setRequestMethod("DELETE");

            if (params != null) {
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);
                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = params.toJsonString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            String response = extractResponseOutput(conn);
            return convertResponseToJsonObject(response);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String extractResponseOutput(HttpURLConnection conn) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    private static JsonObject convertResponseToJsonObject(String response) {
        return JsonParser.parseString(response).getAsJsonObject();
    }
}
