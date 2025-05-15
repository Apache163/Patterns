package dao;

import transport.Transport;
import transport.Auto;
import transport.Motorcycle;
import transport.exceptions.DuplicateModelNameException;
import transport.exceptions.ModelPriceOutOfBoundsException;

import java.io.*;
import java.util.Scanner;

public class TextFileTransportDAO implements TransportDAO {
    @Override
    public void saveTransport(Transport transport, String filename) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(transport.getBrand());
            writer.println(transport.getModelsCount());

            String[] modelNames = transport.getModelNames();
            double[] prices = transport.getModelPrices();

            for (int i = 0; i < modelNames.length; i++) {
                writer.println(modelNames[i]);
                writer.println(prices[i]);
            }
        }
    }

    @Override
    public Transport loadTransport(String filename) throws Exception, DuplicateModelNameException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            String brand = scanner.nextLine();
            int modelCount = Integer.parseInt(scanner.nextLine());

            Transport transport;
            if (brand.toLowerCase().contains("motorcycle")) {
                transport = new Motorcycle(brand, 0);
            } else {
                transport = new Auto(brand, 0);
            }

            for (int i = 0; i < modelCount; i++) {
                String modelName = scanner.nextLine();
                double price = Double.parseDouble(scanner.nextLine());
                transport.addModel(modelName, price);
            }

            return transport;
        } catch (ModelPriceOutOfBoundsException e) {
            throw new Exception("Invalid price in file", e);
        }
    }
}