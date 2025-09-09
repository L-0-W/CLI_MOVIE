package type.api.CLI.WIDGETS;

import java.util.ArrayList;
import java.util.List;
import org.jline.terminal.Terminal;
import type.api.CLI.WIDGETS.JSON_OBJECTS.MOVIE;

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

    public void draw(List<MOVIE> movie) {
        List<String> str = new ArrayList<>();

        for (int i = 0; i < tableHeigthSize; i++) {
            if (i == 0) {
                str.add("┌" + "─".repeat(tableWidthSize) + "┐");
            } else if (i == (tableHeigthSize - 1)) {
                str.add("└" + "─".repeat(tableWidthSize) + "┘");
            } else {
                if (movie.size() >= i) {
                    str.add(
                        "│" +
                        " " +
                        movie.get(i - 1).getNome() +
                        " ".repeat(
                            ((tableWidthSize - 1) -
                                movie.get(i - 1).getNome().length())
                        ) +
                        "│"
                    );

                    str.add("│" + "─".repeat(tableWidthSize) + "│");
                    continue;
                } else {
                    str.add("│" + " ".repeat(tableWidthSize) + "│");
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
}
