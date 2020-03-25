package exercise;

/**
 * This class implements the Door component of the sushi bar
 * assignment The Door corresponds to the Producer in the
 * producer/consumer problem
 */
public class Door implements Runnable {

    private WaitingArea waitingArea;

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This method will run when the door thread is created (and started).
     * The method should create customers at random intervals and try to put them in the waiting area.
     */
    @Override
    public void run() {
        while (SushiBar.isOpen) {
            try {
                waitingArea.enter(new Customer());
                Thread.sleep((int)(Math.random()*SushiBar.doorWait));
            } catch (InterruptedException e) {}
        }
    }
}
