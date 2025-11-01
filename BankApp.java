import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BankApp extends JPanel {
    private final BankCashier bankCashier;

    public BankApp() {
        bankCashier = new BankCashier();
        JButton addCustomerButton = new JButton("Add Customer");
        JButton serveCustomerButton = new JButton("Serve Customer");

        addCustomerButton.addActionListener(e -> {
            bankCashier.addCustomer();
            repaint();
        });
        serveCustomerButton.addActionListener(e -> serveCustomer());

        add(addCustomerButton);
        add(serveCustomerButton);
    }

    private void serveCustomer() {
        if (bankCashier.canServe()) {
            bankCashier.startServing();
            repaint(); // Repaint immediately to show the busy status
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
                    repaint(); // Repaint after serving is done
                }
            }.execute();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Bank Cashier System", 20, 20);
        g.drawString("Queue:", 20, 40);

        List<Customer> queueContents = bankCashier.getQueueContents();

        int y = 60;
        for (Customer customer : queueContents) {
            g.drawString("Customer " + customer.getCustomerId(), 20, y);
            y += 20;
        }

        if (bankCashier.isServing()) {
            g.drawString("Cashier is busy.", 20, 200);
        } else {
            g.drawString("Cashier is available.", 20, 200);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bank Cashier System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            frame.add(new BankApp());
            frame.setVisible(true);
        });
    }
}
