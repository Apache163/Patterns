package factory;

import transport.Auto;
import transport.Transport;

public class AutoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String brand, int size) {
        return new Auto(brand, size);
    }
}