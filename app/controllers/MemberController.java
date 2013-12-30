package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Member;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.OJException;

public class MemberController extends Controller {
    public static Result register() {
        ObjectNode out = Json.newObject();
        DynamicForm in = Form.form().bindFromRequest();
        String username = in.get("username").trim();
        try {
            Member member = new Member();
            member.setUsername(username);
            member.setEmail(in.get("email"));
            member.setPassword(in.get("password"));
            member.gender = in.get("gender").equals("1");
            member.save();
            out.put("error", 0);
            out.put("message", "member is registered.");
        } catch (OJException e) {
            out.put("error", e.getCode());
            out.put("message", e.getMessage());
        }
        return ok(out);
    }
}
