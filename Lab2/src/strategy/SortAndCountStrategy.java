package strategy;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class SortAndCountStrategy implements CountStrategy {
    @Override
    public Map<Integer, Integer> count(int[] array) {
        Map<Integer, Integer> result = new TreeMap<>();
        if (array.length == 0) return result;

        Arrays.sort(array);
        int current = array[0];
        int count = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == current) {
                count++;
            } else {
                result.put(current, count);
                current = array[i];
                count = 1;
            }
        }
        result.put(current, count);
        return result;
    }
}