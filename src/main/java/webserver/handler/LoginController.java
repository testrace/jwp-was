package webserver.handler;

import db.DataBase;
import java.util.Optional;
import model.User;
import webserver.request.HttpRequest;
import webserver.request.UserBinder;
import webserver.response.HttpResponse;

public class LoginController implements Controller {

    @Override
    public HttpResponse handle(final HttpRequest request) {

        User user = UserBinder.from(request.getRequestBody().getParameters());

        final boolean loginSuccess = login(user);

        return HttpResponse.redirect(getRedirectLocation(loginSuccess))
            .addCookie("logined", String.valueOf(loginSuccess));
    }

    private boolean login(final User user) {
        return Optional.ofNullable(DataBase.findUserById(user.getUserId()))
            .map(it -> it.getPassword().equals(user.getPassword()))
            .orElse(false);
    }

    private String getRedirectLocation(final boolean loginSuccess) {
        if (loginSuccess) {
            return "/index.html";
        }
        return "/user/login_failed.html";
    }
}
