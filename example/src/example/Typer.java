package example;

public class Typer extends Thread {

    // Two words that will be typed on the screen by the typer
    private String firstWord, secondWord;

    // Reference to the Monitor-object
    private Screen screen;

    // Instasiate a new Typer object
    public Typer(Screen screen, String firstWord, String secondWord) {
        super();
        this.screen = screen;
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    // Method that will execute the Typer thread
    public void run() {
        for(int i = 0; i < 5; i++) {
            // Types out the two first words
            screen.writeOut(firstWord, secondWord);
            // Wait a seven tenths of a second
            try {
                Thread.sleep((int)(Math.random()*700));
            } catch (InterruptedException e) {
                // If someone calls an interrupt on the thread, it will be catched here.
                // Won't happen in this example
            }
        }
    }

    // Method that will execute when the application commence
    public static void main(String[] args) {
        // Instansiate Screen object
        Screen screen = new Screen();
        // Commence two typer threads that will type out the words given in the command line
        Typer typer1, typer2;
        typer1 = new Typer(screen, "kake", "spade");
        typer1.start();
        typer2 = new Typer(screen, "bamse", "mums");
        typer2.start();
    }
}
