import java.io.*;

public class CreateTestArray {
    public static void main(String[] args) throws IOException {
        int[] testArray = {1, 2, 3, 2, 1, 3, 4, 5, 4, 3, 2, 1};
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("array_data.ser"))) {
            oos.writeObject(testArray);
        }
    }
}