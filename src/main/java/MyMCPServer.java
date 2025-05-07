import com.fasterxml.jackson.databind.ObjectMapper;

import mcpServer.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class MyMCPServer {
    public static void main(String[] args) throws Exception {
        // HttpServletSseServerTransportProvider transportProvider =
        //         new HttpServletSseServerTransportProvider(new ObjectMapper(), "/", "/sse");

        // McpAsyncServer syncServer = McpAsyncServer.builder(transportProvider)
        //         .serverInfo("custom-server", "0.0.1")
        //         .capabilities(McpSchema.ServerCapabilities.builder()
        //                 .tools(true)
        //                 .logging()
        //                 .build())
        //         .build();
        HttpServletSseServerTransportProvider transportProvider = new HttpServletSseServerTransportProvider();

        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("server");

        Server server = new Server(threadPool);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(45451);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(new ServletHolder(transportProvider), "/*");

        server.setHandler(context);
        server.start();
    }
}
