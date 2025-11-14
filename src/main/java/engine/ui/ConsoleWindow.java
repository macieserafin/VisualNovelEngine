package engine.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConsoleWindow {
    private final JFrame frame;
    private final JTextPane textArea;
    private final JTextField inputField;
    private final JLabel promptLabel;
    private final JPanel toolbarPanel;
    private final BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();
    private volatile boolean skipWait = false;

    public int width = 800;
    public int height = 800;

    private int dragOffsetX, dragOffsetY;
    private ToolbarListener toolbarListener;

    public static final Color COLOR_TEXT = new Color(230, 230, 230);
    public static final Color COLOR_PROMPT = new Color(136, 136, 136);
    public static final Color COLOR_BG = new Color(20, 20, 20);
    public static final Color COLOR_INPUT_BG = new Color(18, 18, 18);
    public static final Color COLOR_TOOLBAR = new Color(25, 25, 25);


    public interface ToolbarListener {
        void onSave();
        void onLoad();
        void onSettings();
        void onMenu();
        void onExit();
    }

    public void setToolbarListener(ToolbarListener l) {
        this.toolbarListener = l;
    }

    public ConsoleWindow() {
        frame = new JFrame("Visual Novel Engine");
        frame.setUndecorated(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setShape(new RoundRectangle2D.Double(0, 0, width, height, 20, 20));
        frame.setLayout(new BorderLayout());

        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                dragOffsetX = e.getX();
                dragOffsetY = e.getY();
            }
        });
        frame.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - dragOffsetX, e.getYOnScreen() - dragOffsetY);
            }
        });

        toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        toolbarPanel.setBackground(COLOR_TOOLBAR);
        toolbarPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        JButton bSave = mkBtn("Save", e -> toolbarListener.onSave());
        JButton bLoad = mkBtn("Load", e -> toolbarListener.onLoad());
        JButton bSettings = mkBtn("Settings", e -> toolbarListener.onSettings());
        JButton bMenu = mkBtn("Menu", e -> toolbarListener.onMenu());
        JButton bExit = mkBtn("Exit", e -> toolbarListener.onExit());

        toolbarPanel.add(bSave);
        toolbarPanel.add(bLoad);
        toolbarPanel.add(bSettings);
        toolbarPanel.add(bMenu);
        toolbarPanel.add(bExit);

        textArea = new JTextPane();
        textArea.setEditable(false);
        textArea.setBackground(new Color(20, 20, 20));
        textArea.setForeground(new Color(220, 220, 220));
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        textArea.setMargin(new Insets(10,10,10,10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        promptLabel = new JLabel(" ");
        promptLabel.setForeground(COLOR_PROMPT);
        promptLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        promptLabel.setBorder(new EmptyBorder(4, 12, 4, 12));
        promptLabel.setVisible(false);

        inputField = new JTextField();
        inputField.setBackground(COLOR_INPUT_BG);
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        inputField.setFont(new Font("Consolas", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        inputField.addActionListener(e -> {
            String text = inputField.getText();
            inputQueue.offer(text == null ? "" : text);
            inputField.setText("");
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(COLOR_INPUT_BG);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(40, 40, 40)));
        bottomPanel.add(promptLabel, BorderLayout.NORTH);
        bottomPanel.add(inputField, BorderLayout.CENTER);

        frame.add(toolbarPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                inputField.requestFocusInWindow();
            }
        });

        frame.setVisible(true);
        SwingUtilities.invokeLater(() -> inputField.requestFocusInWindow());
    }

    private JButton mkBtn(String label, ActionListener al) {
        JButton b = new JButton(label);
        b.addActionListener(al);
        b.setFocusPainted(false);
        b.setBackground(new Color(45, 45, 45));
        b.setForeground(Color.WHITE);
        b.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    public void showToolbar(boolean visible) { toolbarPanel.setVisible(visible); }

    public void print(String text) {
        try {
            StyledDocument doc = textArea.getStyledDocument();
            doc.insertString(doc.getLength(), text, null);
            textArea.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void println(String text) {
        try {
            StyledDocument doc = textArea.getStyledDocument();
            doc.insertString(doc.getLength(), text + "\n", null);
            textArea.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void clear() { textArea.setText(""); }

    public void type(String text, int delayMs) throws InterruptedException {
        StyledDocument doc = textArea.getStyledDocument();
        for (char c : text.toCharArray()) {
            try {
                doc.insertString(doc.getLength(), String.valueOf(c), null);
                textArea.setCaretPosition(doc.getLength());
                Thread.sleep(delayMs);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        try {
            doc.insertString(doc.getLength(), "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return inputQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "";
        }
    }


    public void close() { frame.dispose(); }

    public void removeLastLine() {
        String text = textArea.getText();
        int lastLineIndex = text.lastIndexOf("\n", text.length() - 2);
        if (lastLineIndex >= 0) {
            textArea.setText(text.substring(0, lastLineIndex + 1));
        } else {
            textArea.setText("");
        }
    }

    public void showPrompt(String text) {
        SwingUtilities.invokeLater(() -> {
            promptLabel.setText(text);
            promptLabel.setVisible(true);
        });
    }

    public void hidePrompt() {
        SwingUtilities.invokeLater(() -> promptLabel.setVisible(false));
    }

    public void printColored(String text, Color color) {
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, color);

        StyleConstants.setFontFamily(attrs, "Consolas");
        StyleConstants.setFontSize(attrs, 16);
        try {
            doc.insertString(doc.getLength(), text, attrs);
            textArea.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void printlnColored(String text, Color color) {
        printColored(text + "\n", color);
    }

    public void typeColored(String text, int delayMs, Color color) throws InterruptedException {
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, color);
        for (char c : text.toCharArray()) {
            try {
                doc.insertString(doc.getLength(), String.valueOf(c), attrs);
                textArea.setCaretPosition(doc.getLength());
                Thread.sleep(delayMs);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        try {
            doc.insertString(doc.getLength(), " ", attrs);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void skipWaiting() {
        skipWait = true;
        inputQueue.offer("");
    }

    public void waitForEnter() {
        try {
            while (true) {
                String input = inputQueue.take();
                if (skipWait || input.isEmpty()) {
                    break;
                }
            }
        } catch (InterruptedException ignored) {}
        skipWait = false;
    }

    public void clearInputQueue() {
        inputQueue.clear();
    }


}
