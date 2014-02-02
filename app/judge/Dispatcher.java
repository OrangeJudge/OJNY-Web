package judge;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Judger;
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
        synchronized (Dispatcher.this) {
            System.out.println("start judge");
            Judger judger = Judger.find.byId(1);
            ObjectNode postData = (ObjectNode) Json.toJson(submit);
            System.out.println(postData);
            postData.put("server", "http://localhost:9000");
            F.Promise<Integer> promiseOfError = WS.url(judger.address + "/submit").post(postData).map(
                    new F.Function<WS.Response, Integer>() {
                        public Integer apply(WS.Response response) {
                            return response.asJson().findPath("error").asInt();
                        }
                    });
            Integer error = promiseOfError.get();
            System.out.println("Error:" + error);
            if (error == 0) {
                synchronized (Judger.class) {
                    judger.refresh();
                    judger.queue += 1;
                    judger.save();
                }
            }
            System.out.println("stop judge");
        }
    }
}
