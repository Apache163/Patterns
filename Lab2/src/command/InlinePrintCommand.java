package command;

import transport.Auto;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class InlinePrintCommand implements PrintCommand {
    private final Auto auto;

    public InlinePrintCommand(Auto auto) {
        this.auto = auto;
    }

    @Override
    public void execute(OutputStream output) throws IOException {
        String result = String.format(
                "Brand: %s | Models: %s | Prices: %s%n",
                auto.getBrand(),
                Arrays.toString(auto.getModelNames()),
                Arrays.toString(auto.getModelPrices())
        );
        output.write(result.getBytes());
    }
}