package controllers;

import play.mvc.Controller;
import play.mvc.Result;

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
}
