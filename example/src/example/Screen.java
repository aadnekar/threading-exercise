package example;

public class Screen {

    // Becuse the following function is synchronized, only one thread will
    // be able to use it at a time.
    public synchronized void writeOut(String firstWord, String secondWord) {
        System.out.print(firstWord);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        System.out.println(secondWord);
    }
}
