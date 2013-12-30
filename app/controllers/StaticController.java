package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class StaticController extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result register() {
        return ok(views.html.member.register.render());
    }

}
