/*
 * SnapLogic - Data Integration
 *
 * Copyright (C) 2013, SnapLogic, Inc.  All rights reserved.
 *
 * This program is licensed under the terms of
 * the SnapLogic Commercial Subscription agreement.
 *
 * "SnapLogic" is a trademark of SnapLogic, Inc.
 */
    
 package integrate_example;

 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 
 import java.io.IOException;
 
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 
 /**
  * SseHandler is responsible for handling Server-Sent Events (SSE) requests.
  * This is a simple implementation that returns a success message.
  */
 public class SseHandler extends HttpServlet {
     
     private static final long serialVersionUID = 1L;
     private static final Logger log = LogManager.getLogger("sl.sse");
     
     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException {
         log.info("Received SSE connection request");
         
         response.setContentType("text/html");
         response.setStatus(HttpServletResponse.SC_OK);
         response.getWriter().println("<html><body>");
         response.getWriter().println("<h1>SSE Connection Successful</h1>");
         response.getWriter().println("<p>The server has successfully established an SSE " +
                 "connection.</p>");
         response.getWriter().println("</body></html>");
     }
     
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException {
         // Handle POST requests if needed
         doGet(request, response);
     }
 }