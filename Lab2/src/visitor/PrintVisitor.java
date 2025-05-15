package visitor;

import transport.Auto;
import transport.Motorcycle;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Auto auto) {
        // Вывод в одну строку для Auto
        System.out.print("Auto: " + auto.getBrand() + " | Models: ");
        String[] models = auto.getModelNames();
        double[] prices = auto.getModelPrices();
        for (int i = 0; i < models.length; i++) {
            System.out.print(models[i] + "(" + prices[i] + ") ");
        }
        System.out.println();
    }

    @Override
    public void visit(Motorcycle motorcycle) {
        // Вывод в столбик для Motorcycle
        System.out.println("Motorcycle: " + motorcycle.getBrand());
        System.out.println("Models and prices:");
        String[] models = motorcycle.getModelNames();
        double[] prices = motorcycle.getModelPrices();
        for (int i = 0; i < models.length; i++) {
            System.out.println("- " + models[i] + ": " + prices[i]);
        }
    }
}