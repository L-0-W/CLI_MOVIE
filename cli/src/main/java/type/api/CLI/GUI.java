package type.api.CLI;

import type.api.CLI.WIDGETS.CLIENT;
import type.api.CLI.WIDGETS.INPUT;
import type.api.CLI.WIDGETS.TABLE;

public class GUI {

    public static INPUT INPUT_BUILDER() {
        return new INPUT();
    }

    public static TABLE TABLE_BUILDER() {
        return new TABLE();
    }

    public static CLIENT CLIENT_BUILDER() {
        return new CLIENT();
    }
}
