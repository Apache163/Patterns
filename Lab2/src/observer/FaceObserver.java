package observer;

import javax.swing.*;

public class FaceObserver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FacePanel panel = new FacePanel();
            panel.setVisible(true); // Это ключевое исправление!
        });
    }
}