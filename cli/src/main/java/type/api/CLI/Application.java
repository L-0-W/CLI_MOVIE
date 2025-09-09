package type.api.CLI;

import java.io.IOException;
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

            while (true) {
                // change for - while(gui.open())
                terminal.puts(Capability.clear_screen);
                terminal.puts(Capability.cursor_invisible);

                var response = client.GET_REQUEST(
                    "http://localhost:8080/movies"
                );

                input.draw();
                table.draw(response);

                terminal.writer().flush();
            }

            //terminal.close();
        } catch (IOException e) {
            Log.error(e);
        }
    }
}
