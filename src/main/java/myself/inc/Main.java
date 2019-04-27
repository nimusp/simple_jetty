package myself.inc;

import myself.inc.servlets.MirrorRequestServlet;
import myself.inc.servlets.RootRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception {
        RootRequestServlet rootRequestServlet = new RootRequestServlet();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(rootRequestServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
