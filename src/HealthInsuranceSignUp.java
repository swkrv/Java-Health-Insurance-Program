// Importing necessary Java libraries for GUI components, events, and file handling
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Class representing the Health Insurance Sign-Up Page, extending JFrame
public class HealthInsuranceSignUp extends JFrame {

    // Declaration of GUI components
    private JLabel titleLabel, firstNameLabel, lastNameLabel, ageLabel, idLabel, passwordLabel;
    private JTextField firstNameField, lastNameField, ageField, idField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    // Constructor for HealthInsuranceSignUp
    public HealthInsuranceSignUp() {
        setTitle("Health Insurance Sign Up"); // Setting the title for the JFrame
        setSize(500, 300); // Setting the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting the close operation

        buildPage(); // Building the main GUI components

        setLocationRelativeTo(null); // Centering the frame on the screen
        setVisible(true); // Making the frame visible
    }

    // Method to build the GUI components
    private void buildPage() {
        JPanel mainPanel = new JPanel(); // Creating a main panel
        mainPanel.setLayout(new BorderLayout(10, 10)); // Setting layout for the main panel

        // Creating and configuring the title label
        titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Creating a panel for labels
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(5, 1, 5, 5));
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        // Adding labels to the labels panel
        labelsPanel.add(firstNameLabel = new JLabel("First Name: "));
        labelsPanel.add(lastNameLabel = new JLabel("Last Name: "));
        labelsPanel.add(ageLabel = new JLabel("Age: "));
        labelsPanel.add(idLabel = new JLabel("ID Number: "));
        labelsPanel.add(passwordLabel = new JLabel("Password: "));

        // Creating a panel for text fields
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(5, 1, 5, 5));
        // Adding text fields to the fields panel
        fieldsPanel.add(firstNameField = new JTextField(20));
        fieldsPanel.add(lastNameField = new JTextField(20));
        fieldsPanel.add(ageField = new JTextField(20));
        fieldsPanel.add(idField = new JTextField(20));
        fieldsPanel.add(passwordField = new JPasswordField(20));

        // Creating a button panel
        JPanel buttonPanel = new JPanel();
        // Adding sign-up button to the button panel
        buttonPanel.add(signUpButton = new JButton("Sign Up"));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(Color.GRAY);
        // Adding action listener to the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp(); // Calling signUp method when sign-up button is clicked
            }
        });

        JPanel eastPanel = new JPanel(); // Creating an empty panel for layout purposes
        eastPanel.setPreferredSize(new Dimension(100, 300)); // Setting preferred size for east panel

        // Adding components to the main panel with BorderLayout
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(labelsPanel, BorderLayout.WEST);
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);

        add(mainPanel); // Adding the main panel to the frame
    }

    // Method to perform sign-up operation
    private void signUp() {
        // Retrieving values from text fields and password field
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String age = ageField.getText();
        String idNumber = idField.getText();
        char[] password = passwordField.getPassword();

        // Validating password length
        if (password.length < 8) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long", "Error", JOptionPane.ERROR_MESSAGE);
            passwordField.setText(""); // Clearing password field
            return;
        }

        // Validating ID number length
        if (idNumber.length() < 10) {
            JOptionPane.showMessageDialog(this, "ID number must be at least 10 digits long", "Error", JOptionPane.ERROR_MESSAGE);
            idField.setText(""); // Clearing ID field
            return;
        }

        // Saving user data to a file
        saveUserData(firstName, lastName, age, idNumber, new String(password));
        openLoginPage(); // Opening the login page after successful sign-up
    }

    // Method to open the login page
    private void openLoginPage() {
        HealthInsurance loginPage = new HealthInsurance();
        loginPage.setVisible(true);
        dispose(); // Closing the sign-up page
    }

    // Method to save user data to a file
    private void saveUserData(String firstName, String lastName, String age, String idNumber, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_data.txt", true))) {
            // Writing user data to the file
            writer.write("ID Number: " + idNumber + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("ID Number: " + idNumber + "\n");
            writer.write("Password: " + password + "\n\n");

            JOptionPane.showMessageDialog(this, "Sign up successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user data", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Main method to start the HealthInsuranceSignUp application
    public static void main(String[] args) {
        new HealthInsuranceSignUp();
    }
}
