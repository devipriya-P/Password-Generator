import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PasswordGeneratorGUI extends JFrame {

    private JTextField passwordField;
    private JTextField lengthField; // Input field for password length
    private JCheckBox specialCharsCheckBox; // Checkbox to include special characters
    private JButton generateButton;
    private JLabel instructionLabel;
    private JLabel lengthLabel; // Label for password length input

    public PasswordGeneratorGUI() {
        // Set up the frame
        setTitle("Password Generator");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Initialize components
        instructionLabel = new JLabel("Click the button to generate a password:");
        lengthLabel = new JLabel("Enter password length: ");
        lengthField = new JTextField(5); // Text field for length input
        specialCharsCheckBox = new JCheckBox("Include special characters"); // Checkbox for special chars
        passwordField = new JTextField(20);
        generateButton = new JButton("Generate Password");

        // Add action listener to the button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int passwordLength = Integer.parseInt(lengthField.getText()); // Get user-defined length
                    if (passwordLength <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a positive number for length.");
                    } else {
                        // Get whether special characters should be included
                        boolean includeSpecialChars = specialCharsCheckBox.isSelected();
                        String generatedPassword = generatePassword(passwordLength, includeSpecialChars);
                        passwordField.setText(generatedPassword);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for the length.");
                }
            }
        });

        // Add components to the frame
        add(instructionLabel);
        add(lengthLabel);
        add(lengthField); // Text field for length input
        add(specialCharsCheckBox); // Checkbox for special chars
        add(passwordField);
        add(generateButton);
    }

    // Function to generate a random password
    public String generatePassword(int length, boolean includeSpecialChars) {
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()-_+=<>?/[]{}|";

        String allCharacters = uppercase + lowercase + numbers;

        // If user wants to include special characters, add them to the pool
        if (includeSpecialChars) {
            allCharacters += specialChars;
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(index));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        // Create an instance of the GUI and make it visible
        PasswordGeneratorGUI frame = new PasswordGeneratorGUI();
        frame.setVisible(true);
    }
}
