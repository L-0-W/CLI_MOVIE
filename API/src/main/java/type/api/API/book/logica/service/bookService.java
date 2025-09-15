package type.api.API.book.logica.service;

import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import type.api.API.book.dados.model.BOOK;
import type.api.API.book.dados.repository.queryController;

@Service
public class bookService {

    @Autowired
    private queryController queryController;

    public ArrayList<BOOK> getBookByTitle(String str) throws SQLException {
        if (str != null) {
            System.out.println(str);
            String[] campos = { "*" };

            /*
            String[] ttt = {
                "20",
                "'1239oi2'",
                "'book'",
                "28",
                "'http://'",
                "'Luiz'",
            };
            */

            //queryController.insert_into("books").values(ttt).runUpdate();

            ArrayList<BOOK> bookArrayList = queryController
                .select(campos)
                .from("books")
                .where("title == " + str)
                .runQuery();

            return bookArrayList;
        } else {
            // FAZER MELHOR RETORNO DE ERRO COM INTERFACE
            return null;
        }
    }
}
