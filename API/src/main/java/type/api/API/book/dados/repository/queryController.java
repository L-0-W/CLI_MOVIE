package type.api.API.book.dados.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import type.api.API.book.dados.model.BOOK;

@Service
public class queryController {

    private static StringBuilder query = new StringBuilder();

    public queryController insert_into(String table) {
        query.append("insert into " + table);
        return this;
    }

    public queryController fields(String[] fields) {
        query.append("(" + String.join(",", fields) + ")");
        return this;
    }

    public queryController values(String[] values) {
        // se tiver valores maior que o fields, erro, se fields estiver vazio, continua;
        query.append(" values(" + String.join(",", values) + ")");
        return this;
    }

    public void runUpdate() {
        query.append(";");

        try (
            Connection connection = DriverManager.getConnection(
                "jdbc:sqlite:DB/books.db"
            );
            Statement statement = connection.createStatement();
        ) {
            statement.setQueryTimeout(30);

            System.out.println(query.toString());

            statement.executeUpdate(query.toString());

            query.setLength(0);
        } catch (SQLException e) {
            query.setLength(0);
            e.printStackTrace(System.err);
        }
    }

    public queryController select(String[] fields) {
        query.append("select ");

        int counter = 0;
        for (String str_ : fields) {
            query.append(str_);

            if (counter == fields.length - 1) {
                query.append(" from ");
            } else {
                query.append(", ");
            }

            counter++;
        }

        return this;
    }

    public queryController from(String param) {
        query.append(param);

        return this;
    }

    public queryController where(String param) {
        query.append(" where " + param);
        return this;
    }

    public ArrayList<BOOK> runQuery() {
        query.append(";");

        try (
            Connection connection = DriverManager.getConnection(
                "jdbc:sqlite:DB/books.db"
            );
            Statement statement = connection.createStatement();
        ) {
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery(query.toString());
            ArrayList<BOOK> newBookArray = new ArrayList<BOOK>();

            while (rs.next()) {
                String title = rs.getString("title");
                int book_id = rs.getInt("book_id");
                String isbn = rs.getString("isbn");
                int publication_date = rs.getInt("publication_date");
                String cover_image_url = rs.getString("cover_image_url");
                String authors_name = rs.getString("authors_name");

                BOOK novoBook = new BOOK(
                    book_id,
                    isbn,
                    title,
                    publication_date,
                    cover_image_url,
                    authors_name
                );

                newBookArray.add(novoBook);
            }

            query.setLength(0);
            return newBookArray;
        } catch (SQLException e) {
            query.setLength(0);
            e.printStackTrace(System.err);

            return null;
        }
    }
}
