package utils;

import decorator.SynchronizedTransport;
import transport.Transport;

public class TransportUtils {
    public static double getAveragePrice(Transport transport) {
        double[] prices = transport.getModelPrices();
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    public static void printModels(Transport transport) {
        String[] models = transport.getModelNames();
        for (String model : models) {
            System.out.println(model);
        }
    }

    public static void printPrices(Transport transport) {
        double[] prices = transport.getModelPrices();
        for (double price : prices) {
            System.out.println(price);
        }
    }

    public static Transport synchronizedTransport(Transport t) {
        return new SynchronizedTransport(t);
    }
}