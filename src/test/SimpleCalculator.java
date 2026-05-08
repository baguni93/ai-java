package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField num1Field;
    private JTextField num2Field;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton equalsButton;
    private JLabel resultLabel;

    private String selectedOperator = ""; // Stores the selected operation (+, -, *, /)

    // Modern Color Palette
    private final Color bgColor = new Color(44, 62, 80);      // Dark Blue-Gray
    private final Color panelColor = new Color(52, 73, 94);   // Lighter Blue-Gray
    private final Color textColor = new Color(236, 240, 241);  // Off-white
    private final Color operatorColor = new Color(230, 126, 34); // Orange
    private final Color equalsColor = new Color(39, 174, 96);   // Green
    private final Color fieldColor = new Color(255, 255, 255);

    public SimpleCalculator() {
        // Frame setup
        setTitle("Simple Calculator");
        setSize(400, 350); // Slightly larger for better spacing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        mainPanel.setBackground(bgColor);

        // Content panel
        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));
        contentPanel.setBackground(bgColor);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        inputPanel.setBackground(bgColor);

        JLabel num1Label = createStyledLabel("Number 1:");
        num1Field = createStyledTextField();
        
        JLabel num2Label = createStyledLabel("Number 2:");
        num2Field = createStyledTextField();

        inputPanel.add(num1Label);
        inputPanel.add(num1Field);
        inputPanel.add(num2Label);
        inputPanel.add(num2Field);

        // Operator panel
        JPanel operatorPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        operatorPanel.setBackground(bgColor);

        addButton = createStyledButton("+", operatorColor);
        subtractButton = createStyledButton("-", operatorColor);
        multiplyButton = createStyledButton("*", operatorColor);
        divideButton = createStyledButton("/", operatorColor);

        operatorPanel.add(addButton);
        operatorPanel.add(subtractButton);
        operatorPanel.add(multiplyButton);
        operatorPanel.add(divideButton);

        // Control panel
        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        controlPanel.setBackground(bgColor);

        equalsButton = createStyledButton("=", equalsColor);
        resultLabel = createStyledLabel("Result: ");
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        resultLabel.setHorizontalAlignment(SwingConstants.LEFT);

        controlPanel.add(equalsButton, BorderLayout.WEST);
        equalsButton.setPreferredSize(new Dimension(80, 40));
        controlPanel.add(resultLabel, BorderLayout.CENTER);

        // Layout assembly
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(operatorPanel, BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Action listeners
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        equalsButton.addActionListener(this);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setBackground(fieldColor);
        field.setForeground(bgColor);
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(panelColor, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        double num1, num2;
        double result = 0;
        boolean error = false;

        // Attempt to parse input numbers
        try {
            num1 = Double.parseDouble(num1Field.getText());
            num2 = Double.parseDouble(num2Field.getText());
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid input");
            return; // Stop execution if input is invalid
        }

        // Determine the operation based on the button pressed
        if (sourceButton == addButton) {
            selectedOperator = "+";
            result = num1 + num2;
        } else if (sourceButton == subtractButton) {
            selectedOperator = "-";
            result = num1 - num2;
        } else if (sourceButton == multiplyButton) {
            selectedOperator = "*";
            result = num1 * num2;
        } else if (sourceButton == divideButton) {
            selectedOperator = "/";
            if (num2 == 0) {
                resultLabel.setText("Error: Divide by zero");
                error = true;
            } else {
                result = num1 / num2;
            }
        } else if (sourceButton == equalsButton) {
            // If equals button is pressed, perform the last selected operation
            if (selectedOperator.isEmpty()) {
                resultLabel.setText("Error: Select an operation");
                error = true;
            } else {
                // Re-calculate using the stored operator
                switch (selectedOperator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            resultLabel.setText("Error: Divide by zero");
                            error = true;
                        } else {
                            result = num1 / num2;
                        }
                        break;
                }
            }
        }

        // Update the result label if no error occurred
        if (!error) {
            resultLabel.setText("Result: " + result);
            // If equals was pressed, clear the operator to prevent accidental re-use without re-selection.
            if (sourceButton == equalsButton) {
                selectedOperator = ""; // Clear operator after calculation is complete
            }
        }
    }

    public static void main(String[] args) {
        // Run the GUI creation on the event-dispatching thread
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}
