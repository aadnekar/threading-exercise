package exercise;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

    private WaitingArea waitingArea;

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
        // TODO: Implement required functionality.
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        // TODO: Implement required functionality.
        while(SushiBar.isOpen || !waitingArea.isEmpty()) {
            // Run this as long as the bar is open or there is someone still in the waiting area.
            try {
                Customer customer = waitingArea.next();

                // If there were no customers in the waiting area then customer will be null
                if (customer != null) {
                    SushiBar.write(Thread.currentThread().getName() + " is waiting some time before taking the order.");
                    Thread.currentThread().sleep(SushiBar.waitressWait);
                    SushiBar.write(Thread.currentThread().getName() + " is now taking the order for customer: " + customer.getCustomerID());
                    customer.order();
                    Thread.currentThread().sleep(SushiBar.customerWait);
                }
            } catch (InterruptedException e) {
                SushiBar.write("The Queue is empty " + Thread.currentThread().getName() + " is waiting for some more customers.");
            }
        }




    }


}

