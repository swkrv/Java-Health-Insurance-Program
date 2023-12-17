// Importing necessary Java libraries for GUI components, events, and file handling
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Class representing the Health Insurance program, extending JFrame
public class HealthInsurance extends JFrame {
    
    private static String loggedInUserId;  // Static variable to hold the ID of the logged-in user

    public static String getLoggedInUserId() {  // Getter for logged-in user ID
        return loggedInUserId;
    }

    public static void setLoggedInUserId(String userId) {    // Setter for logged-in user ID
        loggedInUserId = userId;
    }

    // Declaration of GUI components
    JLabel iconLabel, signUpLabel, IDLabel, passwordLabel;
    JTextField IDnumber;
    JPasswordField password;
    JButton login, signUp;
    JPanel panel1, panel2, panel3, panel4;

    final int WINDOW_WIDTH = 340;
    final int WINDOW_HEIGHT = 350;

    // Constructor for HealthInsurance class
    public HealthInsurance() {
        setTitle("Health Insurance ");  // Setting the title for the JFrame
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);  // Setting the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Setting the close operation

        buildPage();  // Building the main GUI components

        add(panel2, BorderLayout.NORTH);  // Adding panel2 to the top of the frame
        add(panel1, BorderLayout.CENTER);  // Adding panel1 to the center of the frame

        setLocationRelativeTo(null);  // Centering the frame on the screen
        setVisible(true);  // Making the frame visible
    }

    // Method to build the GUI components
    public void buildPage() {
        // Initializing and configuring panels
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel4 = new JPanel();

        // Creating and configuring GUI elements
        iconLabel = new JLabel();
        // Loading and resizing an image for the icon
        ImageIcon logo = new ImageIcon("HI.jpg");
        Image originalImage = logo.getImage();
        iconLabel.setIcon(logo);
        Image resizedImage = originalImage.getScaledInstance(220, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(resizedImage);
        iconLabel.setIcon(resizedLogo);

        IDLabel = new JLabel("ID number: ");
        passwordLabel = new JLabel("Password: ");

        IDnumber = new JTextField(20);
        password = new JPasswordField(20);

        signUpLabel = new JLabel("Don't have an account? ");
        signUpLabel.setForeground(Color.blue);

        signUp = new JButton("Sign Up!");
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(Color.GRAY);
        login = new JButton("Login");

        // ActionListener for the login button
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to validate login credentials
                String enteredID = IDnumber.getText();
                String enteredPassword = new String(password.getPassword());

                if (isValidLogin(enteredID, enteredPassword)) {
                    // Display a success message upon successful login
                    JOptionPane.showMessageDialog(HealthInsurance.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    HealthInsurance.setLoggedInUserId(enteredID);  // Set the logged-in user ID

                    // Redirecting to the home page after successful login
                    HealthInsuranceHomePage homePage = new HealthInsuranceHomePage();
                    homePage.setVisible(true);
                    dispose();
                } else {
                    // Display an error message for invalid credentials
                    JOptionPane.showMessageDialog(HealthInsurance.this, "Invalid ID number or password", "Error", JOptionPane.ERROR_MESSAGE);

                    IDnumber.setText("");  // Clear the ID number field
                    password.setText("");  // Clear the password field
                }
            }
        });

        // ActionListener for the sign-up button
        signUp.addActionListener(new SignUp());

        // Setting colors for buttons
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(Color.GRAY);
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLACK);

        // Adding components to panels
        panel1.add(IDLabel);
        panel1.add(IDnumber);
        panel1.add(passwordLabel);
        panel1.add(password);
        panel4.add(signUpLabel);
        panel4.add(signUp);
        panel3.add(login, BorderLayout.NORTH);
        panel3.add(panel4, BorderLayout.CENTER);
        panel1.add(panel3);
        panel2.add(iconLabel);
    }

    // Method to validate login credentials
    private boolean isValidLogin(String enteredID, String enteredPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            // Reading user data from a file and validating credentials
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID Number:")) {
                    String storedID = line.substring("ID Number:".length()).trim();

                    String passwordLine = reader.readLine();
                    if (passwordLine != null && passwordLine.startsWith("Password:")) {
                        String storedPassword = passwordLine.substring("Password:".length()).trim();

                        if (enteredID.equals(storedID) && enteredPassword.equals(storedPassword)) {
                            return true;  // Return true for valid credentials
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace for any IO exceptions
        }

        return false;  // Return false for invalid credentials
    }

    // ActionListener for the sign-up button
    private class SignUp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handling sign-up button click
            HealthInsuranceSignUp signUpPage = new HealthInsuranceSignUp();
            signUpPage.setVisible(true);  // Display sign-up page
            dispose();  // Close the current window
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        new HealthInsurance();  // Create an instance of HealthInsurance class
    }
}
