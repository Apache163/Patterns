package command;

import java.io.IOException;
import java.io.OutputStream;

public interface PrintCommand {
    void execute(OutputStream output) throws IOException;
}