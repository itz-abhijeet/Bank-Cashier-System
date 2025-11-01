import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BankApp extends JPanel {
    private final BankCashier bankCashier;
    private final JTextArea queueTextArea;
    private final JLabel statusLabel;
    private final JTextField nameTextField;

    public BankApp() {
        bankCashier = new BankCashier();
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 240));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Bank Cashier System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Queue Display Panel
        JPanel queuePanel = new JPanel(new BorderLayout());
        queuePanel.setBorder(BorderFactory.createTitledBorder("Customer Queue"));
        queueTextArea = new JTextArea();
        queueTextArea.setEditable(false);
        queueTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(queueTextArea);
        queuePanel.add(scrollPane, BorderLayout.CENTER);
        add(queuePanel, BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel(new BorderLayout(10, 0));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        JLabel nameLabel = new JLabel("Customer Name:");
        nameTextField = new JTextField();
        inputPanel.add(nameLabel, BorderLayout.WEST);
        inputPanel.add(nameTextField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton addCustomerButton = new JButton("Add Customer");
        JButton serveCustomerButton = new JButton("Serve Customer");
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(serveCustomerButton);

        controlPanel.add(inputPanel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.EAST);
        add(controlPanel, BorderLayout.SOUTH);

        // Status Panel
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(BorderFactory.createTitledBorder("Cashier Status"));
        statusLabel = new JLabel("Cashier is available.");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.EAST);

        // Action Listeners
        addCustomerButton.addActionListener(e -> addCustomer());
        serveCustomerButton.addActionListener(e -> serveCustomer());
    }

    private void addCustomer() {
        String customerName = nameTextField.getText().trim();
        if (!customerName.isEmpty()) {
            bankCashier.addCustomer(customerName);
            nameTextField.setText("");
            refreshUI();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a customer name.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void serveCustomer() {
        if (bankCashier.canServe()) {
            bankCashier.startServing();
            refreshUI();
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.sleep(5000); // Simulate serving time
                    bankCashier.serveNextCustomer();
                    return null;
                }

                @Override
                protected void done() {
                    bankCashier.finishServing();
                    refreshUI();
                }
            }.execute();
        }
    }

    private void refreshUI() {
        // Update queue display
        List<Customer> queueContents = bankCashier.getQueueContents();
        StringBuilder queueText = new StringBuilder();
        for (Customer customer : queueContents) {
            queueText.append(customer.getCustomerName()).append("\n");
        }
        queueTextArea.setText(queueText.toString());

        // Update status display
        if (bankCashier.isServing()) {
            statusLabel.setText("Cashier is busy.");
        } else {
            statusLabel.setText("Cashier is available.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bank Cashier System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.add(new BankApp());
            frame.setVisible(true);
        });
    }
}
