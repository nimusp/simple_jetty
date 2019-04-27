/*
 * Developed by Sumin Pavel on 4/27/19 4:45 PM.
 * Last modified 4/27/19 4:45 PM
 */

package myself.inc.servlets;

import com.google.gson.Gson;
import myself.inc.accounts.AccountService;
import myself.inc.accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionsServlet extends HttpServlet {

    private final AccountService accountService;

    public SessionsServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sessionId = req.getSession().getId();
        UserProfile profile = accountService.getUserBySession(sessionId);
        if (profile != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            String json = new Gson().toJson(profile);
            resp.getWriter().println(json + "\nHello, " + profile.getLogin());
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        resp.setContentType("text/html;charset=utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = new UserProfile(login, password, "test_email");
        accountService.addSession(req.getSession().getId(), profile);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Success register for " + profile.getLogin());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");

        String sessionId = req.getSession().getId();
        UserProfile profile = accountService.getUserBySession(sessionId);

        if (profile == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            resp.getWriter().println("Goodbye, " + profile.getLogin() + "!");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
