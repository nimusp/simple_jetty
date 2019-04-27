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

public class SingUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SingUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            UserProfile profile = new UserProfile(login, password, "test_email");
            accountService.addUser(profile);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
