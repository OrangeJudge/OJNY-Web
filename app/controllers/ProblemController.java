package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Problem;
import models.Submit;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Authentication;
import utils.OJException;

import java.util.List;

public class ProblemController extends Controller {
    public static Result problemsPage() {
        List<Problem> problems = Problem.find.all();
        return ok(views.html.problem.list.render(problems));
    }
    public static Result problemPage(int problemId) {
        Problem problem = Problem.find.byId(problemId);
        if (problem == null) {
            return ok("Problem not found");
        }
        return ok(views.html.problem.display.render(problem));
    }

    @Authentication
    public static Result submit() {
        ObjectNode out = Json.newObject();
        DynamicForm in = Form.form().bindFromRequest();
        try {
            try {
                int problemId = Integer.parseInt(in.get("problemId"));
                Submit submit = new Submit();
                submit.setProblem(problemId);

                out.put("error", 0);
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
