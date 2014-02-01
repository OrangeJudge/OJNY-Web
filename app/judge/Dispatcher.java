package judge;

public class Dispatcher implements Runnable {
    @Override
    public void run() {
        synchronized (Dispatcher.class) {
            System.out.println("start judge");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("stop judge");
        }
    }
}
