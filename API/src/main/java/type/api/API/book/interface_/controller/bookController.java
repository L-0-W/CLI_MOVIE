package type.api.API.book.interface_.controller;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import java.util.Map;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import type.api.API.book.dados.model.BOOK;
import type.api.API.book.logica.service.bookService;

@RestController
public class bookController {

    @Autowired
    private bookService bookService;

    @GetMapping("/books")
    public ArrayList<BOOK> bookHandler(
        @RequestParam String title,
        HttpServletResponse response
    ) throws IOException, SQLException {
        ArrayList<BOOK> bookArrayList = bookService.getBookByTitle(title);

        if (bookArrayList != null) {
            response.setStatus(200);
            return bookArrayList;
        } else {
            response.setStatus(400);

            ArrayList<BOOK> empty_return = new ArrayList<>();
            BOOK newBook = new BOOK(0, null, null, 0, null, null);

            empty_return.add(newBook);

            return empty_return;
        }
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
