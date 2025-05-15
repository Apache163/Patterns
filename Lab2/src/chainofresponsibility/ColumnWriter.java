package chainofresponsibility;

import transport.Transport;
import java.io.FileWriter;
import java.io.IOException;

public class ColumnWriter implements TransportWriter {
    private TransportWriter next;

    @Override
    public void writeToFile(Transport transport, String filename) throws IOException {
        if (transport.getModelsCount() > 3) {
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write("Brand: " + transport.getBrand() + "\n");
                writer.write("Models:\n");
                for (String name : transport.getModelNames()) {
                    writer.write("- " + name + "\n");
                }
                writer.write("Prices:\n");
                for (double price : transport.getModelPrices()) {
                    writer.write("- " + price + "\n");
                }
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