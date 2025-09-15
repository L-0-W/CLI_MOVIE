package type.api.CLI.WIDGETS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import type.api.CLI.WIDGETS.JSON_OBJECTS.BOOK;

public class CLIENT {

    public List<BOOK> GET_REQUEST(String URL) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(URL))
            .build();

        HttpResponse<String> response = client.send(
            request,
            BodyHandlers.ofString()
        );

        //MOVIE[] movie = Array.map(mapper.readValue(response.body(), MOVIE.class));
        List<BOOK> book = mapper.readValue(
            response.body(),
            new TypeReference<List<BOOK>>() {}
        );

        return book;
    }
}
