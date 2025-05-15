package chainofresponsibility;

import transport.Transport;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class InlineWriter implements TransportWriter {
    private TransportWriter next;

    @Override
    public void writeToFile(Transport transport, String filename) throws IOException {
        if (transport.getModelsCount() <= 3) {
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write("Brand: " + transport.getBrand() + " | ");
                writer.write("Models: " + Arrays.toString(transport.getModelNames()) + " | ");
                writer.write("Prices: " + Arrays.toString(transport.getModelPrices()));
            }
        } else if (next != null) {
            next.writeToFile(transport, filename);
        }
    }

    @Override
    public void setNext(TransportWriter next) {
        this.next = next;
    }
}