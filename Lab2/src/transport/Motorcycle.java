package transport;

import visitor.Visitor;
import transport.exceptions.DuplicateModelNameException;
import transport.exceptions.ModelPriceOutOfBoundsException;
import transport.exceptions.NoSuchModelNameException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Motorcycle implements Transport, Cloneable, Serializable {
    private String brand;
    private Model head = new Model();
    private int size = 0;

    {
        head.prev = head;
        head.next = head;
    }

    private class Model implements Serializable {
        String name;
        double price;
        Model prev;
        Model next;

        Model() {
            this.name = null;
            this.price = 0.0;
            this.prev = null;
            this.next = null;
        }

        Model(Model other) {
            this.name = other.name;
            this.price = other.price;
            this.prev = null;
            this.next = null;
        }
    }

    public Motorcycle(String brand, int size) {
        this.brand = brand;
        for (int i = 0; i < size; i++) {
            try {
                addModel("Model" + (i + 1), 1000.0 * (i + 1));
            } catch (DuplicateModelNameException | ModelPriceOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Motorcycle clone() {
        try {
            Motorcycle cloned = (Motorcycle) super.clone();
            cloned.head = new Model();
            cloned.head.prev = cloned.head;
            cloned.head.next = cloned.head;
            cloned.size = 0;

            Model current = head.next;
            while (current != head) {
                try {
                    cloned.addModel(current.name, current.price);
                } catch (DuplicateModelNameException | ModelPriceOutOfBoundsException e) {
                    System.err.println("Error while cloning model: " + e.getMessage());
                }
                current = current.next;
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String[] getModelNames() {
        String[] names = new String[size];
        Model current = head.next;
        for (int i = 0; i < size; i++) {
            names[i] = current.name;
            current = current.next;
        }
        return names;
    }

    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(modelName)) {
                return current.price;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException("Model not found: " + modelName);
    }

    @Override
    public void setModelPrice(String modelName, double price) throws NoSuchModelNameException, ModelPriceOutOfBoundsException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Price cannot be negative: " + price);
        }
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(modelName)) {
                current.price = price;
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException("Model not found: " + modelName);
    }

    @Override
    public double[] getModelPrices() {
        double[] prices = new double[size];
        Model current = head.next;
        for (int i = 0; i < size; i++) {
            prices[i] = current.price;
            current = current.next;
        }
        return prices;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException, ModelPriceOutOfBoundsException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Price cannot be negative: " + price);
        }
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(modelName)) {
                throw new DuplicateModelNameException("Model already exists: " + modelName);
            }
            current = current.next;
        }
        Model newModel = new Model();
        newModel.name = modelName;
        newModel.price = price;
        newModel.prev = head.prev;
        newModel.next = head;
        head.prev.next = newModel;
        head.prev = newModel;
        size++;
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        Model current = head.next;
        while (current != head) {
            if (current.name.equals(modelName)) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                return;
            }
            current = current.next;
        }
        throw new NoSuchModelNameException("Model not found: " + modelName);
    }

    @Override
    public int getModelsCount() {
        return size;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}