package judge;

import com.fasterxml.jackson.databind.JsonNode;
import models.Submit;
import play.libs.Json;
import play.libs.WS;

public class Dispatcher implements Runnable {
    private Submit submit;
    public Dispatcher(Submit submit) {
        this.submit = submit;
    }
    @Override
    public void run() {
        synchronized (Dispatcher.class) {
            System.out.println("start judge");
            JsonNode postData = Json.toJson(submit);
            System.out.println(postData);
            WS.url("http://localhost:1314/submit").post(postData);
            System.out.println("stop judge");
        }
    }
}
