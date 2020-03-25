package exercise;

import java.util.*;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    private int size;
    private Queue<Customer> customerQueue;

    /**
     * Creates a new waiting area.
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        // TODO Implement required functionality
        this.size = size;
        this.customerQueue = new LinkedList<>();
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) throws InterruptedException {
        while (isFull()) {
            SushiBar.write("The waiting area is currently full and " +  Thread.currentThread().getName() + " will have to wait before letting more customers enter.");
            wait();
        }
        this.customerQueue.add(customer);
        SushiBar.write("Customer " + customer.getCustomerID() + " has entered the waiting area and is now waiting.");
        notifyAll();
    }

    /**
     * Consumer method.
     * @return The customer that is first in line.
     */
    public synchronized Customer next() throws InterruptedException {
        Customer customer = customerQueue.poll();
        notifyAll();
        return customer;
    }

    /**
     * @return True if the customer queue is full, false otherwise
     */
    public boolean isFull() {
        return this.size <= customerQueue.size();
    }

    /**
     * @return True if the customer queue is empty, false otherwise
    */
    public boolean isEmpty() {
        return this.customerQueue.size() == 0;
    }

}
