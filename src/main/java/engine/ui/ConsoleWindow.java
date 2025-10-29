package engine.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConsoleWindow {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();

    public ConsoleWindow(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));

        inputField = new JTextField();
        inputField.setBackground(Color.DARK_GRAY);
        inputField.setForeground(Color.WHITE);
        inputField.setFont(new Font("Consolas", Font.PLAIN, 16));

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String text = inputField.getText();
                    inputQueue.offer(text);
                    inputField.setText("");
                }
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

//    public void print(String text) {
//        textArea.append(text);
//        textArea.setCaretPosition(textArea.getDocument().getLength());
//    }

    public void println(String text) {
        print(text + "\n");
    }

    public String readLine() {
        try {
            return inputQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "";
        }
    }

    public void clear() {
        textArea.setText("");
    }

    public void close() {
        frame.dispose();
    }

    public void print(String text) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            textArea.append(text);
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }

    public void type(String text, int delayMs) {
        if (text == null) return;
        for (int i = 0; i < text.length(); i++) {
            final char ch = text.charAt(i);
            javax.swing.SwingUtilities.invokeLater(() -> {
                textArea.append(String.valueOf(ch));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
            textArea.append("\n");
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }

    public void waitForEnter() throws InterruptedException {
        inputQueue.take();
    }

}
