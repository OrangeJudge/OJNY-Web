package controllers;

import models.Problem;
import play.mvc.Controller;
import play.mvc.Result;

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
}
