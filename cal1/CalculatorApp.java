import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton, percButton;
    private JButton decButton, clrButton, delButton, equButton;
    private JPanel panel;
    private boolean isOn = false;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        display = new JTextField();
        display.setBounds(50, 25, 300, 50);
        display.setEditable(false);
        add(display);

        JRadioButton onButton = new JRadioButton("ON");
        JRadioButton offButton = new JRadioButton("OFF");
        onButton.setBounds(50, 80, 60, 40);
        offButton.setBounds(120, 80, 60, 40);
        add(onButton);
        add(offButton);

        ButtonGroup bg = new ButtonGroup();
        bg.add(onButton);
        bg.add(offButton);

        onButton.addActionListener(e -> turnOn());
        offButton.addActionListener(e -> turnOff());

        clrButton = new JButton("C");
        delButton = new JButton("DEL");
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        percButton = new JButton("%");
        decButton = new JButton(".");
        equButton = new JButton("=");

        JButton[] functionButtons = {addButton, subButton, mulButton, divButton, percButton, clrButton, delButton, decButton, equButton};
        Color functionColor = new Color(173, 216, 230); // Light blue

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFocusable(false);
            button.setBackground(functionColor);
        }

        panel = new JPanel();
        panel.setBounds(50, 130, 300, 300);
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(255, 228, 196)); // Bisque

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(255, 182, 193)); // Light pink
        }

        // Adding buttons to panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(percButton);
        panel.add(divButton);
        panel.add(clrButton);
        panel.add(delButton);
        panel.add(equButton);

        add(panel);

        setVisible(true);
    }

    public void turnOn() {
        isOn = true;
        for (Component comp : panel.getComponents()) {
            comp.setEnabled(true);
        }
        display.setEnabled(true);
    }

    public void turnOff() {
        isOn = false;
        for (Component comp : panel.getComponents()) {
            comp.setEnabled(false);
        }
        display.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isOn) return;

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText() + i);
            }
        }

        if (e.getSource() == decButton) {
            display.setText(display.getText() + ".");
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            display.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            display.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            display.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            display.setText("");
        }

        if (e.getSource() == percButton) {
            num1 = Double.parseDouble(display.getText());
            operator = '%';
            display.setText("");
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                case '%':
                    result = (num1 * num2) / 100;
                    break;
            }
            display.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource() == clrButton) {
            display.setText("");
        }

        if (e.getSource() == delButton) {
            String text = display.getText();
            display.setText("");
            for (int i = 0; i < text.length() - 1; i++) {
                display.setText(display.getText() + text.charAt(i));
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}