package judge;

import com.fasterxml.jackson.databind.JsonNode;
import models.Submit;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;

public class Dispatcher implements Runnable {
    private Submit submit;
    public Dispatcher(Submit submit) {
        this.submit = submit;
    }
    @Override
    public void run() {
        System.out.println("start judge");
        JsonNode postData = Json.toJson(submit);
        System.out.println(postData);
        F.Promise<Integer> result = WS.url("http://localhost:1314/submit").post(postData).map(
            new F.Function<WS.Response, Integer>() {
                public Integer apply(WS.Response response) {
                    System.out.println("Error:" + response.asJson().findPath("error"));
                    return response.asJson().findPath("error").asInt();
                }
            });
        System.out.println("stop judge");
    }
}
