package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Problem;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.OJException;

public class AdminController extends Controller {
    public static Result loginPage() {
        return ok(views.html.admin.login.render());
    }

    public static Result dashboardPage() {
        return ok(views.html.admin.dashboard.render());
    }

    public static Result login() {
        return redirect("/admin");
    }

    public static Result problemsPage() {
        return ok(views.html.admin.problems.render());
    }

    public static Result addProblemPage() {
        return ok(views.html.admin.addProblem.render());
    }

    public static Result addProblem() {
        ObjectNode out = Json.newObject();
        DynamicForm in = Form.form().bindFromRequest();
        try {
            Problem problem = new Problem();
            try {
                problem.title = in.get("title");
                problem.tags = in.get("tags");
                problem.source = in.get("source");
                problem.timeLimit = Integer.parseInt(in.get("timeLimit"));
                problem.memoryLimit = Integer.parseInt(in.get("memoryLimit"));
                problem.description = in.get("description");
                problem.specialJudge = in.get("specialJudge") != null;
            } catch (NullPointerException np) {
                throw new OJException(1, "Null Pointer Exception");
            } catch (NumberFormatException nf) {
                throw new OJException(1, "Number Format Exception");
            }
            problem.updateCreatedDate();
            problem.updateLastModifiedDate();
            problem.save();
            out.put("error", 0);
        } catch (OJException e) {
            out.put("error", e.getCode());
            out.put("message", e.getMessage());
        }
        return ok(out);
    }
}
