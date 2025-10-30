package engine.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        frame.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false); // brak możliwości kliknięcia
        textArea.setBackground(new Color(15, 15, 15));
        textArea.setForeground(new Color(230, 230, 230));
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        textArea.setMargin(new Insets(10, 15, 10, 15));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(40, 40, 40)));
        scrollPane.setBackground(new Color(15, 15, 15));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setFocusable(false); // nie można kliknąć w scroll
        scrollPane.getVerticalScrollBar().setBackground(new Color(25, 25, 25));
        scrollPane.getVerticalScrollBar().setForeground(new Color(100, 100, 100));

        inputField = new JTextField();
        inputField.setBackground(new Color(25, 25, 25));
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(new Color(0, 255, 180));
        inputField.setFont(new Font("Consolas", Font.PLAIN, 16));
        inputField.setBorder(new EmptyBorder(8, 12, 8, 12));
        inputField.setMargin(new Insets(5, 10, 5, 10));

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = inputField.getText().trim();
                    inputQueue.offer(text);
                    inputField.setText("");
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(10, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        SwingUtilities.invokeLater(() -> inputField.requestFocusInWindow());
    }

    public void print(String text) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(text);
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }

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

    public void waitForEnter() throws InterruptedException {
        inputQueue.take();
    }

    public void type(String text, int delayMs) {
        if (text == null) return;
        for (int i = 0; i < text.length(); i++) {
            final char ch = text.charAt(i);
            SwingUtilities.invokeLater(() -> {
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
        SwingUtilities.invokeLater(() -> textArea.append("\n"));
    }

    public void clear() {
        textArea.setText("");
    }

    public void close() {
        frame.dispose();
    }
}
