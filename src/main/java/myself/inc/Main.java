/*
 * Developed by Sumin Pavel on 4/27/19 12:50 PM.
 * Last modified 4/27/19 12:50 PM
 */

package myself.inc;

import myself.inc.accounts.AccountService;
import myself.inc.accounts.UserProfile;
import myself.inc.servlets.SessionsServlet;
import myself.inc.servlets.SingInServlet;
import myself.inc.servlets.SingUpServlet;
import myself.inc.servlets.TestRequestServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        accountService.addUser(new UserProfile("test"));
        accountService.addUser(new UserProfile("admin"));

        TestRequestServlet testRequestServlet = new TestRequestServlet();
        SessionsServlet sessionsServlet = new SessionsServlet(accountService);
        SingUpServlet singUpServlet = new SingUpServlet(accountService);
        SingInServlet singInServlet = new SingInServlet(accountService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(testRequestServlet), "/testPage");
        contextHandler.addServlet(new ServletHolder(sessionsServlet), "/api/v1/sessions");
        contextHandler.addServlet(new ServletHolder(singUpServlet), "/signUp");
        contextHandler.addServlet(new ServletHolder(singInServlet), "/signIn");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("templates");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[] {resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlerList);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
