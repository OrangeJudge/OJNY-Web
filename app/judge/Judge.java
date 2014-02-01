package judge;

import models.Submit;

public class Judge {
    public static void submit(Submit submit) {
        (new Thread(new Dispatcher(submit))).start();
    }
}
