package factory;

import transport.Motorcycle;
import transport.Transport;

public class MotorcycleFactory implements TransportFactory {
    @Override
    public Transport createInstance(String brand, int size) {
        return new Motorcycle(brand, size);
    }
}