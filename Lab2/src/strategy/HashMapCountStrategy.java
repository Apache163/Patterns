package strategy;

import java.util.HashMap;
import java.util.Map;

public class HashMapCountStrategy implements CountStrategy {
    @Override
    public Map<Integer, Integer> count(int[] array) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int num : array) {
            result.put(num, result.getOrDefault(num, 0) + 1);
        }
        return result;
    }
}