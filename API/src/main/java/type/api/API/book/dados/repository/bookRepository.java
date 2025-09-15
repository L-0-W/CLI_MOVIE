package type.api.API.book.dados.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Repository;

@Repository
public class bookRepository {

    //private static Connection connection;
    public static Statement globalStatement;

    public static void makeCon() {
        try (
            // create a database connection
            Connection connection = DriverManager.getConnection(
                "jdbc:sqlite:DB/books.db"
            );
            Statement statement = connection.createStatement();
        ) {
            statement.setQueryTimeout(30);
            globalStatement = statement;
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }

    /*

    ResultSet rs = statement.executeQuery("select * from books");
    while (rs.next()) {
        // read the result set
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
    }

    */
}
