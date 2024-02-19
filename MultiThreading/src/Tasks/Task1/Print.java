package Tasks.Task1;

public class Print extends Thread {
    private final String character;
    private static final Object lock = new Object();

    public Print(String character) {
        this.character = character;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                synchronized (lock) {
                    lock.notifyAll();
                    System.out.print(this.character);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}