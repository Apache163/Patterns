package factory;

import transport.Transport;

public interface TransportFactory {
    Transport createInstance(String brand, int size);
}