package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import judge.Judge;
import models.Problem;
import models.Submit;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Authentication;
import utils.OJException;

import java.util.ArrayList;
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
        List<Submit> submits;
        if (session("mid") != null) {
            submits = Submit.find.where("member_id = " + session("mid") + " and problem_id = " + problem.id)
                    .order("id DESC").findList();
        } else {
            submits = new ArrayList();
        }
        return ok(views.html.problem.display.render(problem, submits));
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
                submit.setMember(Integer.parseInt(session("mid")));
                submit.language = Integer.parseInt(in.get("language"));
                submit.source = in.get("source");
                submit.save();
                Judge.submit(submit);
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
