package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class StaticController extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result register() {
        return ok(views.html.member.register.render());
    }

}
