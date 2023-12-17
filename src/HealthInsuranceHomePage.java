// Importing necessary Java libraries for GUI components, events, and file handling
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Class representing the Health Insurance Home Page, extending JFrame
public class HealthInsuranceHomePage extends JFrame {
    // Declaration of GUI components
    private JLabel welcomeLabel;
    private JTextArea priceLabel1;
    private JTextArea priceLabel2;
    private JTextArea priceLabel3;
    private JButton subscribeButton1;
    private JButton subscribeButton2;
    private JButton subscribeButton3;
    private boolean hasSubscription = false; // Flag to check if the user has a subscription

    // Constructor for HealthInsuranceHomePage
    public HealthInsuranceHomePage() {
        setTitle("Health Insurance"); // Setting the title for the JFrame
        setSize(800, 600); // Setting the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting the close operation

        buildPage(); // Building the main GUI components

        setLocationRelativeTo(null); // Centering the frame on the screen
        setVisible(true); // Making the frame visible
    }

    // Method to build the GUI components
    private void buildPage() {
        JPanel mainPanel = new JPanel(); // Creating a main panel
        mainPanel.setLayout(new BorderLayout()); // Setting the layout of the main panel
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Setting border for the main panel

        // Initializing and configuring various GUI components
        welcomeLabel = new JLabel("Welcome to Health Insurance App!"); // Creating a welcome label
        // Creating and setting text for insurance type labels
        priceLabel1 = new JTextArea("Economy Health Insurance:\n\n" +
                "Offers essential coverage at a low cost.\n" +
                "Basic medical services with lower premiums and higher deductibles.\n" +
                "Limited coverage for specialized treatments or elective procedures.");
        priceLabel2 = new JTextArea("Basic Health Insurance:\n\n" +
                "Provides broader coverage beyond essentials.\n" +
                "Includes preventive care, specialist consultations, and more.\n" +
                "Moderate premiums and balanced coverage for comprehensive needs.");
        priceLabel3 = new JTextArea("Luxury Health Insurance:\n\n" +
                "Comprehensive coverage with higher premiums.\n" +
                "Extensive benefits like concierge services, top-tier networks, and wellness programs.\n" +
                "Tailored for individuals seeking premium care and flexibility in treatments.");
        // Creating subscribe buttons
        subscribeButton1 = new JButton("subscription");
        subscribeButton2 = new JButton("subscription");
        subscribeButton3 = new JButton("subscription");

        // Setting font, color, and other properties for the welcome label
        welcomeLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        welcomeLabel.setForeground(Color.GRAY);
        welcomeLabel.setOpaque(true);

        // Creating panels and configuring layouts
        JPanel topPanel = new JPanel(); // Creating a panel for the welcome label
        topPanel.add(welcomeLabel); // Adding the welcome label to the top panel
        mainPanel.add(topPanel, BorderLayout.NORTH); // Adding the top panel to the main panel

        JPanel centerPanel = new JPanel(); // Creating a panel for insurance type information and buttons
        centerPanel.setLayout(new GridLayout(3, 2, 20, 20)); // Setting grid layout for center panel
        centerPanel.add(createCardPanel(priceLabel1, subscribeButton1)); // Creating and adding card panel for insurance type 1
        centerPanel.add(createCardPanel(priceLabel2, subscribeButton2)); // Creating and adding card panel for insurance type 2
        centerPanel.add(createCardPanel(priceLabel3, subscribeButton3)); // Creating and adding card panel for insurance type 3
        // Setting colors for subscribe buttons
        subscribeButton1.setForeground(Color.WHITE);
        subscribeButton1.setBackground(Color.GRAY);
        subscribeButton2.setForeground(Color.WHITE);
        subscribeButton2.setBackground(Color.GRAY);
        subscribeButton3.setForeground(Color.WHITE);
        subscribeButton3.setBackground(Color.GRAY);
        mainPanel.add(centerPanel, BorderLayout.CENTER); // Adding the center panel to the main panel

        // Creating a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("user"); // Creating a menu for user-related actions

        // Creating menu items for user-related actions
        JMenuItem personalInfoItem = new JMenuItem("Personal Information");
        // Adding action listener to display personal information
        personalInfoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUserInfo(HealthInsurance.getLoggedInUserId());
            }
        });

        // Creating menu items for insurance-related actions
        JMenuItem insuranceTypeItem = new JMenuItem("Insurance Information");
        // Adding action listener to display insurance information
        insuranceTypeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayInsuranceInformation(HealthInsurance.getLoggedInUserId());
            }
        });

        JMenuItem upgradeInsuranceItem = new JMenuItem("Upgrade Insurance");
        // Adding action listener to handle insurance upgrades
        upgradeInsuranceItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Insuranceupgrade upgradeFrame = new Insuranceupgrade();
                // Adding an upgrade listener to handle upgrade confirmation
                upgradeFrame.addUpgradeListener(new UpgradeListener() {
                    @Override
                    public void onUpgradeConfirmed() {
                        String selectedInsuranceType = upgradeFrame.getSelectedInsuranceType();
                        int selectedDuration = upgradeFrame.getSelectedDuration();
                        updateInsuranceInformation(selectedInsuranceType, selectedDuration);
                    }
                });
            }
        });

        JMenuItem logoutMenuItem = new JMenuItem("Log out");
        // Adding action listener to handle user logout
        logoutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
            }
        });

        // Adding menu items to the menu
        menu.add(personalInfoItem);
        menu.add(insuranceTypeItem);
        menu.add(upgradeInsuranceItem);
        menu.add(logoutMenuItem);

        JMenu helpMenu = new JMenu("Help"); // Creating a menu for help-related actions

        JMenuItem contactSupportItem = new JMenuItem("Contact Support");
        // Adding action listener to display support contact information
        contactSupportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HealthInsuranceHomePage.this, "Please contact with: Help@uj.edu.sa");
            }
        });

        helpMenu.add(contactSupportItem); // Adding contact support item to help menu

        menuBar.add(menu); // Adding user menu to menu bar
        menuBar.add(helpMenu); // Adding help menu to menu bar

        setJMenuBar(menuBar); // Setting menu bar for the frame
        add(mainPanel); // Adding the main panel to the frame
    }

    // Method to create a panel with insurance information and subscribe button
    private JPanel createCardPanel(JTextArea textArea, JButton subscribeButton) {
        JPanel cardPanel = new JPanel(); // Creating a panel for insurance type card
        cardPanel.setLayout(new BorderLayout()); // Setting layout for the card panel
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Setting border for the card panel

        // Configuring properties for the insurance type description
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setForeground(new Color(50, 100, 150));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JPanel textPanel = new JPanel(new BorderLayout()); // Creating a panel for text area
        textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER); // Adding text area to the text panel

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Creating a panel for buttons
        buttonPanel.add(subscribeButton); // Adding subscribe button to the button panel

        cardPanel.add(textPanel, BorderLayout.CENTER); // Adding text panel to the card panel
        cardPanel.add(buttonPanel, BorderLayout.SOUTH); // Adding button panel to the card panel

        // Adding action listener to subscribe button
        subscribeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (HealthInsurance.getLoggedInUserId() != null) {
            if (!hasSubscription) {
                showSubscriptionDurationFrame(textArea.getText(), getBasePrice(textArea.getText()));
            } else {
                JOptionPane.showMessageDialog(HealthInsuranceHomePage.this,
                        "You already have a subscription. Click 'Upgrade Insurance' in the user menu to upgrade.");
            }
        } else {
            JOptionPane.showMessageDialog(HealthInsuranceHomePage.this,
                    "Please log in to subscribe.");
        }
    }

            // Method to show subscription duration frame
            private void showSubscriptionDurationFrame(String insuranceType, int basePrice) {
                // Creating a frame for subscription duration selection
                JFrame durationFrame = new JFrame("Subscription Duration");
                durationFrame.setSize(300, 150);
                durationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JLabel durationLabel = new JLabel("Select the number of years for subscription:");
                JComboBox<String> yearsComboBox = new JComboBox<>(new String[]{"1 year", "2 years", "3 years"});
                JButton confirmButton = new JButton("Continue");

                // Adding action listener to the continue button for subscription confirmation
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedDuration = (String) yearsComboBox.getSelectedItem();
                        int years = Integer.parseInt(selectedDuration.split(" ")[0]);
                        int totalPrice = basePrice * years;
                        String subscriptionDetails = "Insurance Type: " + insuranceType + "\nBase Price: $" + basePrice +
                                "\nSubscription Duration: " + selectedDuration + "\nTotal Price: $" + totalPrice;
                        saveSubscriptionInformation(subscriptionDetails);
                        showDetails(subscriptionDetails);
                        durationFrame.dispose();
                    }
                });

                JPanel durationPanel = new JPanel(new GridLayout(3, 1, 10, 10));
                durationPanel.add(durationLabel);
                durationPanel.add(yearsComboBox);
                durationPanel.add(confirmButton);

                durationFrame.add(durationPanel);
                durationFrame.setLocationRelativeTo(null);
                durationFrame.setVisible(true);
                hasSubscription = true;
            }
        });

        return cardPanel;
    }

    // Method to retrieve base price for different insurance types
    private int getBasePrice(String insuranceDescription) {
        if (insuranceDescription.contains("Economy")) {
            return 200;
        } else if (insuranceDescription.contains("Basic")) {
            return 400;
        } else if (insuranceDescription.contains("Luxury")) {
            return 700;
        } else {
            return 0;
        }
    }

    // Method to show subscription details in a dialog
    private void showDetails(String details) {
        JFrame detailsFrame = new JFrame("Subscription Details");
        detailsFrame.setSize(400, 300);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea detailsArea = new JTextArea(details);
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsArea.setForeground(new Color(50, 100, 150));
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);

        JButton payButton = new JButton("Confirm");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Subscription successful!");
                detailsFrame.dispose();
            }
        });

        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.add(new JScrollPane(detailsArea), BorderLayout.CENTER);
        detailsPanel.add(payButton, BorderLayout.SOUTH);

        detailsFrame.add(detailsPanel);
        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setVisible(true);
    }
    private void showSubscriptionDetailsDialog(String details, String title) {
    // Creating a JFrame to display subscription details
    JFrame detailsFrame = new JFrame(title);
    detailsFrame.setSize(400, 300);
    detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the frame on window close

    // Creating a JTextArea to display the subscription details
    JTextArea detailsArea = new JTextArea(details);
    detailsArea.setEditable(false); // Making the text area non-editable
    detailsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    detailsArea.setForeground(new Color(50, 100, 150));
    detailsArea.setLineWrap(true); // Enable line wrapping
    detailsArea.setWrapStyleWord(true); // Wrap at word boundaries

    // Creating a JPanel to hold the JTextArea within a scrollable view
    JPanel detailsPanel = new JPanel(new BorderLayout());
    detailsPanel.add(new JScrollPane(detailsArea), BorderLayout.CENTER);

    // Adding the detailsPanel to the detailsFrame
    detailsFrame.add(detailsPanel);

    // Centering the frame on the screen
    detailsFrame.setLocationRelativeTo(null);

    // Making the frame visible
    detailsFrame.setVisible(true);
}

    // Method to save subscription information to a file
    private void saveSubscriptionInformation(String details) {
    String userId = HealthInsurance.getLoggedInUserId();
    if (userId != null) {
        String fileName = "Subscription_" + userId + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(details);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to save subscription information");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please log in to save subscription information.");
    }
}
    private void updateInsuranceInformation(String insuranceType, int duration) {
    // Assuming you want to update the insurance information for the logged-in user
    String userId = HealthInsurance.getLoggedInUserId();

    if (userId != null && !userId.isEmpty()) {
        String fileName = "Subscription_" + userId + ".txt";
        File file = new File(fileName);

        if (file.exists()) {
            try {
                // Open a FileWriter in append mode to update the existing file
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Append the updated information (for example, appending duration or type)
                bufferedWriter.write("\nUpdated Insurance Type: " + insuranceType);
                bufferedWriter.write("\nUpdated Duration: " + duration + " years");
                
                bufferedWriter.close(); // Close the writer to save changes
                JOptionPane.showMessageDialog(null, "Insurance information updated successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to update insurance information");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No subscription information found for the user.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Error: User not found.");
    }
}
    // Method to display user information in a frame
private void displayUserInfo(String userId) {
    // Creating a frame for displaying user information
    JFrame userInfoFrame = new JFrame("User Information");
    userInfoFrame.setSize(300, 200);
    userInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JTextArea userInfoArea = new JTextArea();
    userInfoArea.setEditable(false);
    userInfoArea.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
    userInfoArea.setForeground(new Color(50, 100, 150));

    if (userId != null && !userId.isEmpty()) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            boolean foundUser = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID Number: " + userId)) {
                    foundUser = true;
                } else if (foundUser) {
                    userInfoArea.append(line + "\n");
                }

                if (foundUser && line.trim().isEmpty()) {
                    break;
                }
            }

            if (!foundUser) {
                userInfoArea.setText("Error: User information not found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            userInfoArea.setText("Error: Unable to retrieve user information");
        }
    } else {
        userInfoArea.setText("Error: User not found");
    }

    userInfoFrame.add(new JScrollPane(userInfoArea));
    userInfoFrame.setLocationRelativeTo(null);
    userInfoFrame.setVisible(true);
}

// Method to display insurance information in a frame
private void displayInsuranceInformation(String userId) {
    // Checking if user ID exists and is not empty
    if (userId != null && !userId.isEmpty()) {
        String fileName = "Subscription_" + userId + ".txt";
        File file = new File(fileName);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder subscriptionDetails = new StringBuilder();
                String line;

                // Reading subscription details from the file
                while ((line = reader.readLine()) != null) {
                    subscriptionDetails.append(line).append("\n");
                }

                // Displaying subscription details in a dialog
                showSubscriptionDetailsDialog(subscriptionDetails.toString(), "Insurance Information");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to read subscription details");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No subscription information found for the user.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Error: User not found.");
    }
}

// Method to handle user logout and open login page
private void handleLogout() {
    HealthInsurance loginPage = new HealthInsurance();
    loginPage.setVisible(true);
    dispose();
}

// Main method to start the HealthInsuranceHomePage application
public static void main(String[] args) {
    new HealthInsuranceHomePage();
}
}
    