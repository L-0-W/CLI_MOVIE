package type.api.CLI.WIDGETS;

import java.util.ArrayList;
import java.util.List;
import org.jline.terminal.Terminal;
import type.api.CLI.WIDGETS.JSON_OBJECTS.BOOK;

public class TABLE {

    protected int tableWidthSize = 10;
    protected int tableHeigthSize = 10;
    protected Terminal terminal;

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
            } else {
                if (i == 1) {
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
                } else {
                    if (!movie.isEmpty()) {
                        var element = movie.remove(0);
                        str.add(
                            "│" +
                                " " +
                                element.getTitle() +
                                " ".repeat(5) +
                                "│" +
                                " ".repeat(5) +
                                element.getBook_Id() +
                                " ".repeat(
                                    ((tableWidthSize - 1) -
                                        element.getTitle().length() -
                                        13)
                                ) +
                                "│"
                        );

                        str.add("│" + "─".repeat(tableWidthSize) + "│");
                    } else {
                        str.add("│" + " ".repeat(tableWidthSize) + "│");
                    }
                }
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

    public void elements_draw(List<BOOK> books_draw) {
        return;
    }

    public TABLE init() {
        this.draw();

        return this;
    }
}
