package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Judger;
import models.Submit;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.OJException;

import java.util.Date;

public class JudgerController extends Controller {
    public static Result updateSubmit() {
        ObjectNode out = Json.newObject();
        DynamicForm in = Form.form().bindFromRequest();
        try {
            try {
                int submitId = Integer.parseInt(in.get("id"));
                Submit submit = Submit.find.byId(submitId);
                submit.status = Integer.parseInt(in.get("status"));
                submit.detail = in.get("detail");
                submit.updateTime = new Date();
                submit.save();
                if (submit.status >= 200) {
                    synchronized (Judger.class) {
                        Judger judger = Judger.find.byId(1);
                        judger.queue -= 1;
                        judger.save();
                    }
                }
            } catch (NullPointerException npe) {
                throw new OJException(1, "Null Pointer Exception");
            }
        } catch (OJException oje) {
            out.put("error", oje.getCode());
            out.put("message", oje.getMessage());
        }
        return ok(out);
    }
}
