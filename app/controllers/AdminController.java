package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class AdminController extends Controller {
    public static Result loginPage() {
        return ok(views.html.admin.login.render());
    }

    public static Result login() {
        return redirect("/admin");
    }
}
