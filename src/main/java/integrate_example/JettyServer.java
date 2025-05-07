package integrate_example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {
    public static void main(String[] args) {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("server");

        Server server = new Server(threadPool);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(10087);
        server.addConnector(connector);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");

        context.addServlet(SseHandler.class, "/sse");

        server.setHandler(context);
        try {
            server.start();
            System.out.println("Server started on port " + connector.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
