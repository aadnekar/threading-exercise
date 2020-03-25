package exercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SushiBar {
    // SushiBar settings.
    private static int waitingAreaCapacity = 20;
    private static int waitressCount = 7;
    private static int duration = 5;
    public static int maxOrder = 15;
    public static int waitressWait = 60; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 250; // Used to calculate the time the customer spends eating
    public static int doorWait = 1; // Used to calculate the interval at which the door tries to create a customer
    public static int numberOfCustomers = 0;
    public static boolean isOpen = true;

    // Creating log file.
    private static File log;
    private static String path = "./";

    // Variables related to statistics.
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) throws InterruptedException {
        log = new File(path + "log.txt");

        // Initializing shared variables for counting number of orders.
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);

        // TODO: initialize the bar and start the different threads.
        write("Instantiate the Sushi Bar");
        SushiBar sushiBar = new SushiBar();

        // Instantiate the clock as a thread to open the restaurant
        write("Instantiate Clock!");
        Thread clock = new Clock(duration);
        write("Clock Instantiated!");

        // Instantiate the threading classes
        write("Instantiating waiting area");
        WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);
        // Begin the thread taking in the runnable door
        write("Instantiating Door");
        Thread doorThread = new Thread(new Door(waitingArea), "Door-thread");
        doorThread.start();
        write("Door: " + doorThread.getName() + " has come alive!");

        // Begin the waitresses
        write("Instantiating Waitresses");
        List<Thread> waitressList = new ArrayList<>();
        for(int i = 0; i < waitressCount; i++) {
            Thread currentThread = new Thread(new Waitress(waitingArea), "Waitress " + (i+1));
            currentThread.start();
            waitressList.add(currentThread);
            write("Waitress: " + currentThread.getName() + " has come alive!");
        }

        for (Thread waitress : waitressList)
            waitress.join();

        SushiBar.write("***** STATISTICS *****");
        SushiBar.write("TOTAL NUMBER OF ORDERS: " + SushiBar.totalOrders.get());
        SushiBar.write("TOTAL NUMBER OF TAKEAWAY ORDERS: " + SushiBar.takeawayOrders.get());
        SushiBar.write("TOTAL NUMBER OF SERVED ORDERS: " + SushiBar.servedOrders.get());
        SushiBar.write("TOTAL NUMBER OF CUSTOMERS SERVED: " + SushiBar.numberOfCustomers);
        SushiBar.write("***** END OF STATISTICS *****");

    }

    // Writes actions in the log file and console.
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            // TODO: Auto-generated catch block.
            e.printStackTrace();
        }
    }
}
