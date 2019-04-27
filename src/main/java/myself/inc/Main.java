/*
 * Developed by Sumin Pavel on 4/27/19 12:50 PM.
 * Last modified 4/27/19 12:50 PM
 */

package myself.inc;

import myself.inc.servlets.TestRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception {
        TestRequestServlet testRequestServlet = new TestRequestServlet();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(testRequestServlet), "/testPage");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
