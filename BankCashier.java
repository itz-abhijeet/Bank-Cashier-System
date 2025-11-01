import java.util.List;

class BankCashier {
    private final Queue customerQueue;
    private boolean isServing;

    public BankCashier() {
        customerQueue = new Queue(10);
        isServing = false;
    }

    public synchronized void addCustomer(String customerName) {
        if (!customerQueue.isFull()) {
            customerQueue.enqueue(new Customer(customerName));
        }
    }

    public synchronized boolean canServe() {
        return !customerQueue.isEmpty() && !isServing;
    }

    public synchronized void startServing() {
        isServing = true;
    }

    public synchronized Customer serveNextCustomer() {
        return customerQueue.dequeue();
    }

    public synchronized void finishServing() {
        isServing = false;
    }

    public synchronized List<Customer> getQueueContents() {
        return customerQueue.getQueueContents();
    }

    public synchronized boolean isServing() {
        return isServing;
    }
}
