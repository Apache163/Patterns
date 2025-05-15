package transport;

import command.PrintCommand;
import visitor.Visitor;
import transport.exceptions.DuplicateModelNameException;
import transport.exceptions.ModelPriceOutOfBoundsException;
import transport.exceptions.NoSuchModelNameException;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Auto implements Transport, Cloneable, Serializable, Iterable<Auto.Model> {
    private String brand;
    private Model[] models;
    private transient PrintCommand printCommand;

    // Вложенный класс AutoMemento
    public static class AutoMemento implements Serializable {
        private final String brand;
        private final Model[] models;

        public AutoMemento(String brand, Model[] models) {
            this.brand = brand;
            this.models = new Model[models.length];
            System.arraycopy(models, 0, this.models, 0, models.length);
        }

        public String getBrand() {
            return brand;
        }

        public Model[] getModels() {
            Model[] copy = new Model[models.length];
            System.arraycopy(models, 0, copy, 0, models.length);
            return copy;
        }
    }

    public static class Model implements Serializable {
        private String name;
        private double price;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public Model(Model other) {
            this.name = other.name;
            this.price = other.price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return String.format("%s (%.2f)", name, price);
        }
    }

    public Auto(String brand, int size) {
        this.brand = brand;
        this.models = new Model[size];
        for (int i = 0; i < size; i++) {
            models[i] = new Model("Model" + (i + 1), 1000.0 * (i + 1));
        }
    }

    public AutoMemento createMemento() {
        return new AutoMemento(brand, models);
    }

    public void setMemento(AutoMemento memento) {
        this.brand = memento.getBrand();
        this.models = memento.getModels();
    }

    @Override
    public Auto clone() {
        try {
            Auto cloned = (Auto) super.clone();
            cloned.models = new Model[models.length];
            for (int i = 0; i < models.length; i++) {
                cloned.models[i] = new Model(models[i]);
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
        String[] names = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            names[i] = models[i].name;
        }
        return names;
    }

    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        for (Model model : models) {
            if (model.name.equals(modelName)) {
                return model.price;
            }
        }
        throw new NoSuchModelNameException("Model not found: " + modelName);
    }

    @Override
    public void setModelPrice(String modelName, double price) throws NoSuchModelNameException, ModelPriceOutOfBoundsException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Price cannot be negative: " + price);
        }
        for (Model model : models) {
            if (model.name.equals(modelName)) {
                model.price = price;
                return;
            }
        }
        throw new NoSuchModelNameException("Model not found: " + modelName);
    }

    @Override
    public double[] getModelPrices() {
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].price;
        }
        return prices;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException, ModelPriceOutOfBoundsException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Price cannot be negative: " + price);
        }
        for (Model model : models) {
            if (model.name.equals(modelName)) {
                throw new DuplicateModelNameException("Model already exists: " + modelName);
            }
        }
        Model[] newModels = java.util.Arrays.copyOf(models, models.length + 1);
        newModels[models.length] = new Model(modelName, price);
        models = newModels;
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        int index = -1;
        for (int i = 0; i < models.length; i++) {
            if (models[i].name.equals(modelName)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchModelNameException("Model not found: " + modelName);
        }
        Model[] newModels = new Model[models.length - 1];
        System.arraycopy(models, 0, newModels, 0, index);
        System.arraycopy(models, index + 1, newModels, index, models.length - index - 1);
        models = newModels;
    }

    @Override
    public int getModelsCount() {
        return models.length;
    }

    public void setPrintCommand(PrintCommand printCommand) {
        this.printCommand = printCommand;
    }

    public void print(OutputStream output) throws IOException {
        if (printCommand == null) {
            throw new IllegalStateException("Print command not set");
        }
        printCommand.execute(output);
    }

    @Override
    public Iterator<Model> iterator() {
        return new AutoIterator();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    private class AutoIterator implements Iterator<Model> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < models.length;
        }

        @Override
        public Model next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return models[currentIndex++];
        }
    }
}