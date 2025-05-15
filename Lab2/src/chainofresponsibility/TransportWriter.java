package chainofresponsibility;

import transport.Transport;
import java.io.IOException;

public interface TransportWriter {
    void writeToFile(Transport transport, String filename) throws IOException;
    void setNext(TransportWriter next);
}