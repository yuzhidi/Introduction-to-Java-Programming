package chapter42; 

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MixedContent extends HttpServlet {
  /** Process the HTTP Get request */
  public void doGet(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String country = request.getParameter("country");

    out.println("<img src = \"" +
      "ImageContentWithDrawing\" align=right>");

    out.println("This is a clock created using a Java program " +
      "and sent to the browser by a servlet.");
    
    out.close();
  }
}
