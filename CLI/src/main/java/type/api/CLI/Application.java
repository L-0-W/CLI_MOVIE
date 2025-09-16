package type.api.CLI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.Log;
import type.api.CLI.WIDGETS.JSON_OBJECTS.BOOK;

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
            List<BOOK> response_book = new ArrayList<>();

            while (true) {
                // change for - while(gui.open())
                terminal.puts(Capability.clear_screen);
                terminal.puts(Capability.cursor_invisible);

                input.init(client, response_book);

                if (!response_book.isEmpty()) {
                    table.use_elements(response_book);
                } else {
                    table.clean_elements();
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
