package strategy;

import java.io.*;
import java.util.Map;

public class ArrayCounter {
    private CountStrategy strategy;
    private int[] array;

    public ArrayCounter(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.array = (int[]) ois.readObject();
        }
    }

    public void setStrategy(CountStrategy strategy) {
        this.strategy = strategy;
    }

    public Map<Integer, Integer> count() {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set");
        }
        return strategy.count(array);
    }

    public int[] getArray() {
        return array;
    }
}