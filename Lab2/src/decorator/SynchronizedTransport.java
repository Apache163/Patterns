package decorator;

import transport.Transport;
import transport.exceptions.DuplicateModelNameException;
import transport.exceptions.ModelPriceOutOfBoundsException;
import transport.exceptions.NoSuchModelNameException;
import visitor.Visitor;

public class SynchronizedTransport implements Transport {
    private Transport transport;

    public static Transport synchronizedTransport(Transport transport) {
        return new SynchronizedTransport(transport);
    }

    public SynchronizedTransport(Transport transport) {
        this.transport = transport;
    }

    public synchronized String getBrand() {
        return transport.getBrand();
    }

    public synchronized void setBrand(String brand) {
        transport.setBrand(brand);
    }

    @Override
    public synchronized String[] getModelNames() {
        return transport.getModelNames();
    }

    @Override
    public synchronized double getModelPrice(String modelName) throws NoSuchModelNameException {
        return transport.getModelPrice(modelName);
    }

    @Override
    public synchronized void setModelPrice(String modelName, double price)
            throws NoSuchModelNameException, ModelPriceOutOfBoundsException {
        transport.setModelPrice(modelName, price);
    }

    @Override
    public synchronized double[] getModelPrices() {
        return transport.getModelPrices();
    }

    @Override
    public synchronized void addModel(String modelName, double price)
            throws DuplicateModelNameException, ModelPriceOutOfBoundsException {
        transport.addModel(modelName, price);
    }

    @Override
    public synchronized void removeModel(String modelName) throws NoSuchModelNameException {
        transport.removeModel(modelName);
    }

    @Override
    public synchronized int getModelsCount() {
        return transport.getModelsCount();
    }

    @Override
    public synchronized void accept(Visitor visitor) {
        transport.accept(visitor);
    }
}