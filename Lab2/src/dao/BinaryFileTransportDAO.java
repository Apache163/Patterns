package dao;

import transport.Transport;
import transport.exceptions.DuplicateModelNameException;

import java.io.*;

public class BinaryFileTransportDAO implements TransportDAO {
    @Override
    public void saveTransport(Transport transport, String filename) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(transport);
        }
    }

    @Override
    public Transport loadTransport(String filename) throws Exception, DuplicateModelNameException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Transport) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new Exception("Class not found during deserialization", e);
        }
    }
}