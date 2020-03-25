package exercise;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class Clock extends Thread {
    Timer timer;

    public Clock(int seconds) {
        // A new Thread will be created, and scheduled to unset
        // SushiBar.isOpen after <duration> milliseconds.
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            // To prevent creating new customers.
            SushiBar.write("Sushi bar will now close and all customers must finish their orders!");
            SushiBar.write("***** DOOR CLOSED *****");
            SushiBar.isOpen = false;
            timer.cancel();
//            SushiBar.write("***** STATISTICS *****");
//            SushiBar.write("TOTAL NUMBER OF ORDERS: " + SushiBar.totalOrders.get());
//            SushiBar.write("TOTAL NUMBER OF TAKEAWAY ORDERS: " + SushiBar.takeawayOrders.get());
//            SushiBar.write("TOTAL NUMBER OF SERVED ORDERS: " + SushiBar.servedOrders.get());
//            SushiBar.write("***** END OF STATISTICS *****");
        }
    }

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
        return sdf.format(cal.getTime());
    }
}
