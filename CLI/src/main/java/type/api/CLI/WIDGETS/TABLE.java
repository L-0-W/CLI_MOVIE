package type.api.CLI.WIDGETS;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import type.api.CLI.WIDGETS.JSON_OBJECTS.BOOK;

public class TABLE {

    protected int tableWidthSize = 10;
    protected int tableHeigthSize = 10;
    protected Terminal terminal;
    protected List<BOOK> elements_draw = new ArrayList<>();
    protected String pesquisar_por = "Frase";
    protected int selected = 0;

    public TABLE setTerminal(Terminal terminal) {
        this.terminal = terminal;
        return this;
    }

    public TABLE setTableWidthSize(int tableSize) {
        this.tableWidthSize = tableSize;
        return this;
    }

    public TABLE setTableHeigthSize(int tableSize) {
        this.tableHeigthSize = tableSize;
        return this;
    }

    public void draw() {
        List<String> str = new ArrayList<>();

        for (int i = 0; i < tableHeigthSize; i++) {
            if (i == 0) {
                str.add("┌" + "─".repeat(tableWidthSize) + "┐");
            } else if (i == (tableHeigthSize - 1)) {
                str.add("└" + "─".repeat(tableWidthSize) + "┘");
            } else if (i == 1) {
                str.add(
                    "│" +
                        " " +
                        "Book Name" +
                        " ".repeat(5) +
                        "│" +
                        " ".repeat(5) +
                        "Book Id" +
                        " ".repeat(((tableWidthSize - 1) - (20 + 7))) +
                        "│"
                );

                str.add("│" + "─".repeat(tableWidthSize) + "│");
            } else if (
                !elements_draw.isEmpty() &&
                elements_draw.size() >= (i - 1) &&
                elements_draw.get(0).getAuthorsName() != null
            ) {
                var current_element = elements_draw.get(i - 2);
                str.add(
                    "│" +
                        " ".repeat(5) +
                        current_element.getTitle() +
                        " ".repeat(5) +
                        current_element.getBook_Id() +
                        " ".repeat(5) +
                        current_element.getAuthorsName() +
                        " ".repeat(5) +
                        current_element.getISBN() +
                        " ".repeat(5) +
                        current_element.getPublicationDate() +
                        " ".repeat(5) +
                        "│" +
                        " ".repeat(
                            tableWidthSize -
                                (current_element.getTitle().length() +
                                    current_element.getAuthorsName().length() +
                                    current_element.getISBN().length() +
                                    10) -
                                31
                        ) +
                        "│"
                );

                str.add("│" + "─".repeat(tableWidthSize) + "│");
            } else {
                str.add("│" + " ".repeat(tableWidthSize) + "│");
            }
        }

        AttributedStringBuilder asb = new AttributedStringBuilder();

        asb
            .style(
                AttributedStyle.DEFAULT.bold().foreground(AttributedStyle.BLUE)
            )
            .append("");

        terminal.writer().println(asb.toAnsi(terminal));

        int index = 0;
        for (String line : str) {
            terminal
                .writer()
                .print(
                    String.format(
                        "\033[%d;%dH%s",
                        (((terminal.getHeight() - terminal.getHeight()) + 10) +
                            index),
                        ((terminal.getWidth() - line.length()) / 2),
                        line
                    )
                );
            index++;
        }
    }

    private String checkIAField(
        Optional<JsonNode> ai_array_optional,
        BOOK book_converted
    ) {
        if (ai_array_optional.isPresent()) {
            JsonNode ai_array = ai_array_optional.get();
            for (
                int index_ia_array = 0;
                index_ia_array < ai_array.size();
                index_ia_array++
            ) {
                if (ai_array.get(index_ia_array).asText().contains("isbn_")) {
                    return ai_array.get(index_ia_array).asText();
                }
            }
        }

        return "Vazio";
    }

    public void use_elements(
        List<JsonNode> books_draw,
        String pesquisar_por,
        int selected
    ) {
        this.selected = selected;
        this.pesquisar_por = pesquisar_por;
        this.elements_draw.removeAll(this.elements_draw);

        String query = books_draw.get(0).get("q").asText();
        JsonNode docs = books_draw.get(0).get("docs");

        int result_size = books_draw.get(0).get("num_found").asInt() > 10
            ? 10
            : books_draw.get(0).get("num_found").asInt();

        List<BOOK> list_books_conveted = new ArrayList<BOOK>();

        for (int i = 0; i < result_size; i++) {
            BOOK book_converted = new BOOK();

            Optional<JsonNode> ai_array = docs.get(i).has("ia")
                ? Optional.of(docs.get(i).get("ia"))
                : Optional.empty();

            String publicationData = docs.get(i).has("first_publish_year")
                ? docs.get(i).get("first_publish_year").asText()
                : "Vazio";
            String title = docs.get(i).has("title")
                ? docs.get(i).get("title").asText()
                : "Vazio";

            String title_lowerCase = title.toLowerCase();

            if (this.pesquisar_por == "Frase") {
                if (!title_lowerCase.contains(query)) {
                    continue;
                }
            }

            String authorsName = docs.get(i).get("author_name").get(0).asText();

            book_converted.setAuthorsName(authorsName);
            book_converted.setBook_Id(2);
            book_converted.setCover_Image_Url("adad");
            book_converted.setISBN(this.checkIAField(ai_array, book_converted));
            book_converted.setPublicationDate(publicationData);
            book_converted.setTitle(title);

            list_books_conveted.add(book_converted);
        }

        this.elements_draw.addAll(list_books_conveted);
    }

    public void clean_elements() {
        this.elements_draw.removeAll(this.elements_draw);
    }

    public List<BOOK> get_elements() {
        return this.elements_draw;
    }

    public TABLE init() {
        this.draw();
        return this;
    }
}
