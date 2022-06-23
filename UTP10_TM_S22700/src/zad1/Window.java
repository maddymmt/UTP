package zad1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {

    static List<Button> buttonList = new ArrayList<>();
    JPanel lowerButtons = new JPanel();
    static JTextArea textArea = new JTextArea();

    static int threadCount = 0;

    public Window() {
        super("Thread pool");
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(500, 600));
        getContentPane().setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        upperPanel.setLayout(new GridLayout(2, 1, 0, 5));
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            Button.executor.shutdownNow();
            stopButton.setEnabled(false);
        });
        JButton createNewButton = new JButton("Create new");
        createNewButton.addActionListener(e -> {
            ++threadCount;
            Button button = new Button("T" + threadCount);
            buttonList.add(button);
            lowerButtons.add(button);
            revalidate();
        });
        upperPanel.add(stopButton);
        upperPanel.add(createNewButton);

        textArea.setEditable(false);
        JScrollPane middlePanel = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        middlePanel.setBorder(new EmptyBorder(0, 10, 0, 10));

        lowerButtons.setLayout(new GridLayout(1, 0, 5, 0));
        lowerButtons.setBorder(new EmptyBorder(10, 10, 10, 10));

        getContentPane().add(upperPanel, BorderLayout.NORTH);
        getContentPane().add(middlePanel, BorderLayout.CENTER);
        getContentPane().add(lowerButtons, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public static JTextArea getTextArea() {
        return textArea;
    }
}
