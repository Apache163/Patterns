package command;

import transport.Auto;
import java.io.IOException;
import java.io.OutputStream;

public class ColumnPrintCommand implements PrintCommand {
    private final Auto auto;

    public ColumnPrintCommand(Auto auto) {
        this.auto = auto;
    }

    @Override
    public void execute(OutputStream output) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Brand: ").append(auto.getBrand()).append("\n");
        sb.append("Models:\n");
        for (String name : auto.getModelNames()) {
            sb.append("- ").append(name).append("\n");
        }
        sb.append("Prices:\n");
        for (double price : auto.getModelPrices()) {
            sb.append("- ").append(price).append("\n");
        }
        output.write(sb.toString().getBytes());
    }
}