package type.api.CLI.WIDGETS;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.NonBlockingReader;

public class INPUT {

    protected StringBuilder input = new StringBuilder();
    protected int searchBarSize = 20;
    protected Terminal terminal;
    protected int charsLength = 0;
    protected int cursorLine = 1;

    protected boolean api_response_ok = true;

    public String pesquisar_por = "Frase";
    public int selected = 0;

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

    public void handleKeyEvents(CLIENT client, List<JsonNode> response_book)
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
                    response_book.removeAll(response_book);

                    var trimInput = input.toString().trim();
                    if (trimInput.length() >= 10) {
                        var text = String.join(
                            "+",
                            input.toString().replace("|", "").split(" ")
                        );

                        if (text.isBlank()) {
                            return;
                        }

                        Optional<JsonNode> response = client.GET_REQUEST(
                            "https://openlibrary.org/search.json?q=" + text
                        );

                        if (response.isPresent()) {
                            this.api_response_ok = true;
                            response_book.add(response.get());
                        } else {
                            this.api_response_ok = false;
                            return;
                        }
                    }

                    return;
                }

                if (c == 27) {
                    int next = reader.read(100);

                    if (next == 91) {
                        int last = reader.read(100);
                        if (last == 67) {
                            this.pesquisar_por = "Palavra";
                            return;
                        } else if (last == 68) {
                            this.pesquisar_por = "Frase";
                            return;
                        } else if (last == 65) {
                            if (this.selected > 0) this.selected -= 1;
                            return;
                        } else if (last == 66) {
                            if (this.selected > 9) this.selected += 1;
                            return;
                        }
                    }
                }

                if (c == 40) {
                    this.pesquisar_por = "Palavra";
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

        var showText = String.join(
            "+",
            input.toString().replace("|", "").split(" ")
        );

        if (showText.length() >= 1) {
            showText = showText.substring(1);
        }

        String string_color;

        if (this.api_response_ok) {
            string_color = new AttributedStringBuilder()
                .style(
                    AttributedStyle.DEFAULT.bold().foreground(
                        AttributedStyle.BLUE
                    )
                )
                .append("º")
                .toAnsi();
        } else {
            string_color = new AttributedStringBuilder()
                .style(
                    AttributedStyle.DEFAULT.bold().foreground(
                        AttributedStyle.RED
                    )
                )
                .append("º")
                .toAnsi();
        }

        String[] str = {
            "https://openlibrary.org/search.json?q=" +
            showText +
            "  | Pesquisa: " +
            this.pesquisar_por,
            "┌" + "─".repeat(searchBarSize) + "┐",
            "│" + input.toString() + "│" + " " + string_color,
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
                        ((terminal.getWidth() - 30) / 2),
                        line
                    )
                );
            index++;
        }
    }

    public void init(CLIENT client, List<JsonNode> response_book)
        throws Exception {
        this.handleKeyEvents(client, response_book);
        this.draw();
    }
}
