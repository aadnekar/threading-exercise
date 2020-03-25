package exercise;

/**
 * This class implements a customer, which is used for holding data
 * and update the statistics
 */
public class Customer {

    /**
     *  Creates a new Customer.  Each customer should be given a
     *  unique ID
     */

    private static SynchronizedInteger id_incrementer = new SynchronizedInteger(1);
    private int id;

    public Customer() {
        // TODO: Implement required functionality.
        this.id = id_incrementer.get();
        SushiBar.numberOfCustomers = this.id;
        id_incrementer.increment();
    }


    /**
     * Here you should implement the functionality for ordering food
     * as described in the assignment.
     */
    public void order() {
        // TODO: Implement required functionality.
        int numberOfOrders = (int) (Math.random() * SushiBar.maxOrder);
        int numberOfServedOrders = (int) (Math.random() * numberOfOrders);
        int numberOfTakeAwayOrders = numberOfOrders - numberOfServedOrders;

        SushiBar.write("Customer " + getCustomerID() + " is ordering \nTakeaway: " + numberOfTakeAwayOrders + "\nServings: " + numberOfServedOrders + "\nTOTAL: " + numberOfOrders);
        SushiBar.servedOrders.add(numberOfServedOrders);
        SushiBar.takeawayOrders.add(numberOfTakeAwayOrders);
        SushiBar.totalOrders.add(numberOfOrders);
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        // TODO: Implement required functionality.
        return this.id;
    }

}
