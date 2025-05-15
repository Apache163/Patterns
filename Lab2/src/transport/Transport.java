package transport;

import visitor.Visitor;
import transport.exceptions.DuplicateModelNameException;
import transport.exceptions.ModelPriceOutOfBoundsException;
import transport.exceptions.NoSuchModelNameException;

public interface Transport {
    String getBrand();
    void setBrand(String brand);
    String[] getModelNames();
    double getModelPrice(String modelName) throws NoSuchModelNameException;
    void setModelPrice(String modelName, double price)
            throws NoSuchModelNameException, ModelPriceOutOfBoundsException;
    double[] getModelPrices();
    void addModel(String modelName, double price)
            throws DuplicateModelNameException, ModelPriceOutOfBoundsException;
    void removeModel(String modelName) throws NoSuchModelNameException;
    int getModelsCount();
    void accept(Visitor visitor);
}