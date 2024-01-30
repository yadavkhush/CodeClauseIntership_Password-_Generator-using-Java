package genrator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class khushboo {

    private JFrame frame;
    private JTextField passwordField;
    private JCheckBox uppercaseCheckBox;
    private JCheckBox lowercaseCheckBox;
    private JCheckBox numbersCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JTextField lengthField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new khushboo().initialize();
        });
    }

    public void initialize() {
        frame = new JFrame("Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel lengthLabel = new JLabel("Password Length:");
        lengthLabel.setBounds(10, 20, 120, 25);
        panel.add(lengthLabel);

        lengthField = new JTextField();
        lengthField.setBounds(140, 20, 50, 25);
        panel.add(lengthField);

        JLabel optionsLabel = new JLabel("Options:");
        optionsLabel.setBounds(10, 50, 120, 25);
        panel.add(optionsLabel);

        uppercaseCheckBox = new JCheckBox("Uppercase Letters");
        uppercaseCheckBox.setBounds(10, 80, 150, 25);
        panel.add(uppercaseCheckBox);

        lowercaseCheckBox = new JCheckBox("Lowercase Letters");
        lowercaseCheckBox.setBounds(10, 110, 150, 25);
        panel.add(lowercaseCheckBox);

        numbersCheckBox = new JCheckBox("Numbers");
        numbersCheckBox.setBounds(10, 140, 150, 25);
        panel.add(numbersCheckBox);

        specialCharsCheckBox = new JCheckBox("Special Characters");
        specialCharsCheckBox.setBounds(10, 170, 150, 25);
        panel.add(specialCharsCheckBox);

        JButton generateButton = new JButton("Generate Password");
        generateButton.setBounds(10, 200, 180, 25);
        panel.add(generateButton);

        passwordField = new JTextField();
        passwordField.setBounds(200, 200, 180, 25);
        passwordField.setEditable(false);
        panel.add(passwordField);

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
    }

    private void generatePassword() {
        int length = 0;

        try {
            length = Integer.parseInt(lengthField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid password length. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (length <= 0) {
            JOptionPane.showMessageDialog(frame, "Password length must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String password = generateRandomPassword(length);
        passwordField.setText(password);
    }

    private String generateRandomPassword(int length) {
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

        StringBuilder validChars = new StringBuilder();

        if (uppercaseCheckBox.isSelected()) {
            validChars.append(uppercaseChars);
        }
        if (lowercaseCheckBox.isSelected()) {
            validChars.append(lowercaseChars);
        }
        if (numbersCheckBox.isSelected()) {
            validChars.append(numberChars);
        }
        if (specialCharsCheckBox.isSelected()) {
            validChars.append(specialChars);
        }

        if (validChars.length() == 0) {
            JOptionPane.showMessageDialog(frame, "Select at least one option for password generation.", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(validChars.length());
            password.append(validChars.charAt(index));
        }

        return password.toString();
    }
}