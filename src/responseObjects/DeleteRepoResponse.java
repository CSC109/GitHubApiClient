package responseObjects;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteRepoResponse extends ApiObjectResponse {

    public DeleteRepoResponse(JsonObject json) {
        super(json);
    }
}
