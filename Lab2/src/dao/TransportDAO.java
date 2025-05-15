package dao;

import transport.Transport;
import transport.exceptions.DuplicateModelNameException;

public interface TransportDAO {
    void saveTransport(Transport transport, String filename) throws Exception;
    Transport loadTransport(String filename) throws Exception, DuplicateModelNameException;
}