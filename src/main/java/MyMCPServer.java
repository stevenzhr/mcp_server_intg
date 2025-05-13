import mcpServer.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class MyMCPServer {
    public static void main(String[] args) throws Exception {

        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("server");

        Server server = new Server(threadPool);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(45450);
        server.addConnector(connector);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setResourceBase(".");

        context.addServlet(HttpServletSseServerTransportProvider.class, "/mcp/*");

        server.setHandler(context);
        server.start();
    }
}
