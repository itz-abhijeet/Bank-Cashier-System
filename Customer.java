class Customer {
    private int customerId;
    private long arrivalTime;

    public Customer(int customerId) {
        this.customerId = customerId;
        this.arrivalTime = System.currentTimeMillis();
    }

    public int getCustomerId() {
        return customerId;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }
}
