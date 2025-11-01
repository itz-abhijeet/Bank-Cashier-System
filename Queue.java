import java.util.ArrayList;
import java.util.List;

class Queue {
    private int maxSize;
    private Customer[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int size) {
        maxSize = size;
        queueArray = new Customer[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public boolean enqueue(Customer customer) {
        if (isFull()) {
            return false;
        }
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queueArray[++rear] = customer;
        nItems++;
        return true;
    }

    public Customer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Customer temp = queueArray[front];
        queueArray[front] = null;
        front++;
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public Customer peek() {
        if (isEmpty()) {
            return null;
        }
        return queueArray[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }

    public List<Customer> getQueueContents() {
        List<Customer> contents = new ArrayList<>();
        for (int i = 0; i < nItems; i++) {
            int index = (front + i) % maxSize;
            if (queueArray[index] != null) {
                contents.add(queueArray[index]);
            }
        }
        return contents;
    }

    public int getFront() {
        return front;
    }
}
