import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] buttons;
    private String[] labels = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", ".", "0", "=", "/", "C"};

    private double result = 0;
    private String operator = "=";
    private boolean startOfNumber = true;

     Main() {
        display = new JTextField(20);
        display.setEditable(false);
        buttons = new JButton[labels.length];
        for (int i = 0; i < labels.length; i++) {
            buttons[i] = new JButton(labels[i]);
        }

        for (int i = 0; i < labels.length; i++) {
            buttons[i].addActionListener(this);
        }

        setLayout(new BorderLayout());

        add(display, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 2, 2));
        for (int i = 0; i < labels.length; i++) {
            panel.add(buttons[i]);
        }
        add(panel, BorderLayout.CENTER);

        setTitle("calcu");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.charAt(0) == 'C') {
            display.setText("");
            result = 0;
            operator = "=";
            startOfNumber = true;
        } else if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            if (startOfNumber) {
                display.setText(command);
            } else {
                display.setText(display.getText() + command);
            }
            startOfNumber = false;
        } else {
            // If an operator is pressed, perform the calculation and update the display
            if (startOfNumber) {
                // If a second operator is pressed, replace the previous one
                if (command.equals("-")) {
                    display.setText(command);
                    startOfNumber = false;
                } else {
                    operator = command;
                }
            } else {
                double x = Double.parseDouble(display.getText());
                calculate(x);
                operator = command;
                startOfNumber = true;
            }
        }
    }

    public void calculate(double n) {
        switch (operator) {
            case "+":
                result += n;
                break;
            case "-":
                result -= n;
                break;
            case "*":
                result *= n;
                break;
            case "/":
                result /= n;
                break;
            case "=":
                result = n;
                break;
        }
        display.setText("" + result);
    }

    // Main method
    public static void main(String[] args) {
        new Main();
    }
}