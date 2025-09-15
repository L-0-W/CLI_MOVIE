package type.api.API.movie.interface_.controller;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import type.api.API.movie.logica.service.movieService;

@RestController
public class movieController {

    @Autowired
    private movieService movieService;

    @GetMapping("/movies")
    public void moviesHandler(HttpServletResponse response) throws IOException {
        movieService.createDB();
    }

    /*
public ResponseEntity<List<Map<String, Object>>> moviesHandler(
    HttpServletResponse response
) throws IOException {
    ClassPathResource resource = new ClassPathResource("movies.json");
    InputStream input = resource.getInputStream();

    List<Map<String, Object>> data = objectMapper.readValue(
        input,
        new TypeReference<>() {}
    );

    return ResponseEntity.ok(data);
}
*/
}
