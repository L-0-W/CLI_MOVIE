package type.api.CLI;

import java.io.IOException;
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

            while (true) {
                // change for - while(gui.open())
                terminal.puts(Capability.clear_screen);
                terminal.puts(Capability.cursor_invisible);

                List<BOOK> response_book = null;

                input.init(client, response_book);
                table.init().elements_draw(response_book);

                terminal.writer().flush();
            }

            //terminal.close();
        } catch (IOException e) {
            Log.error(e);
        }
    }
}
