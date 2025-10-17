package type.api.CLI.WIDGETS;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

public class CLIENT {

    public Optional<JsonNode> GET_REQUEST(String URL) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(URL))
            .header("User-Agent", "JavaApp/1.0 (typeint@proton.me)")
            .header("Accept", "application/json")
            .build();

        HttpResponse<String> response = client.send(
            request,
            BodyHandlers.ofString()
        );

        if (response.statusCode() == 200) {
            JsonNode root = mapper.readTree(response.body());
            Boolean docs = root.has("docs");

            if (!docs) {
                return Optional.empty();
            }

            return Optional.of(root);
        }

        return Optional.empty();
    }
}
