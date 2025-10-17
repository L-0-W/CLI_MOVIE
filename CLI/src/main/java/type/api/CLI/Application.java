package type.api.CLI;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.Log;

public class Application {

    public static void main(String[] args) throws Exception {
        try {
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            terminal.enterRawMode();

            terminal.puts(Capability.clear_screen);
            terminal.flush();

            var input = GUI.INPUT_BUILDER()
                .setSearchBarSize(50)
                .setTerminal(terminal);

            var table = GUI.TABLE_BUILDER()
                .setTableWidthSize(150)
                .setTableHeigthSize(30)
                .setTerminal(terminal);

            var client = GUI.CLIENT_BUILDER();
            List<JsonNode> response_book = new ArrayList<JsonNode>();

            while (true) {
                // change for - while(gui.open())
                terminal.puts(Capability.clear_screen);
                terminal.puts(Capability.cursor_invisible);

                input.init(client, response_book);

                if (response_book.isEmpty()) {
                    table.clean_elements();
                } else {
                    table.use_elements(
                        response_book,
                        input.pesquisar_por,
                        input.selected
                    );
                }

                table.init();

                terminal.writer().flush();
            }

            //terminal.close();
        } catch (IOException e) {
            Log.error(e);
        }
    }
}
