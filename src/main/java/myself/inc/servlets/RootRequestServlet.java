package myself.inc.servlets;

import myself.inc.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RootRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> pageMap = getPageVariablesMap(req);
        pageMap.put("message", "");

        resp.getWriter().println(PageGenerator.getInstance().getPage("templates/page.html", pageMap));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageMap = getPageVariablesMap(req);
        String message = req.getParameter("message");
        pageMap.put("message", message == null ? "" : message);

        resp.getWriter().println(PageGenerator.getInstance().getPage("templates/page.html", pageMap));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(message == null || message.isEmpty() ? HttpServletResponse.SC_BAD_REQUEST : HttpServletResponse.SC_OK);
    }

    private Map<String, Object> getPageVariablesMap(HttpServletRequest httpRequest) {
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        pageVariables.put("method", httpRequest.getMethod());
        pageVariables.put("URL", httpRequest.getRequestURL().toString());
        pageVariables.put("pathInfo", httpRequest.getPathInfo());
        pageVariables.put("sessionId", httpRequest.getSession().getId());
        pageVariables.put("parameters", httpRequest.getParameterMap().toString());
        return pageVariables;
    }
}
