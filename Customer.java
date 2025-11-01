class Customer {
    private final String customerName;
    private final long arrivalTime;

    public Customer(String customerName) {
        this.customerName = customerName;
        this.arrivalTime = System.currentTimeMillis();
    }

    public String getCustomerName() {
        return customerName;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }
}
