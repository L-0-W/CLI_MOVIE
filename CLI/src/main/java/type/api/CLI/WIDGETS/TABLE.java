package type.api.CLI.WIDGETS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jline.terminal.Terminal;
import type.api.CLI.WIDGETS.JSON_OBJECTS.BOOK;

public class TABLE {

    protected int tableWidthSize = 10;
    protected int tableHeigthSize = 10;
    protected Terminal terminal;
    protected List<BOOK> elements_draw = new ArrayList<>();

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
                        "│" +
                        " ".repeat(
                            tableWidthSize -
                                (current_element.getTitle().length()) -
                                11
                        ) +
                        "│"
                );

                str.add("│" + "─".repeat(tableWidthSize) + "│");
            } else {
                str.add("│" + " ".repeat(tableWidthSize) + "│");
            }
        }

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

    public void use_elements(List<BOOK> books_draw) {
        this.elements_draw.removeAll(this.elements_draw);
        this.elements_draw.addAll(books_draw);
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
