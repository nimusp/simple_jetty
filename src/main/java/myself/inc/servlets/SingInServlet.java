/*
 * Developed by Sumin Pavel on 4/27/19 6:06 PM.
 * Last modified 4/27/19 6:06 PM
 */

package myself.inc.servlets;

import myself.inc.accounts.AccountService;
import myself.inc.accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SingInServlet extends HttpServlet {

    private final AccountService accountService;

    public SingInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);
        if (profile == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Unauthorized");
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Authorized: " + profile.getLogin());
        }
    }
}
