package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Member extends Controller {
    public static Result register() {
        return ok(views.html.member.register.render());
    }
}
