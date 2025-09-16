package type.api.API.book.logica.service;

import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import type.api.API.book.RETURNS;
import type.api.API.book.RETURNS_CODES;
import type.api.API.book.RETURNS_MESSAGE;
import type.api.API.book.dados.model.BOOK;
import type.api.API.book.dados.repository.queryController;

@Service
public class bookService {

    @Autowired
    private queryController queryController;

    public RETURNS<ArrayList<BOOK>> getBookByTitle(String str)
        throws SQLException {
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
                .where("title")
                .like_start(str)
                .runQuery();

            RETURNS<ArrayList<BOOK>> returns = new RETURNS<>(
                bookArrayList,
                RETURNS_MESSAGE.ALL_OK,
                RETURNS_CODES.ALL_OK
            );
            return returns;
        } else {
            RETURNS<ArrayList<BOOK>> returns = new RETURNS<>(
                null,
                RETURNS_MESSAGE.PARAM_IS_EMPTY,
                RETURNS_CODES.PARAM_IS_EMPTY
            );
            return returns;
        }
    }
}
