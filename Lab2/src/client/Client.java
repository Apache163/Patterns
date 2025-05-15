package client;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        MathProxy proxy = new MathProxy();
        double result = proxy.multiply(3.5, 2.5);
        System.out.println("3.5 * 2.5 = " + result);
    }
}

