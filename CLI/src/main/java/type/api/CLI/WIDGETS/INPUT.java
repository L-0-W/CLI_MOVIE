package type.api.CLI.WIDGETS;

import java.io.IOException;
import java.util.List;
import org.jline.terminal.Terminal;
import org.jline.utils.NonBlockingReader;
import type.api.CLI.WIDGETS.JSON_OBJECTS.BOOK;

public class INPUT {

    protected StringBuilder input = new StringBuilder();
    protected int searchBarSize = 20;
    protected Terminal terminal;
    protected int charsLength = 0;
    protected int cursorLine = 1;
    long tempoInicial = System.currentTimeMillis();

    public INPUT setSearchBarSize(int searchBarSize) {
        this.searchBarSize = searchBarSize;
        input.insert(0, " ".repeat(searchBarSize));

        return this;
    }

    public INPUT setTerminal(Terminal terminal) {
        this.terminal = terminal;

        return this;
    }

    public void handleKeyEvents(CLIENT client, List<BOOK> response_book)
        throws Exception {
        try {
            NonBlockingReader reader = terminal.reader();
            int indexInput = charsLength + 1;
            int c = reader.read(60);

            terminal.writer().println(c);

            if (c != -1 && c != -2) {
                tempoInicial = System.currentTimeMillis();
                var chr = (char) c;

                if (c == 13) {
                    return;
                }

                if (
                    indexInput > charsLength &&
                    c != 127 &&
                    indexInput < (searchBarSize - 1)
                ) {
                    input.deleteCharAt(indexInput);
                    input.insert(indexInput, chr);

                    charsLength++;
                    cursorLine++;
                }

                if (c == 127 && charsLength > 0) {
                    input.deleteCharAt(charsLength);
                    input.insert(charsLength, ' ');
                    charsLength--;

                    input.deleteCharAt(cursorLine);
                    input.insert(cursorLine, ' ');
                    cursorLine--;
                }

                var text = input.toString().trim().replace("|", "");
                response_book.removeAll(response_book);

                List<BOOK> response = client.GET_REQUEST(
                    "http://localhost:8080/books?title=" + text
                );

                if (text.isBlank()) {
                    return;
                }

                response_book.addAll(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() throws Exception {
        long tempoAtual = System.currentTimeMillis();
        if ((tempoAtual - tempoInicial) / 1000 >= 1) {
            input.deleteCharAt(cursorLine);
            input.insert(cursorLine, ' ');

            tempoInicial = System.currentTimeMillis();
        } else {
            input.deleteCharAt(cursorLine);
            input.insert(cursorLine, '|');
        }

        String[] str = {
            "http://localhost:8080/books?title=" +
            input.toString().trim().replace("|", ""),
            "┌" + "─".repeat(searchBarSize) + "┐",
            "│" + input.toString() + "│",
            "└" + "─".repeat(searchBarSize) + "┘",
        };

        int index = 0;
        for (String line : str) {
            terminal
                .writer()
                .print(
                    String.format(
                        "\033[%d;%dH%s",
                        ((terminal.getHeight() - terminal.getHeight()) + 3) +
                            index,
                        ((terminal.getWidth() - line.length()) / 2),
                        line
                    )
                );
            index++;
        }
    }

    public void init(CLIENT client, List<BOOK> response_book) throws Exception {
        this.handleKeyEvents(client, response_book);
        this.draw();
    }
}
