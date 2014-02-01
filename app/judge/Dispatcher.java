package judge;

import models.Submit;

public class Dispatcher implements Runnable {
    private Submit submit;
    public Dispatcher(Submit submit) {
        this.submit = submit;
    }
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
