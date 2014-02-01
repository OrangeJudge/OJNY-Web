package judge;

import models.Submit;

public class Judge {
    public synchronized static void submit(Submit submit) {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
