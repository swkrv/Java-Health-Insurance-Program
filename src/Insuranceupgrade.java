import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interface to handle upgrade confirmation
interface UpgradeListener {
    void onUpgradeConfirmed();
}

public class Insuranceupgrade extends JFrame {
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;

    private UpgradeListener upgradeListener;

    public Insuranceupgrade() {
        // Frame setup
        setTitle("Insurance upgrade");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Choose Insurance Type to upgrade:");
        label.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        labelPanel.add(label);

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(4, 1));
        checkBox1 = new JCheckBox("Economy ");
        checkBox2 = new JCheckBox("Basic ");
        checkBox3 = new JCheckBox("Luxury ");

        ButtonGroup checkBoxGroup = new ButtonGroup();
        checkBoxGroup.add(checkBox1);
        checkBoxGroup.add(checkBox2);
        checkBoxGroup.add(checkBox3);

        checkBoxPanel.add(checkBox1);
        checkBoxPanel.add(checkBox2);
        checkBoxPanel.add(checkBox3);

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new GridLayout(3, 1));
        radioButton1 = new JRadioButton("1 year");
        radioButton2 = new JRadioButton("2 years");
        radioButton3 = new JRadioButton("3 years");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        radioButtonPanel.add(radioButton1);
        radioButtonPanel.add(radioButton2);
        radioButtonPanel.add(radioButton3);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 8));
        JButton subscribeButton = new JButton("Upgrade");
        JButton cancelButton = new JButton("Cancel");
        JButton helpButton = new JButton("Help");
        int gap = 5;
        buttonPanel.setBorder(new EmptyBorder(gap, gap, gap, gap));
        buttonPanel.add(subscribeButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(helpButton);
        
        // Adding components to the frame
        add(labelPanel, BorderLayout.NORTH);
        add(checkBoxPanel, BorderLayout.WEST);
        add(radioButtonPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        subscribeButton.setForeground(Color.WHITE);
        subscribeButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.GRAY);
        helpButton.setForeground(Color.WHITE);
        helpButton.setBackground(Color.GRAY);

        labelPanel.setBackground(Color.LIGHT_GRAY);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Button actions
        subscribeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upgradeListener != null) {
                    String selectedInsurance = getSelectedInsuranceType();
                    int selectedDuration = getSelectedDuration();

                    if (selectedInsurance.isEmpty() || selectedDuration == 0) {
                        JOptionPane.showMessageDialog(Insuranceupgrade.this, "Please select both insurance type and duration", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    String message = "Health insurance upgrade request has been sent.\n\n" +
                            "Insurance Type: " + selectedInsurance + "\n" +
                            "Duration: " + selectedDuration + " year(s)";
                    JOptionPane.showMessageDialog(Insuranceupgrade.this, message);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Insuranceupgrade.this, "Please contact with: Help@uj.edu.sa");
            }
        });

        setVisible(true);
    }
    
    // Method to set the upgrade listener
    public void addUpgradeListener(UpgradeListener listener) {
        this.upgradeListener = listener;
    }

    // Method to get the selected insurance type
    public String getSelectedInsuranceType() {
        if (checkBox1.isSelected()) {
            return "Economy";
        } else if (checkBox2.isSelected()) {
            return "Basic";
        } else if (checkBox3.isSelected()) {
            return "Luxury";
        }
        return "";
    }
    
    // Method to get the selected duration
    public int getSelectedDuration() {
        if (radioButton1.isSelected()) {
            return 1;
        } else if (radioButton2.isSelected()) {
            return 2;
        } else if (radioButton3.isSelected()) {
            return 3;
        }
        return 0;
    }
    
    // Main method to start the application
    public static void main(String[] args) {
        new Insuranceupgrade();
    }
}
