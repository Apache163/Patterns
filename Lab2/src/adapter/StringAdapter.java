package adapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StringAdapter {


    public static byte[] stringToBytes(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }


    public static String bytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }


    public static void writeStringsToStream(String[] strings, OutputStream output)
            throws IOException {
        for (String str : strings) {
            output.write(stringToBytes(str));
            output.write('\n');
        }
        output.flush();
    }

    // InputStream -> массив строк
    public static String[] readStringsFromStream(InputStream input)
            throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int b;
        while ((b = input.read()) != -1) {
            buffer.write(b);
        }
        return buffer.toString().split("\n");
    }
}