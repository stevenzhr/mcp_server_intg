import com.fasterxml.jackson.databind.ObjectMapper;

import mcpServer.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class MyMCPServer {
    public static void main(String[] args) throws Exception {

        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("server");

        Server server = new Server(threadPool);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(45451);
        server.addConnector(connector);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceBase(".");

        context.addServlet(HttpServletSseServerTransportProvider.class, "/mcp/*");

        // ServletContextHandler context = new ServletContextHandler();
        // context.setContextPath("/");
        // context.addServlet(new ServletHolder(transportProvider), "/*");

        server.setHandler(context);
        server.start();
    }
}
