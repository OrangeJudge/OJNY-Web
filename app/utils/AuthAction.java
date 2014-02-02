package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Member;
import play.libs.F;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.SimpleResult;

import static play.mvc.Controller.session;

public class AuthAction extends Action<Authentication> {
    public F.Promise<SimpleResult> call(Http.Context ctx) throws Throwable {
        String strid = session("mid");
        if (strid == null) {
            if (configuration.json()) {
                ObjectNode out = Json.newObject();
                out.put("error", 1234);
                out.put("message", "Not log in");
                return F.Promise.pure((SimpleResult) ok(out));
            } else {
                return F.Promise.pure((SimpleResult) ok("Not log in"));
            }
        } else {
            Member member = Member.find.byId(Integer.parseInt(strid));
            if (member == null) {
                if (configuration.json()) {
                    ObjectNode out = Json.newObject();
                    out.put("error", 2345);
                    out.put("message", "Member not found");
                    return F.Promise.pure((SimpleResult) ok(out));
                } else {
                    return F.Promise.promise(new F.Function0<SimpleResult>() {
                        @Override
                        public SimpleResult apply() throws Throwable {
                            return redirect("/member/logout");
                        }
                    });
                }
            }
            ctx.args.put("mid", Integer.parseInt(strid));
            ctx.args.put("member", member);
            return delegate.call(ctx);
        }
    }
}