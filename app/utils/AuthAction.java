package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
            ctx.args.put("mid", Integer.parseInt(strid));
            return delegate.call(ctx);
        }
    }
}