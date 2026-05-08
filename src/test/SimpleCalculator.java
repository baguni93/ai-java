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

    public SimpleCalculator() {
        // Frame setup
        setTitle("Simple Calculator");
        setSize(400, 300); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the main panel
        mainPanel.setBackground(new Color(240, 240, 240)); // Light grey background

        // Panel for other components (input, operators, control)
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(new Color(240, 240, 240)); // Match background

        // Input panel (GridLayout for two rows of label/field)
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(new Color(240, 240, 240)); // Match background
        JLabel num1Label = new JLabel("Number 1:");
        num1Label.setHorizontalAlignment(SwingConstants.RIGHT);
        num1Field = new JTextField(15);
        JLabel num2Label = new JLabel("Number 2:");
        num2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        num2Field = new JTextField(15);

        inputPanel.add(num1Label);
        inputPanel.add(num1Field);
        inputPanel.add(num2Label);
        inputPanel.add(num2Field);

        // Operator panel (GridLayout for buttons)
        JPanel operatorPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        operatorPanel.setBackground(new Color(240, 240, 240)); // Match background
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        // Customize button appearance slightly for better aesthetics
        Font buttonFont = new Font("SansSerif", Font.BOLD, 16);
        addButton.setFont(buttonFont);
        subtractButton.setFont(buttonFont);
        multiplyButton.setFont(buttonFont);
        divideButton.setFont(buttonFont);

        // You could add more color customization here if desired, e.g.,
        // addButton.setBackground(Color.GREEN);
        // subtractButton.setBackground(Color.RED);

        operatorPanel.add(addButton);
        operatorPanel.add(subtractButton);
        operatorPanel.add(multiplyButton);
        operatorPanel.add(divideButton);

        // Control panel (GridLayout for equals button and result label)
        JPanel controlPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240)); // Match background
        equalsButton = new JButton("=");
        equalsButton.setFont(buttonFont);
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 18f)); // Make result bold and larger

        controlPanel.add(equalsButton);
        controlPanel.add(resultLabel);

        // Add panels to contentPanel
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(operatorPanel, BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);

        // Add contentPanel to mainPanel
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Add action listeners
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        equalsButton.addActionListener(this);
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
