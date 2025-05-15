import chainofresponsibility.ColumnWriter;
import chainofresponsibility.InlineWriter;
import chainofresponsibility.TransportWriter;
import command.ColumnPrintCommand;
import command.InlinePrintCommand;
import decorator.SynchronizedTransport;
import observer.FacePanel;
import state.HumanPanel;
import strategy.ArrayCounter;
import strategy.HashMapCountStrategy;
import strategy.SortAndCountStrategy;
import templatemethod.BouncingShapesApp;
import visitor.PrintVisitor;
import transport.Auto;
import transport.Motorcycle;
import transport.Transport;
import transport.exceptions.DuplicateModelNameException;
import transport.exceptions.ModelPriceOutOfBoundsException;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Демонстрация различных паттернов проектирования
            System.out.println("=== Chain of Responsibility Demo ===");
            demoChainOfResponsibility();

            System.out.println("\n=== Command Pattern Demo ===");
            demoCommandPattern();

            System.out.println("\n=== Iterator Pattern Demo ===");
            demoIteratorPattern();

            System.out.println("\n=== Memento Pattern Demo ===");
            demoMementoPattern();

            System.out.println("\n=== Observer Pattern Demo ===");
            demoObserverPattern();

            System.out.println("\n=== State Pattern Demo ===");
            demoStatePattern();

            System.out.println("\n=== Strategy Pattern Demo ===");
            demoStrategyPattern("array_data.ser");

            System.out.println("\n=== Template Method Pattern Demo ===");
            demoTemplateMethodPattern();

            System.out.println("\n=== Visitor Pattern Demo ===");
            demoVisitorPattern();

        } catch (Exception e) {
            System.err.println("Ошибка в main: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Демонстрация паттерна Chain of Responsibility
    private static void demoChainOfResponsibility() throws DuplicateModelNameException, ModelPriceOutOfBoundsException, IOException {
        // Создаем обработчики для записи транспорта
        TransportWriter inlineWriter = new InlineWriter(); // Запись в строку
        TransportWriter columnWriter = new ColumnWriter(); // Запись в столбец
        inlineWriter.setNext(columnWriter); // Устанавливаем цепочку обработчиков

        // Создаем небольшой транспорт (Toyota)
        Transport smallTransport = new Auto("Toyota", 0);
        smallTransport.addModel("Camry", 30000);
        smallTransport.addModel("Corolla", 25000);
        inlineWriter.writeToFile(smallTransport, "small_transport.txt");
        System.out.println("Малый транспорт записан в small_transport.txt");

        // Создаем большой транспорт (BMW)
        Transport largeTransport = new Auto("BMW", 0);
        largeTransport.addModel("X5", 50000);
        largeTransport.addModel("X3", 40000);
        largeTransport.addModel("M5", 70000);
        largeTransport.addModel("i8", 120000);
        inlineWriter.writeToFile(largeTransport, "large_transport.txt");
        System.out.println("Большой транспорт записан в large_transport.txt");
    }

    // Демонстрация паттерна Command
    private static void demoCommandPattern() {
        try {
            // Создаем автомобиль Tesla
            Auto auto = new Auto("Tesla", 0);
            auto.addModel("Model S", 80000);
            auto.addModel("Model 3", 40000);
            auto.addModel("Model X", 90000);
            auto.addModel("Model Y", 50000);

            // Устанавливаем команду для вывода в строку
            auto.setPrintCommand(new InlinePrintCommand(auto));
            try (FileOutputStream out = new FileOutputStream("tesla_inline.txt")) {
                auto.print(out);
                System.out.println("Вывод в строку записан в tesla_inline.txt");
            }

            // Устанавливаем команду для вывода в столбец
            auto.setPrintCommand(new ColumnPrintCommand(auto));
            try (FileOutputStream out = new FileOutputStream("tesla_column.txt")) {
                auto.print(out);
                System.out.println("Вывод в столбец записан в tesla_column.txt");
            }

        } catch (Exception e) {
            System.err.println("Ошибка в демо Command: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Демонстрация паттерна Iterator
    private static void demoIteratorPattern() {
        try {
            // Создаем автомобиль Audi
            Auto auto = new Auto("Audi", 0);
            auto.addModel("A4", 45000);
            auto.addModel("A6", 60000);
            auto.addModel("Q7", 75000);
            auto.addModel("TT", 55000);

            // Итерируемся по моделям с помощью for-each
            System.out.println("Итерация по моделям Audi:");
            for (Auto.Model model : auto) {
                System.out.println(model);
            }

            // Итерируемся с использованием явного итератора
            System.out.println("\nИспользование явного итератора:");
            Iterator<Auto.Model> iterator = auto.iterator();
            while (iterator.hasNext()) {
                Auto.Model model = iterator.next();
                System.out.printf("Модель: %s, Цена: %.2f%n", model.getName(), model.getPrice());
            }

        } catch (Exception e) {
            System.err.println("Ошибка в демо Iterator: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Демонстрация паттерна Memento
    private static void demoMementoPattern() {
        try {
            // Создаем исходный автомобиль
            Auto original = new Auto("Ford", 0);
            original.addModel("Focus", 25000);
            original.addModel("Mustang", 45000);
            System.out.println("\nИсходное состояние:");
            printAutoInfo(original);

            // Сохраняем состояние
            Auto.AutoMemento memento = original.createMemento();
            System.out.println("\nСостояние сохранено в memento");

            // Модифицируем объект
            original.addModel("Explorer", 40000);
            original.setBrand("Ford Motor Company");
            System.out.println("\nИзмененное состояние:");
            printAutoInfo(original);

            // Восстанавливаем состояние из memento
            original.setMemento(memento);
            System.out.println("\nВосстановленное состояние из memento:");
            printAutoInfo(original);

        } catch (Exception e) {
            System.err.println("Ошибка в демо Memento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Демонстрация паттерна Observer
    private static void demoObserverPattern() {
        SwingUtilities.invokeLater(() -> {
            FacePanel facePanel = new FacePanel();
            facePanel.setVisible(true);
            System.out.println("Демо Observer: FacePanel запущен");
        });
    }

    // Демонстрация паттерна State
    private static void demoStatePattern() {
        SwingUtilities.invokeLater(() -> {
            HumanPanel humanPanel = new HumanPanel();
            humanPanel.setVisible(true);
            System.out.println("Демо State: HumanPanel запущен");
        });
    }

    // Демонстрация паттерна Strategy
    private static void demoStrategyPattern(String filename) {
        try {
            filename = "array_data.ser";
            System.out.println("Используем входной файл: " + filename);
            ArrayCounter counter = new ArrayCounter(filename);

            System.out.println("Содержимое массива: " + Arrays.toString(counter.getArray()));

            // Используем стратегию с HashMap
            counter.setStrategy(new HashMapCountStrategy());
            Map<Integer, Integer> hashMapResult = counter.count();
            System.out.println("\nРезультаты стратегии HashMap:");
            printCountResult(hashMapResult);

            // Используем стратегию с сортировкой
            counter.setStrategy(new SortAndCountStrategy());
            Map<Integer, Integer> sortResult = counter.count();
            System.out.println("\nРезультаты стратегии сортировки:");
            printCountResult(sortResult);

        } catch (Exception e) {
            System.err.println("Ошибка в демо Strategy: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Демонстрация паттерна Template Method
    private static void demoTemplateMethodPattern() {
        SwingUtilities.invokeLater(() -> {
            BouncingShapesApp app = new BouncingShapesApp();
            app.setVisible(true);
            System.out.println("Демо Template Method: Bouncing Shapes App запущен");
            System.out.println("Инструкции:");
            System.out.println("1. Нажмите 'Пуск' для добавления случайных фигур");
            System.out.println("2. Нажмите 'Закрыть' для выхода");
        });
    }

    // Демонстрация паттерна Visitor
    private static void demoVisitorPattern() throws DuplicateModelNameException, ModelPriceOutOfBoundsException {
        // Создаем автомобиль Toyota
        Transport auto = new Auto("Toyota", 0);
        auto.addModel("Camry", 30000);
        auto.addModel("Corolla", 25000);
        auto.addModel("RAV4", 35000);

        // Создаем мотоцикл Harley-Davidson
        Transport motorcycle = new Motorcycle("Harley-Davidson", 0);
        motorcycle.addModel("Sportster", 15000);
        motorcycle.addModel("Fat Boy", 20000);
        motorcycle.addModel("Street Glide", 25000);

        // Создаем посетителя для печати
        PrintVisitor visitor = new PrintVisitor();

        System.out.println("\n=== Печать автомобиля (в строку) ===");
        auto.accept(visitor);

        System.out.println("\n=== Печать мотоцикла (в столбец) ===");
        motorcycle.accept(visitor);

        // Демонстрация работы с декоратором
        Transport syncAuto = SynchronizedTransport.synchronizedTransport(auto);
        System.out.println("\n=== Печать синхронизированного автомобиля (в строку) ===");
        syncAuto.accept(visitor);
        System.out.println();
    }

    // Вспомогательный метод для печати информации об автомобиле
    private static void printAutoInfo(Auto auto) throws Exception {
        System.out.println("Марка: " + auto.getBrand());
        System.out.println("Количество моделей: " + auto.getModelsCount());
        System.out.println("Модели и цены:");
        for (Auto.Model model : auto) {
            System.out.println(model);
        }
    }

    // Вспомогательный метод для печати результатов подсчета
    private static void printCountResult(Map<Integer, Integer> result) {
        if (result.isEmpty()) {
            System.out.println("Элементы не найдены");
            return;
        }

        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            System.out.printf("%d встречается %d раз(а)%n", entry.getKey(), entry.getValue());
        }
    }
}